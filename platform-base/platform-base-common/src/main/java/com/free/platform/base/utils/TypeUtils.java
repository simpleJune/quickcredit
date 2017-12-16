package com.free.platform.base.utils;

import java.util.HashSet;
import java.util.Set;

public class TypeUtils {
    
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
    
    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
    
    /**
     * 判断是否包装类
     * @param type
     * @return
     */
    public static boolean isWrapperType(Class<?> type) {
        return WRAPPER_TYPES.contains(type);
    }
    
    /**
     * 是否基本类型或包装类
     * @param type
     * @return
     */
    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() || isWrapperType(type);
    }
    
    /**
     * 是否是基本类型的包装类
     * @param primitiveType
     * @param wrapperType
     * @return
     */
    public static boolean isWrapperType(Class<?> primitiveType, Class<?> wrapperType) {
        try {
            return primitiveType.isPrimitive() && isWrapperType(wrapperType) && wrapperType.getField("TYPE").get(null).equals(primitiveType);
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(Integer.class.isAssignableFrom(int.class));
    }
}
