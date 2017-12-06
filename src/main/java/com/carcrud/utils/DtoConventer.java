package com.carcrud.utils;

import com.carcrud.dto.CarDTO;
import com.carcrud.model.Car;

public class DtoConventer {
    public static CarDTO convertToDto(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setColour(car.getColour());
        carDTO.setId(car.getId());
        carDTO.setRegistrationDate(car.getRegistrationDate().toString());
        carDTO.setRegistrationPlace(car.getRegistrationPlace());
        return carDTO;
    }
}
