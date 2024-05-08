/**
 * File: BarberServController.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the BarberServController class, which is responsible for handling HTTP requests related
 * to barber services.
 */

package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.model.TypeOfService;
import lt.mark3r.registrationapp.services.BarberServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The BarberServController class is responsible for handling HTTP requests related to barber services.
 * It uses the BarberServService to perform operations and returns the results as HTTP responses.
 * <p>
 * Annotations:
 * - @CrossOrigin: Allows cross-origin requests from "http://localhost:3000".
 * - @RestController: Indicates that the class is a controller and the return values of its methods should be bound
 * to the web response body.
 * - @RequestMapping: Maps all HTTP operations by default to /api/barbers/{barberId}/barberservs.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/barbers/{barberId}/barberservs")
public class BarberServController {

	@Autowired
	private BarberServService barberServService;

	/**
	 * Handles POST requests to create a barber service.
	 * It uses the BarberServService to create the barber service and returns the created barber service as a response.
	 * This endpoint can only be accessed by users with the 'ROLE_BARBER' or 'ROLE_ADMIN' authority.
	 *
	 * @param barberId      The ID of the barber for whom the service is to be created.
	 * @param barberServDTO The barber service to be created.
	 * @return The created barber service and HTTP status CREATED.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_BARBER', 'ROLE_ADMIN')")
	public ResponseEntity<BarberServDTO> createBarberService(@PathVariable Long barberId,
	                                                         @RequestBody BarberServDTO barberServDTO) {
		BarberServDTO createdBarberServ = barberServService.createBarberService(barberId, barberServDTO);
		return new ResponseEntity<>(createdBarberServ, HttpStatus.CREATED);
	}

	/**
	 * Handles GET requests to retrieve a barber service by its ID.
	 * It uses the BarberServService to get the barber service and returns it as a response if found.
	 *
	 * @param barberId The ID of the barber for whom the service is to be retrieved.
	 * @param id       The ID of the barber service to be retrieved.
	 * @return The retrieved barber service and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BarberServDTO> getBarberService(@PathVariable Long barberId, @PathVariable Long id) {
		Optional<BarberServDTO> barberServDTO = barberServService.getBarberService(barberId, id);
		return barberServDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles GET requests to retrieve all barber services for a specific barber.
	 * It uses the BarberServService to get all barber services and returns them as a response if any are found.
	 *
	 * @param barberId The ID of the barber for whom the services are to be retrieved.
	 * @return All barber services for the specified barber and HTTP status OK, or HTTP status NO CONTENT if no barber
	 * services are found.
	 */
	@GetMapping
	public ResponseEntity<List<BarberServDTO>> getAllBarberServices(@PathVariable Long barberId) {
		List<BarberServDTO> dtos = barberServService.getAllBarberServices(barberId);
		if (!dtos.isEmpty()) {
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Handles PUT requests to update a barber service.
	 * It uses the BarberServService to update the barber service and returns the updated barber service as a response
	 * if found.
	 * This endpoint can be accessed by users with either the 'ROLE_BARBER' or 'ROLE_ADMIN' authority.
	 *
	 * @param barberId      The ID of the barber for whom the service is to be updated.
	 * @param id            The ID of the barber service to be updated.
	 * @param barberServDTO The updated barber service.
	 * @return The updated barber service and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_BARBER', 'ROLE_ADMIN')")
	public ResponseEntity<BarberServDTO> updateBarberService(@PathVariable Long barberId, @PathVariable Long id,
	                                                         @RequestBody BarberServDTO barberServDTO) {
		Optional<BarberServDTO> updatedBarberServ = barberServService
				.updateBarberService(barberId, id, barberServDTO);
		return updatedBarberServ.map(updatedDto -> new ResponseEntity<>(updatedDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles DELETE requests to delete a barber service.
	 * It uses the BarberServService to delete the barber service and returns HTTP status NO CONTENT if successful.
	 * This endpoint can only be accessed by users with the 'ROLE_BARBER' or 'ROLE_ADMIN' authority.
	 *
	 * @param barberId The ID of the barber for whom the service is to be deleted.
	 * @param id       The ID of the barber service to be deleted.
	 * @return HTTP status NO CONTENT if successful, or HTTP status NOT FOUND if not found.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_BARBER', 'ROLE_ADMIN')")
	public ResponseEntity<Void> deleteBarberService(@PathVariable Long barberId, @PathVariable Long id) {
		barberServService.deleteBarberService(barberId, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Handles POST requests to add a service to a barber.
	 * It uses the BarberServService to add the service to the barber and returns the created barber service as a response.
	 *
	 * @param barberId      The ID of the barber to whom the service is to be added.
	 * @param typeOfService The type of service to be added to the barber.
	 * @return The created barber service and HTTP status CREATED.
	 */
	@PostMapping("/addService")
	public ResponseEntity<BarberServDTO> addServiceToBarber(@PathVariable Long barberId,
	                                                        @RequestBody TypeOfService typeOfService) {
		BarberServDTO createdBarberServ = barberServService.addServiceToBarber(barberId, typeOfService);
		return new ResponseEntity<>(createdBarberServ, HttpStatus.CREATED);
	}
}
