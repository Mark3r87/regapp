/**
 * File: BarberServDTO.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the BarberServDTO class, which is a Data Transfer Object (DTO) for barber services.
 */

package lt.mark3r.registrationapp.dto;

import lt.mark3r.registrationapp.model.TypeOfService;

/**
 * The BarberServDTO class is a Data Transfer Object (DTO) for barber services.
 * It contains information about a barber service, including its ID, barber ID, type of service, and default
 * duration in minutes.
 */
public class BarberServDTO {
	private Long id;
	private Long barberId;
	private TypeOfService service;
	private int defaultDurationInMinutes;

	/**
	 * Default constructor.
	 */
	public BarberServDTO() {

	}

	/**
	 * Constructor with parameters.
	 *
	 * @param id                       The ID of the barber service.
	 * @param barberId                 The ID of the barber providing the service.
	 * @param service                  The type of service provided.
	 * @param defaultDurationInMinutes The default duration of the service in minutes.
	 */
	public BarberServDTO(Long id, Long barberId, TypeOfService service, int defaultDurationInMinutes) {
		this.id = id;
		this.barberId = barberId;
		this.service = service;
		this.defaultDurationInMinutes = defaultDurationInMinutes;
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

	public Long getBarberId() {
		return barberId;
	}

	public void setBarberId(Long barberId) {
		this.barberId = barberId;
	}

	public TypeOfService getService() {
		return service;
	}

	public void setService(TypeOfService service) {
		this.service = service;
	}

	public int getDefaultDurationInMinutes() {
		return defaultDurationInMinutes;
	}

	public void setDefaultDurationInMinutes(int defaultDurationInMinutes) {
		this.defaultDurationInMinutes = defaultDurationInMinutes;
	}

	@Override
	public String toString() {
		return "BarberServDTO{" +
				"id=" + id +
				", barberId=" + barberId +
				", service=" + service +
				", defaultDurationInMinutes=" + defaultDurationInMinutes +
				'}';
	}
}
