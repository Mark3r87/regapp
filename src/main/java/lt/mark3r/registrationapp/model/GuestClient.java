package lt.mark3r.registrationapp.model;


import jakarta.persistence.*;

@Entity
public class GuestClient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String phoneNumber;
	private String name;

	@OneToOne
	private Appointment appointment;


	public GuestClient() {
	}

	public GuestClient(Long id, String phoneNumber, String name, Appointment appointment) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.appointment = appointment;
	}

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

	public void setName(String email) {
		this.name = email;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "GuestClient [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + name + ", appointment="
				+ appointment + "]";
	}
}
