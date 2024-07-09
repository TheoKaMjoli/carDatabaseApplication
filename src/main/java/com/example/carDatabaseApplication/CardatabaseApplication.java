package com.example.carDatabaseApplication;

import com.example.carDatabaseApplication.domain.AppUser;
import com.example.carDatabaseApplication.domain.Car;
import com.example.carDatabaseApplication.domain.Owner;
import com.example.carDatabaseApplication.repository.AppUserRepository;
import com.example.carDatabaseApplication.repository.CarRepository;
import com.example.carDatabaseApplication.repository.OwnerRepository;
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
	private final AppUserRepository appUserRepository;
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    public CardatabaseApplication(CarRepository carRepository,
								  OwnerRepository ownerRepository,
								  AppUserRepository appUserRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
        this.appUserRepository = appUserRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("Johnny", "Walker");
		Owner owner2 = new Owner("Sim", "Dope");
		Owner owner3 = new Owner("StogieT", "Tman");

		ownerRepository.saveAll(Arrays.asList(owner1,owner2,owner3));

		Car polo = new Car("Polo", "TSI", "Silver Grey", "CA377544", 2023, 269000, owner1);
		Car nissan = new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner2);
		Car toyota = new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2);
		Car bmw = new Car("BMW", "1 series", "Silver", "CY-22152", 2019, 22500, owner1);


		carRepository.save(polo);
		carRepository.save(nissan);
		carRepository.save(toyota);
		carRepository.save(bmw);

		AppUser user1 = new AppUser("user", "$2a$10$rOx//RJznZ8Za94gEDRxSOk6WyfJKoBaIMca8sC8p00xI3wAYJ3Xm", "USER");
		AppUser user2 = new AppUser("admin", "$2a$10$sj/IGETPoCmFTTYSm8Uo/.QXgPgBiMWOaF4APF8LovzZt9DkGpdk.", "ADMIN");
		appUserRepository.save(user1);
		appUserRepository.save(user2);

		//fetch all cars and log to console
		for(Car car : carRepository.findAll()){
			logger.info("brand: {}, model{}", car.getBrand(), car.getModel());
		}

		for(Owner owner : ownerRepository.findAll()){
			logger.info("firstname: {}, lastname: {}", owner.getFirstname(), owner.getLastname());
		}

		for(AppUser user : appUserRepository.findAll()){
			logger.info("username: {}, role: {}", user.getUsername(), user.getRole());
		}
	}
}
