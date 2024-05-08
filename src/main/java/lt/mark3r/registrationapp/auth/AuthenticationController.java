/**
 * File: AuthenticationController.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AuthenticationController class, which handles authentication-related requests.
 */


package lt.mark3r.registrationapp.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lt.mark3r.registrationapp.config.JwtService;
import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.mapper.WorkingScheduleMapper;
import lt.mark3r.registrationapp.model.*;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.BarberServRepository;
import lt.mark3r.registrationapp.repository.UserRepository;
import lt.mark3r.registrationapp.repository.WorkingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The AuthenticationController class handles authentication-related requests.
 * It provides endpoints for user registration, authentication, and token refresh.
 * It is annotated with @RestController, meaning it's a controller where every method returns a domain object instead
 * of a view.
 * It's also annotated with @RequestMapping("/api/auth") to map web requests to the AuthenticationController class.
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

	// Dependencies are automatically injected by Spring Boot
	@Autowired
	private final UserDetailsService userDetailsService;
	private final AuthenticationService service;
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final BarberServRepository barberServRepository;
	private final WorkingScheduleRepository workingScheduleRepository;
	private final JwtService jwtService;
	private final BarberRepository barberRepository;
	private final PasswordEncoder passwordEncoder;


	/**
	 * Handles the registration of a new user.
	 * Only an admin can register a new user.
	 * It creates a new Barber and AppUser, saves them in the database, creates BarberServ entities for the barber,
	 * and saves them as well.
	 * It also creates an initial WorkingSchedule for the barber.
	 *
	 * @param request The registration request containing the user's information.
	 * @return A ResponseEntity containing the authentication response with a JWT token and the barber's ID.
	 */
	@PostMapping("/register")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		// Create new Barber
		Barber barber = new Barber();
		barber.setName(request.getFirstName() + "'s Barber Shop");
		barber.setLocation("Default Location"); // Set default location
		barber.setContactInformation("Default Contact Information");
		barber.setRating(5.0);
		barber.setHasBeenRated(false);

		// Create new AppUser
		AppUser appUser = new AppUser();
		appUser.setFirstName(request.getFirstName());
		appUser.setLastName(request.getLastName());
		appUser.setEmail(request.getEmail());
		appUser.setPassword(passwordEncoder.encode(request.getPassword()));
		appUser.setRole(request.getRole());

		// Set the associated Barber and AppUser
		appUser.setBarber(barber);
		barber.setAppUser(appUser);

		// Save Barber and AppUser to database
		Barber savedBarber = barberRepository.save(barber);
		AppUser savedUser = userRepository.save(appUser);

		// Create BarberServ entities
		BarberServ haircutService = new BarberServ(savedBarber, TypeOfService.HAIRCUT, 30);
		BarberServ beardService = new BarberServ(savedBarber, TypeOfService.BEARD, 30);
		BarberServ hairAndBeardService = new BarberServ(savedBarber, TypeOfService.HAIRCUT_AND_BEARD, 60);

		// Save BarberServ entities to database
		barberServRepository.save(haircutService);
		barberServRepository.save(beardService);
		barberServRepository.save(hairAndBeardService);

		// Create an initial WorkingSchedule for the barber
		WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
		workingScheduleDTO.setDate(LocalDate.now());
		workingScheduleDTO.setTimeSlots(new ArrayList<>()); // Empty list for now
		workingScheduleDTO.setBarberId(savedBarber.getId());

		WorkingSchedule workingSchedule = WorkingScheduleMapper.toEntity(workingScheduleDTO, savedBarber);
		workingScheduleRepository.save(workingSchedule);

		// Create AuthenticationResponse
		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(jwtService.generateToken(savedUser, savedUser.getId().toString()));
		response.setBarberId(savedBarber.getId());

		// Return response
		return ResponseEntity.ok(response);
	}

	/**
	 * Authenticates a user based on their email and password.
	 * If the authentication is successful, it generates a JWT token and returns it along with the barber's ID
	 * in the response.
	 *
	 * @param authenticationRequest The authentication request containing the user's email and password.
	 * @return A ResponseEntity containing the authentication response with a JWT token and the barber's ID.
	 * @throws Exception If the authentication fails.
	 */


	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect email or password", e);
		}

		final AppUser appUser = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
		final String jwt = jwtService.generateToken(appUser, appUser.getId().toString());

		// Fetch barberId based on the authenticated user
		Long barberId = appUser.getBarber() != null ? appUser.getBarber().getId() : null;
		if (barberId == null) {
			throw new Exception("Barber ID is null");
		}

		return ResponseEntity.ok(AuthenticationResponse.builder().token(jwt).barberId(barberId).build());
	}


	/**
	 * Refreshes the authentication token.
	 * It checks if the provided token is valid, and if so, it generates a new token and returns it in the response.
	 *
	 * @param request The HttpServletRequest containing the old token.
	 * @return A ResponseEntity containing the new token if the old one was valid, or an error message otherwise.
	 */
	@PostMapping("/api/refresh")
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		String token = authToken.substring(7); // remove "Bearer " prefix
		String refreshedToken;
		try {
			if (jwtService.isTokenValid(token, null)) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));
				refreshedToken = jwtService.generateToken(userDetails);
			} else {
				return ResponseEntity.badRequest().body("Invalid token");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid token");
		}
		return ResponseEntity.ok(refreshedToken);
	}
}

