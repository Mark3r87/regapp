package lt.mark3r.registrationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationAppApplication.class, args);
	}



	/*@Bean
	public CommandLineRunner createDefaultAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			String defaultAdminUsername = "admin";
			if (!userRepository.existsByUsername(defaultAdminUsername)) {
				AppUser admin = new AppUser();
				admin.setUsername(defaultAdminUsername);
				admin.setPassword(passwordEncoder.encode("password")); // replace with a secure password
				admin.setRole(Role.ADMIN);
				userRepository.save(admin);
			}
		};
	}*/






	/*@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			// Create and save entities here
			Barber barber = new Barber(null, "John Doe", Collections.emptyList(), "123 Barber Street", Arrays.asList("Haircuts", "Beard trims"), "123-456-7890", 5.0, Arrays.asList(TypeOfService.HAIRCUT, TypeOfService.BEARD), false);
			barber = barberRepository.save(barber);

			GuestClient guestClient = new GuestClient(null, "123-456-7891", "guest@example.com", null);
			guestClient = guestClientRepository.save(guestClient);

			Appointment appointment = new Appointment(null, LocalDate.now(), LocalTime.now(), barber, TypeOfService.HAIRCUT, guestClient);
			appointment = appointmentRepository.save(appointment);

			BarberServ barberServ = new BarberServ(barber, TypeOfService.HAIRCUT, 30);
			barberServRepository.save(barberServ);

			WorkingSchedule workingSchedule = new WorkingSchedule(null, LocalDate.now(), Arrays.asList(LocalTime.of(9, 0), LocalTime.of(10, 0)), barber);
			workingScheduleRepository.save(workingSchedule);

			// Update entities with references if necessary
			guestClient.setAppointment(appointment);
			guestClientRepository.save(guestClient);
		};
	}*/
}