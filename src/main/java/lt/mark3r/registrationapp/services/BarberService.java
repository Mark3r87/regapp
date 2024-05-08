/**
 * File: BarberService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * <p>
 * The BarberService interface defines the contract for services related to barbers.
 * It declares methods for registering, retrieving, updating, and deleting barbers.
 * <p>
 * Methods:
 * - registerBarber(BarberDTO barberDTO): Takes a BarberDTO object and registers a barber. Returns the registered
 * BarberDTO.
 * <p>
 * - getBarber(Long id): Retrieves a barber by its ID. Returns an Optional that contains the BarberDTO if found, or
 * is empty if not found.
 * <p>
 * - getAllBarbers(): Retrieves all barbers. Returns a List of BarberDTO objects.
 * <p>
 * - updateBarber(Long id, BarberDTO barberDTO): Updates a barber. Takes a BarberDTO object with the updated details
 * and the ID of the barber to be updated. Returns an Optional that contains the updated BarberDTO if the update was
 * successful, or is empty if not found.
 * <p>
 * - deleteBarber(Long id): Deletes a barber by its ID. Does not return a value.
 */


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

