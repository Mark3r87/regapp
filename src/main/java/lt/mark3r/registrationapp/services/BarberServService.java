package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.BarberServDTO;

import java.util.List;
import java.util.Optional;

public interface BarberServService {
	BarberServDTO createBarberService(BarberServDTO barberServDTO);

	Optional<BarberServDTO> getBarberService(Long id);

	List<BarberServDTO> getAllBarberServices();

	Optional<BarberServDTO> updateBarberService(Long id, BarberServDTO barberServDTO);

	void deleteBarberService(Long id);
}
