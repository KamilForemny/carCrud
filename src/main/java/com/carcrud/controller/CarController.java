package com.carcrud.controller;

import com.carcrud.dto.CarDTO;
import com.carcrud.model.Car;
import com.carcrud.service.CarService;
import com.carcrud.utils.CarValidator;
import com.carcrud.utils.DateValidator;
import com.carcrud.utils.DtoConventer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.carcrud.utils.DtoConventer.convertToDto;

@CrossOrigin
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarValidator validator;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> getCars() {
        List<CarDTO> cars = carService.getAllCars()
                .stream()
                .map(DtoConventer::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Long id) {
        Optional<Car> car = carService.getCar(id);
        if (car.isPresent()) {
            CarDTO carDTO = convertToDto(car.get());
            return new ResponseEntity<>(carDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createCar(@RequestBody /*@Valid*/ Car car, UriComponentsBuilder builder, BindingResult result) throws IllegalArgumentException {
        validator.validate(car, result);
        if (!DateValidator.isFromPast(car)) {
            result.addError(new FieldError("Car", "registrationDate", "date Must be from Past"));
        }
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        } else {
            carService.saveCar(car);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/cars/{id}").buildAndExpand(car.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateCar(@RequestBody Car car, @PathVariable @Min(1) Long id, BindingResult result) {
        if(carService.exist(id)) {
            validator.validate(car, result);
            if (!DateValidator.isFromPast(car)) {
                result.addError(new FieldError("Car", "registrationDate", "date Must be from Past"));
            }
            if (result.hasErrors()) {
                return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
            } else {
                carService.updateCar(car, id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }else {
            result.addError(new FieldError("Car","id","no car found"));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        if (carService.getCar(id).isPresent()) {
            carService.deleteCar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity("no car of such id", HttpStatus.NOT_FOUND);
        }
    }
}
