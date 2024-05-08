/**
 * File: WebConfig.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the WebConfig class, which is responsible for configuring the application's Cross-Origin
 * Resource Sharing (CORS) settings.
 */

package lt.mark3r.registrationapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * The WebConfig class is responsible for configuring the application's Cross-Origin Resource Sharing (CORS) settings.
 * It implements the WebMvcConfigurer interface and overrides the addCorsMappings method to set the allowed origins,
 * methods, headers, and credentials.
 * <p>
 * Annotations:
 * - @Configuration: Indicates that a class declares one or more @Bean methods and may be processed by the Spring
 * container to generate bean definitions and service requests for those beans at runtime.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Configures the CORS mappings for the application.
	 * It allows requests from "http://localhost:3000" and permits "GET", "POST", "PUT", "DELETE", "HEAD", and "OPTIONS" methods.
	 * It also allows all headers and credentials.
	 *
	 * @param registry The CorsRegistry.
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true);
	}
}
