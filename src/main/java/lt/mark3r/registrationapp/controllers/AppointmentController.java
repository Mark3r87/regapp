package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<AppointmentDTO> bookAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		AppointmentDTO bookedAppointmentDTO = appointmentService.bookAppointment(appointmentDTO);
		return new ResponseEntity<>(bookedAppointmentDTO, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Long id) {
		return appointmentService.getAppointment(id)
				.map(appointmentDTO -> new ResponseEntity<>(appointmentDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
		List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
		if (!appointments.isEmpty()) {
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
		appointmentDTO.setId(id); // Ensure the ID matches the URL path
		return appointmentService.updateAppointment(appointmentDTO)
				.map(updatedAppointmentDTO -> new ResponseEntity<>(updatedAppointmentDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

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
