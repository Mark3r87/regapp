/**
 * File: AppUserDTO.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AppUserDTO class, which is a Data Transfer Object (DTO) for application users.
 */

package lt.mark3r.registrationapp.dto;

/**
 * The AppUserDTO class is a Data Transfer Object (DTO) for application users.
 * It contains information about an application user, including its ID, username, role, and barber ID.
 */
public class AppUserDTO {
	private Long id;
	private String username;
	private String role;
	private Long barberId;

	/**
	 * Default constructor.
	 */
	public AppUserDTO() {
	}

	/**
	 * Constructor with parameters.
	 *
	 * @param id       The ID of the application user.
	 * @param username The username of the application user.
	 * @param role     The role of the application user.
	 * @param barberId The ID of the barber associated with the application user.
	 */
	public AppUserDTO(Long id, String username, String role, Long barberId) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.barberId = barberId;
	}

	/**
	 * Getters and setters, toString
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getBarberId() {
		return barberId;
	}

	public void setBarberId(Long barberId) {
		this.barberId = barberId;
	}
}


