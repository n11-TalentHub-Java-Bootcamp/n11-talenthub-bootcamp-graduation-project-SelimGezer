package com.SelimGezer.GraduationProject.CustomValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Repeatable(IdentificationNumber.IdentificationNumberList.class)
@Constraint(validatedBy = IdentificationNumberValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdentificationNumber {
    String message() default "Invalid idenfication number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface IdentificationNumberList {
        IdentificationNumber[] value();
    }
}
