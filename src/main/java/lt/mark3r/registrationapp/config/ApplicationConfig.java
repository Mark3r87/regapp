/**
 * File: ApplicationConfig.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the ApplicationConfig class, which is responsible for configuring the application's security settings.
 */

package lt.mark3r.registrationapp.config;

import lombok.RequiredArgsConstructor;
import lt.mark3r.registrationapp.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The ApplicationConfig class is responsible for configuring the application's security settings.
 * It provides beans for UserDetailsService, AuthenticationProvider, AuthenticationManager, and PasswordEncoder.
 * <p>
 * Annotations:
 * - @Configuration: Indicates that a class declares one or more @Bean methods and may be processed by the Spring
 * container to generate bean definitions and service requests for those beans at runtime.
 * - @RequiredArgsConstructor: Generates a constructor with 1 parameter for each field that requires special handling.
 * All non-initialized final fields get a parameter, as well as any fields that are marked as @NonNull that aren't
 * initialized where they are declared.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	// Dependencies are automatically injected by Spring Boot
	private final UserRepository repository;


	/**
	 * Provides a UserDetailsService bean.
	 *
	 * @return A UserDetailsService that loads user-specific data.
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
	}


	/**
	 * Provides an AuthenticationProvider bean.
	 *
	 * @return An AuthenticationProvider that uses DaoAuthenticationProvider with the UserDetailsService and
	 * PasswordEncoder beans.
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * Provides an AuthenticationManager bean.
	 *
	 * @param config The AuthenticationConfiguration.
	 * @return An AuthenticationManager.
	 * @throws Exception If there's an error during the creation of the AuthenticationManager.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}


	/**
	 * Provides a PasswordEncoder bean.
	 *
	 * @return A PasswordEncoder that uses BCryptPasswordEncoder.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
