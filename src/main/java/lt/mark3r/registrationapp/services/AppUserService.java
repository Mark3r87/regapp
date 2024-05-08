/**
 * File: AppUserService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * <p>
 * The AppUserService interface defines the contract for services related to application users.
 * It declares a method for saving an application user.
 * <p>
 * Methods:
 * - saveAppUser(AppUserDTO appUserDTO): Takes an AppUserDTO object and saves the application user. Returns the
 * saved AppUserDTO.
 */


package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.AppUserDTO;

public interface AppUserService {
	AppUserDTO saveAppUser(AppUserDTO appUserDTO);
}
