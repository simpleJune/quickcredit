package com.free.credit.api.common.utils;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class PropertiesHelper implements EmbeddedValueResolverAware {
 
    private static StringValueResolver stringValueResolver;
 
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        stringValueResolver = resolver;
    }
 
    public static String getPropertiesValue(String name){
        return stringValueResolver.resolveStringValue("${"+name+"}");
    }
    
    public static String getPropertiesValue(String partnerId, String key){
        return stringValueResolver.resolveStringValue("${"+partnerId+"."+key+"}");
    }
}