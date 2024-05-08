/**
 * File: BarberController.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the BarberController class, which is responsible for handling HTTP requests related to barbers.
 */

package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The BarberController class is responsible for handling HTTP requests related to barbers.
 * It uses the BarberService to perform operations and returns the results as HTTP responses.
 * <p>
 * Annotations:
 * - @CrossOrigin: Allows cross-origin requests from all origins.
 * - @RestController: Indicates that the class is a controller and the return values of its methods should be bound to
 * the web response body.
 * - @RequestMapping: Maps all HTTP operations by default to /api/barbers.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/barbers")
public class BarberController {

	@Autowired
	private BarberService barberService;

	/**
	 * Handles POST requests to register a barber.
	 * It uses the BarberService to register the barber and returns the registered barber as a response.
	 * This endpoint can only be accessed by users with the 'ROLE_ADMIN' authority.
	 *
	 * @param barberDTO The barber to be registered.
	 * @return The registered barber and HTTP status CREATED.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<BarberDTO> registerBarber(@RequestBody BarberDTO barberDTO) {
		BarberDTO registeredBarberDTO = barberService.registerBarber(barberDTO);
		return new ResponseEntity<>(registeredBarberDTO, HttpStatus.CREATED);
	}

	/**
	 * Handles GET requests to retrieve a barber by its ID.
	 * It uses the BarberService to get the barber and returns it as a response if found.
	 *
	 * @param id The ID of the barber to be retrieved.
	 * @return The retrieved barber and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BarberDTO> getBarber(@PathVariable Long id) {
		return barberService.getBarber(id)
				.map(barberDTO -> new ResponseEntity<>(barberDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles GET requests to retrieve all barbers.
	 * It uses the BarberService to get all barbers and returns them as a response if any are found.
	 *
	 * @return All barbers and HTTP status OK, or HTTP status NO CONTENT if no barbers are found.
	 */
	@GetMapping
	public ResponseEntity<List<BarberDTO>> getAllBarbers() {
		List<BarberDTO> barbers = barberService.getAllBarbers();
		if (!barbers.isEmpty()) {
			return new ResponseEntity<>(barbers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Handles PUT requests to update a barber.
	 * It uses the BarberService to update the barber and returns the updated barber as a response if found.
	 * This endpoint can be accessed by users with either the 'ROLE_ADMIN' or 'ROLE_BARBER' authority.
	 *
	 * @param id        The ID of the barber to be updated.
	 * @param barberDTO The updated barber.
	 * @return The updated barber and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_BARBER')")
	public ResponseEntity<BarberDTO> updateBarber(@PathVariable Long id, @RequestBody BarberDTO barberDTO) {
		Optional<BarberDTO> updatedBarber = barberService.updateBarber(id, barberDTO);
		return updatedBarber
				.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles DELETE requests to delete a barber.
	 * It uses the BarberService to delete the barber and returns HTTP status NO CONTENT if successful.
	 * This endpoint can only be accessed by users with the 'ROLE_ADMIN' authority.
	 *
	 * @param id The ID of the barber to be deleted.
	 * @return HTTP status NO CONTENT if successful, or HTTP status NOT FOUND if not found.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Void> deleteBarber(@PathVariable Long id) {
		Optional<BarberDTO> existingBarber = barberService.getBarber(id);
		if (existingBarber.isPresent()) {
			barberService.deleteBarber(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
