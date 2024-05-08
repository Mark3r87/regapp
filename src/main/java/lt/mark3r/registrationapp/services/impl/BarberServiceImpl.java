/**
 * File: BarberServiceImpl.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 */

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


/**
 * The BarberServiceImpl class is an implementation of the BarberService interface.
 * It provides the business logic for managing barbers in the application.
 * <p>
 * Annotations:
 * - @Service: Indicates that the class is a service and should be automatically detected by Spring's component scanning.
 */
@Service
public class BarberServiceImpl implements BarberService {
	@Autowired
	private BarberRepository barberRepository;

	/**
	 * Registers a new barber in the system.
	 */
	@Override
	public BarberDTO registerBarber(BarberDTO barberDTO) {
		Barber barber = BarberMapper.toEntity(barberDTO);
		Barber savedBarber = barberRepository.save(barber);
		return BarberMapper.toDTO(savedBarber);
	}

	/**
	 * Retrieves a barber by its ID.
	 */
	@Override
	public Optional<BarberDTO> getBarber(Long id) {
		return barberRepository.findById(id).map(BarberMapper::toDTO);
	}

	/**
	 * Retrieves all barbers.
	 */
	@Override
	public List<BarberDTO> getAllBarbers() {
		return barberRepository.findAll().stream().map(BarberMapper::toDTO).collect(Collectors.toList());
	}

	/**
	 * Updates an existing barber.
	 */
	@Override
	public Optional<BarberDTO> updateBarber(Long id, BarberDTO barberDTO) {
		return barberRepository.findById(id).map(existingBarber -> {

			existingBarber.setName(barberDTO.getName());
			existingBarber.setLocation(barberDTO.getLocation());
			existingBarber.setSpecialties(barberDTO.getSpecialties());
			existingBarber.setContactInformation(barberDTO.getContactInformation());
			existingBarber.setRating(barberDTO.getRating());
			existingBarber.setServicesOffered(barberDTO.getServicesOffered());
			existingBarber.setHasBeenRated(barberDTO.isHasBeenRated());

			Barber savedBarber = barberRepository.save(existingBarber);

			return BarberMapper.toDTO(savedBarber);
		});
	}

	/**
	 * Deletes a barber by its ID.
	 */
	@Override
	public void deleteBarber(Long id) {
		barberRepository.deleteById(id);
	}
}
