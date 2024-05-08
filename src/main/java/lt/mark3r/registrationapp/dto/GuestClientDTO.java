/**
 * File: GuestClientDTO.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the GuestClientDTO class, which is a Data Transfer Object (DTO) for guest clients.
 */


package lt.mark3r.registrationapp.dto;

/**
 * The GuestClientDTO class is a Data Transfer Object (DTO) for guest clients.
 * It contains information about a guest client, including its ID, phone number, name, and appointment ID.
 */
public class GuestClientDTO {
	private Long id;
	private String phoneNumber;
	private String name;
	private Long appointmentId;


	/**
	 * Default constructor.
	 */
	public GuestClientDTO() {
		super();
	}

	/**
	 * Constructor with parameters.
	 *
	 * @param id            The ID of the guest client.
	 * @param phoneNumber   The phone number of the guest client.
	 * @param name          The name of the guest client.
	 * @param appointmentId The ID of the appointment associated with the guest client.
	 */
	public GuestClientDTO(Long id, String phoneNumber, String name, Long appointmentId) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.appointmentId = appointmentId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	@Override
	public String toString() {
		return "GuestClientDTO [id=" + id + ", phoneNumber=" + phoneNumber + ", name=" + name + ", appointmentId="
				+ appointmentId + "]";
	}
}