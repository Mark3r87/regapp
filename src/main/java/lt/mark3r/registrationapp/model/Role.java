package lt.mark3r.registrationapp.model;

public enum Role {
	ADMIN("ROLE_ADMIN"),
	BARBER("ROLE_BARBER");

	private final String permission;

	Role(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
