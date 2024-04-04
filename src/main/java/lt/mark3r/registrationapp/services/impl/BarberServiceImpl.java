package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.mapper.BarberMapper;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarberServiceImpl implements BarberService {
	@Autowired
	private BarberRepository barberRepository;

	@Override
	public BarberDTO registerBarber(BarberDTO barberDTO) {
		// Use BarberMapper for DTO to entity conversion, save it, and convert back to DTO
		Barber barber = BarberMapper.toEntity(barberDTO);
		Barber savedBarber = barberRepository.save(barber);
		return BarberMapper.toDTO(savedBarber);
	}

	@Override
	public Optional<BarberDTO> getBarber(Long id) {
		return barberRepository.findById(id).map(BarberMapper::toDTO);
	}

	@Override
	public List<BarberDTO> getAllBarbers() {
		return barberRepository.findAll().stream().map(BarberMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<BarberDTO> updateBarber(Long id, BarberDTO barberDTO) {
		if (barberRepository.existsById(id)) {
			barberDTO.setId(id);
			Barber updatedBarber = BarberMapper.toEntity(barberDTO);
			Barber savedBarber = barberRepository.save(updatedBarber);
			return Optional.of(BarberMapper.toDTO(savedBarber));
		}
		return Optional.empty();
	}

	@Override
	public void deleteBarber(Long id) {
		barberRepository.deleteById(id);
	}
}
