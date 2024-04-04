package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.BarberDTO;

import java.util.List;
import java.util.Optional;

public interface BarberService {
	BarberDTO registerBarber(BarberDTO barberDTO);

	Optional<BarberDTO> getBarber(Long id);

	List<BarberDTO> getAllBarbers();

	Optional<BarberDTO> updateBarber(Long id, BarberDTO barberDTO);

	void deleteBarber(Long id);
}

