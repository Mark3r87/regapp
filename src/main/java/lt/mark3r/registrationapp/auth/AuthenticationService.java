/**
 * File: AuthenticationService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AuthenticationService class, which handles the registration and authentication of users.
 */

package lt.mark3r.registrationapp.auth;

import lombok.RequiredArgsConstructor;
import lt.mark3r.registrationapp.config.JwtService;
import lt.mark3r.registrationapp.model.AppUser;
import lt.mark3r.registrationapp.model.Role;
import lt.mark3r.registrationapp.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The AuthenticationService class handles the registration and authentication of users.
 * It provides methods for registering a new user and authenticating an existing user.
 * <p>
 * Annotations:
 * - @Service: Indicates that an annotated class is a "Service", originally defined by Domain-Driven Design (Evans, 2003) as "an operation offered as an interface that stands alone in the model".
 * - @RequiredArgsConstructor: Generates a constructor with 1 parameter for each field that requires special handling. All non-initialized final fields get a parameter, as well as any fields that are marked as @NonNull that aren't initialized where they are declared.
 */

@Service
@RequiredArgsConstructor
public class AuthenticationService {


	// Dependencies are automatically injected by Spring Boot
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	/**
	 * Registers a new user.
	 * It creates a new AppUser, saves it in the database, generates a JWT token and returns it in the response.
	 *
	 * @param request The registration request containing the user's information.
	 * @return An AuthenticationResponse containing the JWT token.
	 */
	public AuthenticationResponse register(RegisterRequest request) {
		AppUser user = AppUser.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.BARBER)
				.build();
		repository.save(user);
		String jwtToken = jwtService.generateToken(user, user.getId().toString());
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);
		AppUser user = repository.findByEmail(request.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(user, user.getId().toString());
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}
