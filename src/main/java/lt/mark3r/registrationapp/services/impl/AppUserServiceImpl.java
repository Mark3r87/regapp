package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.AppUserDTO;
import lt.mark3r.registrationapp.mapper.AppUserMapper;
import lt.mark3r.registrationapp.model.AppUser;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.UserRepository;
import lt.mark3r.registrationapp.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private UserRepository appUserRepository;

	@Autowired
	private BarberRepository barberRepository;

	@Autowired
	private AppUserMapper appUserMapper;

	@Override
	public AppUserDTO saveAppUser(AppUserDTO appUserDTO) {
		AppUser appUser = appUserMapper.toEntity(appUserDTO);

		Barber barber = barberRepository.findById(appUserDTO.getBarberId())
				.orElseThrow(() -> new RuntimeException("Barber not found"));
		appUser.setBarber(barber);
		appUser = appUserRepository.save(appUser);

		return appUserMapper.toDTO(appUser);
	}
}
