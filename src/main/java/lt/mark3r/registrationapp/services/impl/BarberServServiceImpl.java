package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.mapper.BarberServMapper;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.BarberServ;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.BarberServRepository;
import lt.mark3r.registrationapp.services.BarberServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarberServServiceImpl implements BarberServService {
	@Autowired
	private BarberServRepository barberServRepository;
	@Autowired
	private BarberRepository barberRepository;

	@Override
	public BarberServDTO createBarberService(BarberServDTO barberServDTO) {
		Barber barber = barberRepository.findById(barberServDTO.getBarberId()).orElse(null);
		BarberServ barberServ = BarberServMapper.toEntity(barberServDTO, barber);
		BarberServ savedBarberServ = barberServRepository.save(barberServ);
		return BarberServMapper.toDTO(savedBarberServ);
	}

	@Override
	public Optional<BarberServDTO> getBarberService(Long id) {
		return barberServRepository.findById(id).map(BarberServMapper::toDTO);
	}

	@Override
	public List<BarberServDTO> getAllBarberServices() {
		return barberServRepository.findAll().stream().map(BarberServMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<BarberServDTO> updateBarberService(Long id, BarberServDTO barberServDTO) {
		if (barberServRepository.existsById(id)) {
			barberServDTO.setId(id);
			Barber barber = barberRepository.findById(barberServDTO.getBarberId()).orElse(null);
			BarberServ updatedBarberServ = BarberServMapper.toEntity(barberServDTO, barber);
			BarberServ savedBarberServ = barberServRepository.save(updatedBarberServ);
			return Optional.of(BarberServMapper.toDTO(savedBarberServ));
		}
		return Optional.empty();
	}

	@Override
	public void deleteBarberService(Long id) {
		barberServRepository.deleteById(id);
	}
}
