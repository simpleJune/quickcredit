package com.free.platform.base.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public class BeanUtil {
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    

    
    /**
     * 将javabean转为map类型，然后返回一个map类型的值
     * @param obj
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
    
    /**
     * Bean2Map
     * @param obj
     * @return
     */
    public static TreeMap<String, String> beanToSortMap(Object obj) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    Object o = propertyUtilsBean.getNestedProperty(obj, name);
                    if (o != null)
                        params.put(name, o.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
    
    /**
     * Map Sort
     * @param map
     * @return
     */
    public static Map<String, String> mapToSortMap(Map<String, String> map) {
        
        Map<String, String> tree = new TreeMap<String, String>();
        
        Set<String> keys = map.keySet();
        for (String key : keys) {
            tree.put(key, map.get(key));
        }
        
        return tree;
    }
    
    /**
     * Map2Bean
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    
    /**
     * 方法描述：Bean属性复制
     * @param source
     * @param target
     * @return
     */
    public static <S, T> T copyPropertiesQuietly(S source, T target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    Class<?> sourceType = sourcePd.getPropertyType();
                    Class<?> targetType = targetPd.getPropertyType();
                    // 类型不相等，且不是基本类型和包装类的转换
                    if(!targetType.isAssignableFrom(sourceType) && !isWrapperType(sourceType, targetType)) {
                        continue;
                    }
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        // 基本类型不要设置null
                        if(value == null && targetType.isPrimitive()) {
                            continue;
                        }
                        Method writeMethod = targetPd.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        try {
                             writeMethod.invoke(target, value);
                        } catch (java.lang.IllegalArgumentException e) {
                            // ignore it
                            throw e;
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target, target property[" + targetPd.getName() + "]", ex);
                    }
                }
            }
        }
        return target;
    }
    
    
    private static boolean isWrapperType(Class<?> typeA, Class<?> typeB) {
        return TypeUtils.isWrapperType(typeA, typeB) || TypeUtils.isWrapperType(typeB, typeA);
    }
    
    /**
     * 方法描述：读取属性值
     * @param bean
     * @param propertyName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPropertyValue(Object bean, String propertyName) {
            try {
                return (T) PropertyUtils.getProperty(bean, propertyName);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("读取属性值[" + propertyName + "]失败", e);
                return null;
            } catch (NoSuchMethodException e) {
                throw new FatalBeanException("Could not copy properties from source to target",  e);
            }
    }

}
