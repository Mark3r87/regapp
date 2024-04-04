package lt.mark3r.registrationapp.dto;

public class GuestClientDTO {
	private Long id;
	private String phoneNumber;
	private String email;
	private Long appointmentId;

	// Constructors
	public GuestClientDTO() {
		super();
	}

	public GuestClientDTO(Long id, String phoneNumber, String email, Long appointmentId) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.appointmentId = appointmentId;
	}

	// Getters and Setters

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

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	@Override
	public String toString() {
		return "GuestClientDTO [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", appointmentId="
				+ appointmentId + "]";
	}
}