package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.mapper.BarberServMapper;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.BarberServ;
import lt.mark3r.registrationapp.model.TypeOfService;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.BarberServRepository;
import lt.mark3r.registrationapp.services.BarberServService;
import org.apache.velocity.exception.ResourceNotFoundException;
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
	public BarberServDTO createBarberService(Long barberId, BarberServDTO barberServDTO) {
		Barber barber = barberRepository.findById(barberId).orElse(null);
		if (barber == null) {
			throw new RuntimeException("Barber not found");
		}
		BarberServ barberServ = BarberServMapper.toEntity(barberServDTO, barber);
		BarberServ savedBarberServ = barberServRepository.save(barberServ);
		return BarberServMapper.toDTO(savedBarberServ);
	}

	@Override
	public Optional<BarberServDTO> getBarberService(Long barberId, Long id) {
		return barberServRepository.findByIdAndBarberId(id, barberId).map(BarberServMapper::toDTO);
	}

	@Override
	public List<BarberServDTO> getAllBarberServices(Long barberId) {
		return barberServRepository.findAllByBarberId(barberId).stream().map(BarberServMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<BarberServDTO> updateBarberService(Long barberId, Long id, BarberServDTO barberServDTO) {
		if (barberServRepository.existsById(id)) {
			Barber barber = barberRepository.findById(barberId).orElse(null);
			BarberServ existingBarberServ = barberServRepository.findById(id).orElse(null);
			BarberServ updatedBarberServ = BarberServMapper.toEntity(barberServDTO, barber, existingBarberServ);
			BarberServ savedBarberServ = barberServRepository.save(updatedBarberServ);
			return Optional.of(BarberServMapper.toDTO(savedBarberServ));
		}
		return Optional.empty();
	}


	@Override
	public void deleteBarberService(Long barberId, Long id) {
		if (barberServRepository.existsByIdAndBarberId(id, barberId)) {
			barberServRepository.deleteById(id);
		} else {
			throw new RuntimeException("Barber service not found");
		}
	}

	@Override
	public BarberServDTO addServiceToBarber(Long barberId, TypeOfService typeOfService) {
		// Fetch the barber from the database
		Barber barber = barberRepository.findById(barberId)
				.orElseThrow(() -> new ResourceNotFoundException("Barber not found with id " + barberId));

		// Create a new BarberServ entity based on the TypeOfService and save it to the database
		BarberServ barberServ = new BarberServ();
		barberServ.setBarber(barber);
		barberServ.setService(typeOfService);
		// Assuming a default duration for each service type
		barberServ.setDefaultDurationInMinutes(30); // adjust this as needed
		BarberServ savedBarberServ = barberServRepository.save(barberServ);

		// Return the created BarberServDTO
		return BarberServMapper.toDTO(savedBarberServ); // assuming you have a BarberServMapper
	}

	@Override
	public void deleteServiceFromBarber(Long barberId, Long id) {
		// Fetch the barber and the service from the database
		Barber barber = barberRepository.findById(barberId)
				.orElseThrow(() -> new ResourceNotFoundException("Barber not found with id " + barberId));
		BarberServ barberServ = barberServRepository.findByIdAndBarber(id, barber)
				.orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + id + " for barber with id " + barberId));

		// Delete the service from the barber
		barberServRepository.delete(barberServ);
	}

}
