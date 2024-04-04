package lt.mark3r.registrationapp.model;

import jakarta.persistence.*;

@Entity
public class BarberServ {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Barber barber;

	@Enumerated(EnumType.STRING)
	private TypeOfService service;

	private int defaultDurationInMinutes;

	public BarberServ() {
	}

	public BarberServ(Barber barber, TypeOfService service, int defaultDurationInMinutes) {
		this.barber = barber;
		this.service = service;
		this.defaultDurationInMinutes = defaultDurationInMinutes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getDefaultDurationInMinutes() {
		return defaultDurationInMinutes;
	}

	public void setDefaultDurationInMinutes(int defaultDurationInMinutes) {
		this.defaultDurationInMinutes = defaultDurationInMinutes;
	}

	@Override
	public String toString() {
		return "BarberServ{" +
				"id=" + id +
				", barber=" + barber +
				", service=" + service +
				", defaultDurationInMinutes=" + defaultDurationInMinutes +
				'}';
	}
}