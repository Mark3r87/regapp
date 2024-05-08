/**
 * File: UserDetailsConfig.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the UserDetailsConfig class, which is responsible for configuring the application's user details
 * service and password encoder.
 */


package lt.mark3r.registrationapp.config;

import lt.mark3r.registrationapp.services.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * The UserDetailsConfig class is responsible for configuring the application's user details service and password
 * encoder.
 * It provides beans for UserDetailsServiceImpl and PasswordEncoder.
 * <p>
 * Annotations:
 * - @Configuration: Indicates that a class declares one or more @Bean methods and may be processed by the Spring
 * container to generate bean definitions and service requests for those beans at runtime.
 */
@Configuration
public class UserDetailsConfig {


	/**
	 * Provides a UserDetailsServiceImpl bean.
	 *
	 * @return A UserDetailsServiceImpl.
	 */
	@Bean
	public UserDetailsServiceImpl userDetailsService() {
		return new UserDetailsServiceImpl();
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
