package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.AppUserDTO;
import lt.mark3r.registrationapp.model.AppUser;
import lt.mark3r.registrationapp.model.Role;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

	public AppUserDTO toDTO(AppUser appUser) {
		AppUserDTO dto = new AppUserDTO();
		dto.setId(appUser.getId());
		dto.setUsername(appUser.getUsername());
		dto.setRole(appUser.getRole().name());
		dto.setBarberId(appUser.getBarber().getId());
		return dto;
	}

	public AppUser toEntity(AppUserDTO dto) {
		AppUser appUser = new AppUser();
		appUser.setId(dto.getId());
		appUser.setUsername(dto.getUsername());
		appUser.setRole(Role.valueOf(dto.getRole()));
		return appUser;
	}
}