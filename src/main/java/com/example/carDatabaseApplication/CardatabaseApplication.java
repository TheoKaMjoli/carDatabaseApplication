package com.example.SharingRecipeDemo;

import com.example.SharingRecipeDemo.domain.Car;
import com.example.SharingRecipeDemo.domain.Owner;
import com.example.SharingRecipeDemo.repository.CarRepository;
import com.example.SharingRecipeDemo.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    public CardatabaseApplication(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("Johnny", "Walker");
		Owner owner2 = new Owner("Sim", "Dope");

		ownerRepository.saveAll(Arrays.asList(owner1,owner2));

		carRepository.save(new Car(owner1, "Polo", "TSI", "Silver Grey", "CA377544", 2023, 269000));
		carRepository.save(new Car(owner2,"Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000));
		carRepository.save(new Car(owner2,"Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000));

		//fetch all cars and log to console
		for(Car car : carRepository.findAll()){
			logger.info("brand: {}, model{}", car.getBrand(), car.getModel());
		}

		for(Owner owner : ownerRepository.findAll()){
			logger.info("firstname: {}, lastname: {}", owner.getFirstname(), owner.getLastname());
		}
	}
}
