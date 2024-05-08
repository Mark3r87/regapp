/**
 * File: Appointment.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the Appointment class, which represents an appointment in the system.
 * Each appointment is associated with a specific Barber, a type of service, and a GuestClient.
 */

package lt.mark3r.registrationapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private LocalTime time;
	@ManyToOne
	private Barber barber;
	@Enumerated(EnumType.STRING)
	private TypeOfService service;
	@ManyToOne
	private GuestClient guestClient;

	public Appointment() {
	}

	public Appointment(Long id, LocalDate date, LocalTime time, Barber barber, TypeOfService service, GuestClient guestClient) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.barber = barber;
		this.service = service;
		this.guestClient = guestClient;
	}

	/**
	 * Getters and setters, toString
	 */
	public String getTimeSlot() {
		return this.time.toString();
	}

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

	public Barber getBarber() {
		return barber;
	}

	public void setBarber(Barber barber) {
		this.barber = barber;
	}

	public TypeOfService getService() {
		return service;
	}

	public void setService(TypeOfService service) {
		this.service = service;
	}

	public GuestClient getGuestClient() {
		return guestClient;
	}

	public void setGuestClient(GuestClient guestClient) {
		this.guestClient = guestClient;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", date=" + date +
				", time=" + time +
				", barber=" + barber +
				", service=" + service +
				", guestClient=" + guestClient +
				'}';
	}
}
