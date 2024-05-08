/**
 * File: Role.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the Role enum, which represents the role of a user in the system.
 */

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
