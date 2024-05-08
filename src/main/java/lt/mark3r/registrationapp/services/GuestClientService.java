/**
 * File: GuestClientService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * <p>
 * The GuestClientService interface defines the contract for services related to guest clients.
 * It declares methods for registering, retrieving, updating, and deleting guest clients.
 * Methods:
 * - registerGuestClient(GuestClientDTO guestClientDTO): Takes a GuestClientDTO object and registers a guest client.
 * Returns the registered GuestClientDTO.
 * - getGuestClient(Long id): Retrieves a guest client by its ID. Returns an Optional that contains the GuestClientDTO
 * if found, or is empty if not found.
 * - getAllGuestClients(): Retrieves all guest clients. Returns a List of GuestClientDTO objects.
 * - updateGuestClient(Long id, GuestClientDTO guestClientDTO): Updates a guest client. Takes a GuestClientDTO object
 * with the updated details and the ID of the guest client to be updated. Returns an Optional that contains the updated
 * GuestClientDTO if the update was successful, or is empty if not found.
 * - deleteGuestClient(Long id): Deletes a guest client by its ID. Does not return a value.
 */

package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.GuestClientDTO;

import java.util.List;
import java.util.Optional;

public interface GuestClientService {
	GuestClientDTO registerGuestClient(GuestClientDTO guestClientDTO);

	Optional<GuestClientDTO> getGuestClient(Long id);

	List<GuestClientDTO> getAllGuestClients();

	Optional<GuestClientDTO> updateGuestClient(Long id, GuestClientDTO guestClientDTO);

	void deleteGuestClient(Long id);
}
