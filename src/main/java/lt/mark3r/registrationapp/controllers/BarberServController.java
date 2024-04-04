package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.services.BarberServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/barberservs")
public class BarberServController {

	@Autowired
	private BarberServService barberServService;

	@PostMapping
	public ResponseEntity<BarberServDTO> createBarberService(@RequestBody BarberServDTO barberServDTO) {
		BarberServDTO createdBarberServ = barberServService.createBarberService(barberServDTO);
		return new ResponseEntity<>(createdBarberServ, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BarberServDTO> getBarberService(@PathVariable Long id) {
		Optional<BarberServDTO> barberServDTO = barberServService.getBarberService(id);
		return barberServDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<BarberServDTO>> getAllBarberServices() {
		List<BarberServDTO> dtos = barberServService.getAllBarberServices();
		if (!dtos.isEmpty()) {
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<BarberServDTO> updateBarberService(@PathVariable Long id, @RequestBody BarberServDTO barberServDTO) {
		Optional<BarberServDTO> updatedBarberServ = barberServService.updateBarberService(id, barberServDTO);
		return updatedBarberServ.map(updatedDto -> new ResponseEntity<>(updatedDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBarberService(@PathVariable Long id) {
		barberServService.deleteBarberService(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
