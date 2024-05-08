/**
 * File: AppUserMapper.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AppUserMapper class, which is responsible for mapping between AppUser and AppUserDTO objects.
 */

package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.AppUserDTO;
import lt.mark3r.registrationapp.model.AppUser;
import lt.mark3r.registrationapp.model.Role;
import org.springframework.stereotype.Component;


/**
 * The AppUserMapper class is responsible for mapping between AppUser and AppUserDTO objects.
 * It provides methods to map from an AppUser entity to an AppUserDTO, and vice versa.
 * <p>
 * Annotations:
 * - @Component: Indicates that an annotated class is a "component". Such classes are considered as candidates for
 * auto-detection when using annotation-based configuration and classpath scanning.
 */
@Component
public class AppUserMapper {

	/**
	 * Maps an AppUser entity to an AppUserDTO.
	 */
	public AppUserDTO toDTO(AppUser appUser) {
		AppUserDTO dto = new AppUserDTO();
		dto.setId(Long.valueOf(appUser.getId()));
		dto.setUsername(appUser.getEmail());
		dto.setRole(appUser.getRole().name());
		dto.setBarberId(appUser.getBarber().getId());
		return dto;
	}

	/**
	 * Maps an AppUserDTO to an AppUser entity.
	 */
	public AppUser toEntity(AppUserDTO dto) {
		AppUser appUser = new AppUser();
		appUser.setId(dto.getId().intValue());
		appUser.setEmail(dto.getUsername());
		appUser.setRole(Role.valueOf(dto.getRole()));
		return appUser;
	}
}