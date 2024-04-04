package lt.mark3r.registrationapp.dto;

import lt.mark3r.registrationapp.model.TypeOfService;

public class BarberServDTO {
	private Long id;
	private Long barberId;
	private TypeOfService service;
	private int defaultDurationInMinutes;

	public BarberServDTO() {

	}

	public BarberServDTO(Long id, Long barberId, TypeOfService service, int defaultDurationInMinutes) {
		this.id = id;
		this.barberId = barberId;
		this.service = service;
		this.defaultDurationInMinutes = defaultDurationInMinutes;
	}

	// Getters and Setters

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
