package com.carcrud.utils;

import com.carcrud.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CarValidator implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return Car.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", "brand is required","brand is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "model is required","model is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "colour", "colour is required","colour is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "registrationDate", "registrationDate is required","registrationDate is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "registrationPlace", "registrationPlace is required","registrationPlace is required");
    }

}


