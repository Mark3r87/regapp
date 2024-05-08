/**
 * File: BarberServService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * <p>
 * The BarberServService interface defines the contract for services related to barber services.
 * It declares methods for creating, retrieving, updating, and deleting barber services, as well as for adding
 * and removing services from a barber.
 * <p>
 * Methods:
 * - createBarberService(Long barberId, BarberServDTO barberServDTO): Takes a barber ID and a BarberServDTO object
 * and creates a barber service. Returns the created BarberServDTO.
 * <p>
 * - getBarberService(Long barberId, Long id): Retrieves a barber service by its ID and the barber's ID. Returns an
 * Optional that contains the BarberServDTO if found, or is empty if not found.
 * <p>
 * - getAllBarberServices(Long barberId): Retrieves all barber services for a specific barber by the barber's ID.
 * Returns a List of BarberServDTO objects.
 * <p>
 * - updateBarberService(Long barberId, Long id, BarberServDTO barberServDTO): Updates a barber service. Takes a
 * barber ID, a service ID, and a BarberServDTO object with the updated details. Returns an Optional that contains the
 * updated BarberServDTO if the update was successful, or is empty if not found.
 * <p>
 * - deleteBarberService(Long barberId, Long id): Deletes a barber service by its ID and the barber's ID. Does not
 * return a value.
 * <p>
 * - addServiceToBarber(Long barberId, TypeOfService typeOfService): Adds a service to a barber. Takes a barber ID
 * and a TypeOfService object. Returns the updated BarberServDTO.
 * <p>
 * - deleteServiceFromBarber(Long barberId, Long id): Removes a service from a barber by the service's ID and the
 * barber's ID. Does not return a value.
 */


package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.model.TypeOfService;

import java.util.List;
import java.util.Optional;

public interface BarberServService {
	BarberServDTO createBarberService(Long barberId, BarberServDTO barberServDTO);

	Optional<BarberServDTO> getBarberService(Long barberId, Long id);

	List<BarberServDTO> getAllBarberServices(Long barberId);

	Optional<BarberServDTO> updateBarberService(Long barberId, Long id, BarberServDTO barberServDTO);

	void deleteBarberService(Long barberId, Long id);

	BarberServDTO addServiceToBarber(Long barberId, TypeOfService typeOfService);

	void deleteServiceFromBarber(Long barberId, Long id);
}

