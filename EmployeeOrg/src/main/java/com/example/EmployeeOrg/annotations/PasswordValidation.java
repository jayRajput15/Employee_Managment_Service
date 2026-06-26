package com.example.EmployeeOrg.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValidation {

    String message() default
            "Password must contain uppercase, lowercase, special character and minimum length 10";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
