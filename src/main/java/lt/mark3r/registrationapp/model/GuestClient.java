package lt.mark3r.registrationapp.model;


import jakarta.persistence.*;

@Entity
public class GuestClient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String phoneNumber;
	private String email;

	@OneToOne
	private Appointment appointment;


	public GuestClient() {
	}

	public GuestClient(Long id, String phoneNumber, String email, Appointment appointment) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "GuestClient [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", appointment="
				+ appointment + "]";
	}
}
