package com.free.platform.base.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.free.platform.base.utils.StringUtils;
import com.free.platform.base.validation.NotBlank;

public class NotBlankValidator implements ConstraintValidator<NotBlank, String>{
    
    @Override
    public void initialize(NotBlank constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(value);
    }
    
}
