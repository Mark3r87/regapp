package lt.mark3r.registrationapp.dto;

public class AppUserDTO {
	private Long id;
	private String username;
	private String role;
	private Long barberId;

	public AppUserDTO() {
	}

	public AppUserDTO(Long id, String username, String role, Long barberId) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.barberId = barberId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getBarberId() {
		return barberId;
	}

	public void setBarberId(Long barberId) {
		this.barberId = barberId;
	}
}


