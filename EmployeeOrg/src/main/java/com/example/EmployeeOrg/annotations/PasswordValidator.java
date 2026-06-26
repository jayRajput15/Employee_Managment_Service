package com.example.EmployeeOrg.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if(password ==  null || password.length() < 10) {
            return false;
        }
        boolean hasUppper = false;
        boolean hasLower = false;
        boolean hasSpecial = false;
        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)) {
                hasUppper = true;
            }
            if(Character.isLowerCase(c)) {
                hasLower = true;
            }
            if(Character.isDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasUppper && hasLower && hasSpecial;
    }
}
