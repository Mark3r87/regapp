/**
 * File: GuestClientServiceImpl.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 */

package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.mapper.GuestClientMapper;
import lt.mark3r.registrationapp.model.GuestClient;
import lt.mark3r.registrationapp.repository.GuestClientRepository;
import lt.mark3r.registrationapp.services.GuestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The GuestClientServiceImpl class is an implementation of the GuestClientService interface.
 * It provides the business logic for managing guest clients in the application.
 * <p>
 * Annotations:
 * - @Service: Indicates that the class is a service and should be automatically detected by Spring's component
 * scanning.
 */
@Service
public class GuestClientServiceImpl implements GuestClientService {
	@Autowired
	private GuestClientRepository guestClientRepository;

	/**
	 * Registers a new guest client in the system.
	 */
	@Override
	public GuestClientDTO registerGuestClient(GuestClientDTO guestClientDTO) {
		GuestClient guestClient = GuestClientMapper.toEntity(guestClientDTO);
		GuestClient savedGuestClient = guestClientRepository.save(guestClient);
		return GuestClientMapper.toDTO(savedGuestClient);
	}

	/**
	 * Retrieves a guest client by its ID.
	 */
	@Override
	public Optional<GuestClientDTO> getGuestClient(Long id) {
		return guestClientRepository.findById(id).map(GuestClientMapper::toDTO);
	}

	/**
	 * Retrieves all guest clients.
	 */
	@Override
	public List<GuestClientDTO> getAllGuestClients() {
		return guestClientRepository.findAll()
				.stream()
				.map(GuestClientMapper::toDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Updates an existing guest client.
	 */
	@Override
	public Optional<GuestClientDTO> updateGuestClient(Long id, GuestClientDTO guestClientDTO) {
		if (guestClientRepository.existsById(id)) {
			guestClientDTO.setId(id);
			GuestClient updatedGuestClient = GuestClientMapper.toEntity(guestClientDTO);
			GuestClient savedGuestClient = guestClientRepository.save(updatedGuestClient);
			return Optional.of(GuestClientMapper.toDTO(savedGuestClient));
		}
		return Optional.empty();
	}

	/**
	 * Deletes a guest client by its ID.
	 */
	@Override
	public void deleteGuestClient(Long id) {
		guestClientRepository.deleteById(id);
	}
}
