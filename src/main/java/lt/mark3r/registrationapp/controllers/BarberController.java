package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

	@Autowired
	private BarberService barberService;

	@PostMapping
	public ResponseEntity<BarberDTO> registerBarber(@RequestBody BarberDTO barberDTO) {
		BarberDTO registeredBarberDTO = barberService.registerBarber(barberDTO);
		return new ResponseEntity<>(registeredBarberDTO, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BarberDTO> getBarber(@PathVariable Long id) {
		return barberService.getBarber(id)
				.map(barberDTO -> new ResponseEntity<>(barberDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<BarberDTO>> getAllBarbers() {
		List<BarberDTO> barbers = barberService.getAllBarbers();
		if (!barbers.isEmpty()) {
			return new ResponseEntity<>(barbers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<BarberDTO> updateBarber(@PathVariable Long id, @RequestBody BarberDTO barberDTO) {
		Optional<BarberDTO> updatedBarber = barberService.updateBarber(id, barberDTO);
		return updatedBarber
				.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
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
