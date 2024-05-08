/**
 * File: WorkingScheduleController.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the WorkingScheduleController class, which is responsible for handling HTTP requests related
 * to working schedules.
 */


package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.services.WorkingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The WorkingScheduleController class is responsible for handling HTTP requests related to working schedules.
 * It uses the WorkingScheduleService to perform operations and returns the results as HTTP responses.
 * <p>
 * Annotations:
 * - @CrossOrigin: Allows cross-origin requests from "http://localhost:3000".
 * - @RestController: Indicates that the class is a controller and the return values of its methods should be bound
 * to the web response body.
 * - @RequestMapping: Maps all HTTP operations by default to /api/barbers/{barberId}/workingschedules.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/barbers/{barberId}/workingschedules")
public class WorkingScheduleController {

	@Autowired
	private WorkingScheduleService workingScheduleService;

	/**
	 * Handles POST requests to create a working schedule.
	 * It uses the WorkingScheduleService to create the working schedule and returns the created working
	 * schedule as a response.
	 * This endpoint can only be accessed by users with the 'ROLE_BARBER' or 'ROLE_ADMIN' authority.
	 *
	 * @param barberId           The ID of the barber for whom the working schedule is to be created.
	 * @param workingScheduleDTO The working schedule to be created.
	 * @return The created working schedule and HTTP status CREATED.
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_BARBER', 'ROLE_ADMIN')")
	public ResponseEntity<WorkingScheduleDTO> createWorkingSchedule(@PathVariable Long barberId,
	                                                                @RequestBody WorkingScheduleDTO workingScheduleDTO) {
		WorkingScheduleDTO createdWorkingSchedule = workingScheduleService
				.createWorkingSchedule(barberId, workingScheduleDTO);
		return new ResponseEntity<>(createdWorkingSchedule, HttpStatus.CREATED);
	}

	/**
	 * Handles GET requests to retrieve a working schedule by its ID.
	 * It uses the WorkingScheduleService to get the working schedule and returns it as a response if found.
	 *
	 * @param barberId The ID of the barber for whom the working schedule is to be retrieved.
	 * @param id       The ID of the working schedule to be retrieved.
	 * @return The retrieved working schedule and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<WorkingScheduleDTO> getWorkingSchedule(@PathVariable Long barberId, @PathVariable Long id) {
		Optional<WorkingScheduleDTO> workingScheduleDTO = workingScheduleService.getWorkingSchedule(barberId, id);
		return workingScheduleDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles GET requests to retrieve all working schedules for a specific barber.
	 * It uses the WorkingScheduleService to get all working schedules and returns them as a response if any are found.
	 *
	 * @param barberId The ID of the barber for whom the working schedules are to be retrieved.
	 * @return All working schedules for the specified barber and HTTP status OK, or HTTP status NO CONTENT if no
	 * working schedules are found.
	 */
	@GetMapping
	public ResponseEntity<List<WorkingScheduleDTO>> getAllWorkingSchedules(@PathVariable Long barberId) {
		List<WorkingScheduleDTO> workingSchedules = workingScheduleService.getAllWorkingSchedules(barberId);
		if (!workingSchedules.isEmpty()) {
			return new ResponseEntity<>(workingSchedules, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Handles PUT requests to update a working schedule.
	 * It uses the WorkingScheduleService to update the working schedule and returns the updated working schedule
	 * as a response if found.
	 * This endpoint can be accessed by users with either the 'ROLE_BARBER' or 'ROLE_ADMIN' authority.
	 *
	 * @param barberId           The ID of the barber for whom the working schedule is to be updated.
	 * @param id                 The ID of the working schedule to be updated.
	 * @param workingScheduleDTO The updated working schedule.
	 * @return The updated working schedule and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_BARBER', 'ROLE_ADMIN')")
	public ResponseEntity<WorkingScheduleDTO> updateWorkingSchedule(@PathVariable Long barberId, @PathVariable Long id,
	                                                                @RequestBody WorkingScheduleDTO workingScheduleDTO) {
		Optional<WorkingScheduleDTO> updatedWorkingSchedule = workingScheduleService
				.updateWorkingSchedule(barberId, id, workingScheduleDTO);
		return updatedWorkingSchedule.map(updatedDto ->
						new ResponseEntity<>(updatedDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles DELETE requests to delete a working schedule.
	 * It uses the WorkingScheduleService to delete the working schedule and returns HTTP status NO CONTENT
	 * if successful.
	 * This endpoint can only be accessed by users with the 'ROLE_BARBER' or 'ROLE_ADMIN' authority.
	 *
	 * @param barberId The ID of the barber for whom the working schedule is to be deleted.
	 * @param id       The ID of the working schedule to be deleted.
	 * @return HTTP status NO CONTENT if successful, or HTTP status NOT FOUND if not found.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_BARBER', 'ROLE_ADMIN')")
	public ResponseEntity<Void> deleteWorkingSchedule(@PathVariable Long barberId, @PathVariable Long id) {
		workingScheduleService.deleteWorkingSchedule(barberId, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
