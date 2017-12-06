package com.carcrud.service;

import com.carcrud.model.Car;
import com.carcrud.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCar(Long id){
        return Optional.ofNullable(carRepository.findOne(id));
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public void updateCar(Car car, Long id){
        Car dbCar = carRepository.findOne(id);
        dbCar.setBrand(car.getBrand());
        dbCar.setModel(car.getModel());
        dbCar.setColour(car.getColour());
        dbCar.setRegistrationDate(car.getRegistrationDate());
        dbCar.setRegistrationPlace(car.getRegistrationPlace());
        carRepository.save(dbCar);
    }
    public void deleteCar(Long id){
        carRepository.delete(id);
    }

    public boolean exist(Long id){
        return carRepository.exists(id);
    }

}
