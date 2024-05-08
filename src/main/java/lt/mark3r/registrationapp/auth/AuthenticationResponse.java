/**
 * File: AuthenticationResponse.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AuthenticationResponse class, which is a data transfer object (DTO) that represents the data necessary for an authentication response.
 */

package lt.mark3r.registrationapp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The AuthenticationResponse class is a data transfer object (DTO) that represents the data necessary for an authentication response.
 * It includes the JWT token and the barber's ID.
 * <p>
 * Annotations:
 * - @Data: Generates getters and setters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields.
 * - @Builder: Lets you automatically produce the code required to have your class be instantiable with code such as:
 * AuthenticationResponse.builder().token(token).barberId(barberId).build().
 * - @NoArgsConstructor: Generates a no-args constructor.
 * - @AllArgsConstructor: Generates a constructor with 1 parameter for each field in your class. Fields are initialized in the order they are declared.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	/**
	 * The JWT token of the authenticated user.
	 */
	private String token;

	/**
	 * The ID of the barber associated with the authenticated user.
	 */
	private Long barberId;
}

