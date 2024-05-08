/**
 * File: AuthenticationRequest.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AuthenticationRequest class, which is a data transfer object (DTO) that represents the data
 * necessary for an authentication request.
 */


package lt.mark3r.registrationapp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The AuthenticationRequest class is a data transfer object (DTO) that represents the data necessary for an
 * authentication request.
 * It includes the user's email and password.
 * <p>
 * Annotations:
 * - @Data: Generates getters and setters for all fields, a useful toString method, and hashCode and equals
 * implementations that check all non-transient fields.
 * - @Builder: Lets you automatically produce the code required to have your class be instantiable with code such as:
 * AuthenticationRequest.builder().email(email).password(password).build().
 * - @NoArgsConstructor: Generates a no-args constructor.
 * - @AllArgsConstructor: Generates a constructor with 1 parameter for each field in your class. Fields are initialized
 * in the order they are declared.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
	/**
	 * The email of the user trying to authenticate.
	 */
	private String email;

	/**
	 * The password of the user trying to authenticate.
	 */
	private String password;
}