package com.carcrud.utils;

import com.carcrud.model.Car;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;

public class DateValidator {
    public static boolean isFromPast(@RequestBody @Valid Car car) {
        if (car.getRegistrationDate() != null) {
            LocalDate carDate = LocalDate.from(car.getRegistrationDate());
            if (carDate.isAfter(LocalDate.now())) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}