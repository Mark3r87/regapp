package lt.mark3r.registrationapp.model;

import jakarta.persistence.*;

@Entity
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
	private Barber barber;

	public AppUser() {
	}

	public AppUser(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.barber = new Barber();
	}

	public Barber getBarber() {
		return barber;
	}

	public void setBarber(Barber barber) {
		this.barber = barber;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
