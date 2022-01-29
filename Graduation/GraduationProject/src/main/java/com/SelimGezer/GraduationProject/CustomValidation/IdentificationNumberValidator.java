package com.SelimGezer.GraduationProject.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificationNumberValidator implements ConstraintValidator<IdentificationNumber,Long> {

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if(aLong==null){
            return false;
        }
        if(aLong>10_000_000_000L && aLong%2==0){
            return true;
        }
        return false;
    }
}