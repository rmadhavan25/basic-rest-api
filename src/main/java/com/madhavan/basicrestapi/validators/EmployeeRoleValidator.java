package com.madhavan.basicrestapi.validators;

import com.madhavan.basicrestapi.annotations.EmployeeRoleValidation;
import jakarta.validation.ConstraintValidator;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {

    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        if(value == null) return false;
        return value.equals("ADMIN") || value.equals("USER") || value.equals("MANAGER");
    }

}
