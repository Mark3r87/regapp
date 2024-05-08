/**
 * File: AppointmentDTO.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AppointmentDTO class, which is a Data Transfer Object (DTO) for appointments.
 */


package lt.mark3r.registrationapp.dto;

import lt.mark3r.registrationapp.model.TypeOfService;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The AppointmentDTO class is a Data Transfer Object (DTO) for appointments.
 * It contains information about an appointment, including its ID, date, time, barber ID, type of service, and guest
 * client ID.
 */

public class AppointmentDTO {
	private Long id;
	private LocalDate date;
	private LocalTime time;
	private Long barberId;
	private TypeOfService service;
	private Long guestClientId;

	/**
	 * Default constructor.
	 */
	public AppointmentDTO() {
	}

	/**
	 * Constructor with parameters.
	 *
	 * @param id            The ID of the appointment.
	 * @param date          The date of the appointment.
	 * @param time          The time of the appointment.
	 * @param barberId      The ID of the barber for the appointment.
	 * @param service       The type of service for the appointment.
	 * @param guestClientId The ID of the guest client for the appointment.
	 */
	public AppointmentDTO(Long id, LocalDate date, LocalTime time, Long barberId, TypeOfService service, Long guestClientId) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.barberId = barberId;
		this.service = service;
		this.guestClientId = guestClientId;
	}

	/**
	 * Getters and setters, toString;
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
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

	public Long getGuestClientId() {
		return guestClientId;
	}

	public void setGuestClientId(Long guestClientId) {
		this.guestClientId = guestClientId;
	}

	@Override
	public String toString() {
		return "AppointmentDTO{" +
				"id=" + id +
				", date=" + date +
				", time=" + time +
				", barberId=" + barberId +
				", service=" + service +
				", guestClientId=" + guestClientId +
				'}';
	}
}
