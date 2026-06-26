package com.example.EmployeeOrg.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {
    @Override
    public boolean isValid(String inputRoles, ConstraintValidatorContext constraintValidatorContext) {
        if(inputRoles == null){
            return false;
        }
        List<String> roles = List.of("USER","ADMIN");
        return roles.contains(inputRoles);
    }

}
