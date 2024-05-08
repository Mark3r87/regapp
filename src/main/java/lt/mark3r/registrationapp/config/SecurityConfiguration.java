/**
 * File: SecurityConfiguration.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the SecurityConfiguration class, which is responsible for configuring the application's security settings.
 */

package lt.mark3r.registrationapp.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * The SecurityConfiguration class is responsible for configuring the application's security settings.
 * It provides beans for SecurityFilterChain and CorsConfigurationSource.
 * <p>
 * Annotations:
 * - @Configuration: Indicates that a class declares one or more @Bean methods and may be processed by the Spring
 * container to generate bean definitions and service requests for those beans at runtime.
 * - @EnableWebSecurity: Enable Spring Security’s web security support.
 * - @RequiredArgsConstructor: Generates a constructor with 1 parameter for each field that requires special handling.
 * All non-initialized final fields get a parameter, as well as any fields that are marked as @NonNull that aren't
 * initialized where they are declared.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	// Dependencies are automatically injected by Spring Boot
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;


	/**
	 * Provides a SecurityFilterChain bean.
	 *
	 * @param http The HttpSecurity.
	 * @return A SecurityFilterChain.
	 * @throws Exception If there's an error during the creation of the SecurityFilterChain.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and() // Enable CORS
				.csrf().disable()
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/admin/**", "/barber/**").hasAnyRole("ADMIN", "BARBER")
						.requestMatchers(HttpMethod.GET
								, "/api/barbers/**", "/api/barberservs/**"
								, "/api/barbers/{barberId}/workingschedules").permitAll()
						.requestMatchers("/api/auth/authenticate", "/api/appointments/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/**
	 * Provides a CorsConfigurationSource bean.
	 *
	 * @return A CorsConfigurationSource.
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*")); // Allow all headers
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
