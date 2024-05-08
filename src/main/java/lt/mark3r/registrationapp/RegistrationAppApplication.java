/**
 * File: RegistrationAppApplication.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-083
 * <p>
 * The RegistrationAppApplication class is the main entry point for the Spring Boot application.
 * It contains the main method which starts the application and a CommandLineRunner bean that creates a default barber.
 * Annotations:
 * - @SpringBootApplication: Indicates that the class is a Spring Boot application.
 * Methods:
 * - main(String[] args): The main method which starts the Spring Boot application.
 * - createDefaultBarber(UserRepository userRepository, BarberRepository barberRepository,
 * BarberServRepository barberServRepository, WorkingScheduleRepository workingScheduleRepository,
 * PasswordEncoder passwordEncoder): A CommandLineRunner bean that creates a default barber if one does not already
 * exist. It uses the UserRepository, BarberRepository, BarberServRepository, WorkingScheduleRepository,
 * and PasswordEncoder to create the barber, their services, and their working schedule. Does not return a value.
 */

package lt.mark3r.registrationapp;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.mapper.WorkingScheduleMapper;
import lt.mark3r.registrationapp.model.*;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.BarberServRepository;
import lt.mark3r.registrationapp.repository.UserRepository;
import lt.mark3r.registrationapp.repository.WorkingScheduleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class RegistrationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationAppApplication.class, args);
	}

	/**
	 * This bean should be commented or removed once first user is created
	 */
	@Bean
	public CommandLineRunner createDefaultBarber(UserRepository userRepository, BarberRepository barberRepository,
	                                             BarberServRepository barberServRepository,
	                                             WorkingScheduleRepository workingScheduleRepository,
	                                             PasswordEncoder passwordEncoder) {
		return args -> {
			/**
			 * replace with a intended email for first user.*/
			String defaultBarberEmail = "default@example.com";
			if (!userRepository.existsByEmail(defaultBarberEmail)) {
				AppUser barberUser = new AppUser();
				/**
				 * replace with a intended user name.*/
				barberUser.setFirstName("Default_name");
				/**
				 * replace with a intended user surname.*/
				barberUser.setLastName("Default_surname");
				barberUser.setEmail(defaultBarberEmail);
				/**
				 * replace with a secure password*/
				barberUser.setPassword(passwordEncoder.encode("password"));
				barberUser.setRole(Role.ADMIN);

				Barber barber = new Barber();

				/**
				 * barber details can be set in user menu, but can be set here on initial run*/
				barber.setName("default Barber Shop");
				barber.setLocation("123 Main St");
				barber.setContactInformation("123-456-7890");
				barber.setRating(5.0);
				barber.setHasBeenRated(true);

				barberUser.setBarber(barber);
				barber.setAppUser(barberUser);

				userRepository.save(barberUser);
				barberRepository.save(barber);

				BarberServ haircutService = new BarberServ(barber, TypeOfService.HAIRCUT, 30);
				BarberServ beardService = new BarberServ(barber, TypeOfService.BEARD, 30);
				BarberServ hairAndBeardService = new BarberServ(barber, TypeOfService.HAIRCUT_AND_BEARD, 60);

				barberServRepository.save(haircutService);
				barberServRepository.save(beardService);
				barberServRepository.save(hairAndBeardService);


				WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
				workingScheduleDTO.setDate(LocalDate.now());
				workingScheduleDTO.setTimeSlots(new ArrayList<>());
				workingScheduleDTO.setBarberId(barber.getId());

				WorkingSchedule workingSchedule = WorkingScheduleMapper.toEntity(workingScheduleDTO, barber);
				workingScheduleRepository.save(workingSchedule);
			}
		};
	}


}