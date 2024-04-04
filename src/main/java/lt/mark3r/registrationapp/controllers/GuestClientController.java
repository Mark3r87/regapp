package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.services.GuestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guestclients")
public class GuestClientController {

	@Autowired
	private GuestClientService guestClientService;

	@PostMapping
	public ResponseEntity<GuestClientDTO> registerGuestClient(@RequestBody GuestClientDTO guestClientDTO) {
		GuestClientDTO registeredGuestClient = guestClientService.registerGuestClient(guestClientDTO);
		return new ResponseEntity<>(registeredGuestClient, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GuestClientDTO> getGuestClient(@PathVariable Long id) {
		Optional<GuestClientDTO> guestClientDTO = guestClientService.getGuestClient(id);
		return guestClientDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<GuestClientDTO>> getAllGuestClients() {
		List<GuestClientDTO> guestClients = guestClientService.getAllGuestClients();
		if (!guestClients.isEmpty()) {
			return new ResponseEntity<>(guestClients, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<GuestClientDTO> updateGuestClient(@PathVariable Long id, @RequestBody GuestClientDTO guestClientDTO) {
		Optional<GuestClientDTO> updatedGuestClient = guestClientService.updateGuestClient(id, guestClientDTO);
		return updatedGuestClient.map(updatedDto -> new ResponseEntity<>(updatedDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGuestClient(@PathVariable Long id) {
		guestClientService.deleteGuestClient(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
