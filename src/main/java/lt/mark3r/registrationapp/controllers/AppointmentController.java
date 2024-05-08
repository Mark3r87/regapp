/**
 * File: AppointmentController.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AppointmentController class, which is responsible for handling HTTP requests related to
 * appointments.
 */

package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * The AppointmentController class is responsible for handling HTTP requests related to appointments.
 * It uses the AppointmentService to perform operations and returns the results as HTTP responses.
 * <p>
 * Annotations:
 * - @CrossOrigin: Allows cross-origin requests from "http://localhost:3000".
 * - @RestController: Indicates that the class is a controller and the return values of its methods should be bound to
 * the web response body.
 * - @RequestMapping: Maps all HTTP operations by default to /api/appointments.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	/**
	 * Handles POST requests to book an appointment.
	 * It uses the AppointmentService to book the appointment and returns the booked appointment as a response.
	 *
	 * @param appointmentDTO The appointment to be booked.
	 * @return The booked appointment and HTTP status CREATED.
	 */
	@PostMapping
	public ResponseEntity<AppointmentDTO> bookAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		AppointmentDTO bookedAppointmentDTO = appointmentService.bookAppointment(appointmentDTO);
		return new ResponseEntity<>(bookedAppointmentDTO, HttpStatus.CREATED);
	}

	/**
	 * Handles GET requests to retrieve an appointment by its ID.
	 * It uses the AppointmentService to get the appointment and returns it as a response if found.
	 *
	 * @param id The ID of the appointment to be retrieved.
	 * @return The retrieved appointment and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Long id) {
		return appointmentService.getAppointment(id)
				.map(appointmentDTO -> new ResponseEntity<>(appointmentDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles GET requests to retrieve all appointments.
	 * It uses the AppointmentService to get all appointments and returns them as a response if any are found.
	 *
	 * @return All appointments and HTTP status OK, or HTTP status NO CONTENT if no appointments are found.
	 */
	@GetMapping
	public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
		List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
		if (!appointments.isEmpty()) {
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Handles GET requests to retrieve available timeslots for a given date and barber.
	 * It uses the AppointmentService to get the timeslots and returns them as a response if any are found.
	 *
	 * @param date     The date for which to retrieve timeslots.
	 * @param barberId The ID of the barber for which to retrieve timeslots.
	 * @return Available timeslots and HTTP status OK, or HTTP status NO CONTENT if no timeslots are found.
	 */
	@GetMapping("/timeslots")
	public ResponseEntity<List<String>> getAvailableTimeslots(@RequestParam LocalDate date,
	                                                          @RequestParam Long barberId) {
		List<String> timeslots = appointmentService.getAvailableTimeslots(date, barberId);
		if (!timeslots.isEmpty()) {
			return new ResponseEntity<>(timeslots, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Handles PUT requests to update an appointment.
	 * It uses the AppointmentService to update the appointment and returns the updated appointment as a response
	 * if found.
	 *
	 * @param id             The ID of the appointment to be updated.
	 * @param appointmentDTO The updated appointment.
	 * @return The updated appointment and HTTP status OK, or HTTP status NOT FOUND if not found.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id,
	                                                        @RequestBody AppointmentDTO appointmentDTO) {
		appointmentDTO.setId(id);
		return appointmentService.updateAppointment(appointmentDTO)
				.map(updatedAppointmentDTO -> new ResponseEntity<>(updatedAppointmentDTO,
						HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Handles DELETE requests to cancel an appointment.
	 * It uses the AppointmentService to cancel the appointment and returns HTTP status NO CONTENT if successful.
	 *
	 * @param id The ID of the appointment to be cancelled.
	 * @return HTTP status NO CONTENT if successful, or HTTP status NOT FOUND if not found.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
		AppointmentDTO existingAppointmentDTO = appointmentService.getAppointment(id).orElse(null);
		if (existingAppointmentDTO != null) {
			appointmentService.cancelAppointment(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
