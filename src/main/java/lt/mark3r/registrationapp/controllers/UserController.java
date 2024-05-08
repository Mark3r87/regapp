/**
 * File: UserController.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * <p>
 * The UserController class is responsible for handling HTTP requests related to users.
 * It uses the UserRepository, BarberRepository, and PasswordEncoder to perform operations and returns the results
 * as HTTP responses.
 * <p>
 * Annotations:
 * - @CrossOrigin: Allows cross-origin requests from "http://localhost:3000".
 * - @RestController: Indicates that the class is a controller and the return values of its methods should be bound
 * to the web response body.
 * - @RequestMapping: Maps all HTTP operations by default to /api/user.
 * <p>
 * Methods:
 * - getMyBarber(Principal principal): Handles GET requests to retrieve the Barber associated with the current user.
 * It uses the UserRepository and BarberRepository to get the Barber and returns it as a response if found.
 * <p>
 * - updatePassword(Long userId, PasswordUpdateRequest request): Handles PUT requests to update a user's password.
 * It uses the UserRepository and PasswordEncoder to update the password and returns HTTP status OK if successful,
 * or HTTP status UNAUTHORIZED if the current password is incorrect, or HTTP status NOT FOUND if the user is not
 * found.
 * <p>
 * Inner Classes:
 * - PasswordUpdateRequest: A helper class used to encapsulate the current and new password in the updatePassword
 * method.
 */


package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.model.AppUser;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserRepository userRepository;
	private final BarberRepository barberRepository;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserRepository userRepository, BarberRepository barberRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.barberRepository = barberRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/me")
	public ResponseEntity<Barber> getMyBarber(Principal principal) {
		AppUser user = userRepository.findByEmail(principal.getName()).orElse(null);
		if (user != null && user.getBarber() != null) {
			Barber barber = barberRepository.findById(user.getBarber().getId()).orElse(null);
			if (barber != null) {
				return ResponseEntity.ok(barber);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping("/{userId}/password")
	public ResponseEntity<?> updatePassword(@PathVariable Long userId, @RequestBody PasswordUpdateRequest request) {
		// Retrieve user by ID
		Optional<AppUser> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			AppUser user = optionalUser.get();
			// Check if current password matches
			if (passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
				// Encode and update new password
				user.setPassword(passwordEncoder.encode(request.getNewPassword()));
				userRepository.save(user);
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect current password");
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	static class PasswordUpdateRequest {
		private String currentPassword;
		private String newPassword;

		// Getters and setters
		public String getCurrentPassword() {
			return currentPassword;
		}

		public void setCurrentPassword(String currentPassword) {
			this.currentPassword = currentPassword;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
	}
}

