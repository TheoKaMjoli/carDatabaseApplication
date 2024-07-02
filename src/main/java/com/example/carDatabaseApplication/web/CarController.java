package com.example.carDatabaseApplication.web;

import com.example.carDatabaseApplication.domain.Car;
import com.example.carDatabaseApplication.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars(){
        return carRepository.findAll();
    }

}
