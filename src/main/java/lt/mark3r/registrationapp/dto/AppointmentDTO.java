package lt.mark3r.registrationapp.dto;

import lt.mark3r.registrationapp.model.TypeOfService;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
	private Long id;
	private LocalDate date;
	private LocalTime time;
	private Long barberId;
	private TypeOfService service;
	private Long guestClientId;

	public AppointmentDTO() {
	}

	public AppointmentDTO(Long id, LocalDate date, LocalTime time, Long barberId, TypeOfService service, Long guestClientId) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.barberId = barberId;
		this.service = service;
		this.guestClientId = guestClientId;
	}

	// Getters and Setters

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
