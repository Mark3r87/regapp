/**
 * File: GuestClientController.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the GuestClientController class, which is responsible for handling HTTP requests related to
 * guest clients.
 */

package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.services.GuestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The GuestClientController class is responsible for handling HTTP requests related to guest clients.
 * It uses the GuestClientService to perform operations and returns the results as HTTP responses.
 * <p>
 * Annotations:
 * - @CrossOrigin: Allows cross-origin requests from "http://localhost:3000".
 * - @RestController: Indicates that the class is a controller and the return values of its methods should be bound
 * to the web response body.
 * - @RequestMapping: Maps all HTTP operations by default to /api/guestclients.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/guestclients")
public class GuestClientController {

	@Autowired
	private GuestClientService guestClientService;

	/**
	 * Handles POST requests to register a guest client.
	 * It uses the GuestClientService to register the guest client and returns the registered guest client as a
	 * response.
	 *
	 * @param guestClientDTO The guest client to be registered.
	 * @return The registered guest client and HTTP status CREATED.
	 */
	@PostMapping
	public ResponseEntity<GuestClientDTO> registerGuestClient(@RequestBody GuestClientDTO guestClientDTO) {
		GuestClientDTO registeredGuestClient = guestClientService.registerGuestClient(guestClientDTO);
		return new ResponseEntity<>(registeredGuestClient, HttpStatus.CREATED);
	}

	/**
	 * Handles GET requests to retrieve a guest client by its ID.
	 * It uses the GuestClientService to get the guest client and returns it as a response if found.
	 *
	 * @param id The ID of the guest client to be retrieved.
	 * @return The retrieved guest client and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<GuestClientDTO> getGuestClient(@PathVariable Long id) {
		Optional<GuestClientDTO> guestClientDTO = guestClientService.getGuestClient(id);
		return guestClientDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles GET requests to retrieve all guest clients.
	 * It uses the GuestClientService to get all guest clients and returns them as a response if any are found.
	 *
	 * @return All guest clients and HTTP status OK, or HTTP status NO CONTENT if no guest clients are found.
	 */
	@GetMapping
	public ResponseEntity<List<GuestClientDTO>> getAllGuestClients() {
		List<GuestClientDTO> guestClients = guestClientService.getAllGuestClients();
		if (!guestClients.isEmpty()) {
			return new ResponseEntity<>(guestClients, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Handles PUT requests to update a guest client.
	 * It uses the GuestClientService to update the guest client and returns the updated guest client as a response
	 * if found.
	 *
	 * @param id             The ID of the guest client to be updated.
	 * @param guestClientDTO The updated guest client.
	 * @return The updated guest client and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<GuestClientDTO> updateGuestClient(@PathVariable Long id,
	                                                        @RequestBody GuestClientDTO guestClientDTO) {
		Optional<GuestClientDTO> updatedGuestClient = guestClientService.updateGuestClient(id, guestClientDTO);
		return updatedGuestClient.map(updatedDto -> new ResponseEntity<>(updatedDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles DELETE requests to delete a guest client.
	 * It uses the GuestClientService to delete the guest client and returns HTTP status NO CONTENT if successful.
	 *
	 * @param id The ID of the guest client to be deleted.
	 * @return HTTP status NO CONTENT if successful, or HTTP status NOT FOUND if not found.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGuestClient(@PathVariable Long id) {
		guestClientService.deleteGuestClient(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
