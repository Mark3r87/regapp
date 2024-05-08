/**
 * File: RegisterRequest.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the RegisterRequest class, which is a data transfer object (DTO) that represents the data necessary for a registration request.
 */

package lt.mark3r.registrationapp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.mark3r.registrationapp.model.Role;

/**
 * The RegisterRequest class is a data transfer object (DTO) that represents the data necessary for a registration request.
 * It includes the user's first name, last name, email, password, and role.
 * <p>
 * Annotations:
 * - @Data: Generates getters and setters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields.
 * - @Builder: Lets you automatically produce the code required to have your class be instantiable with code such as:
 * RegisterRequest.builder().firstName(firstName).lastName(lastName).email(email).password(password).role(role).build().
 * - @NoArgsConstructor: Generates a no-args constructor.
 * - @AllArgsConstructor: Generates a constructor with 1 parameter for each field in your class. Fields are initialized in the order they are declared.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	/**
	 * The first name of the user trying to register.
	 */
	private String firstName;

	/**
	 * The last name of the user trying to register.
	 */
	private String lastName;

	/**
	 * The email of the user trying to register.
	 */
	private String email;

	/**
	 * The password of the user trying to register.
	 */
	private String password;

	/**
	 * The role of the user trying to register.
	 */
	private Role role;
}

