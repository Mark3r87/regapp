package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.services.BarberServService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BarberServControllerTests {

	@InjectMocks
	BarberServController barberServController;

	@Mock
	BarberServService barberServService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateBarberService() {
		BarberServDTO barberServDTO = new BarberServDTO();
		when(barberServService.createBarberService(any(BarberServDTO.class))).thenReturn(barberServDTO);

		ResponseEntity<BarberServDTO> response = barberServController.createBarberService(barberServDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(barberServDTO, response.getBody());
	}

	@Test
	public void testGetBarberService() {
		BarberServDTO barberServDTO = new BarberServDTO();
		when(barberServService.getBarberService(anyLong())).thenReturn(Optional.of(barberServDTO));

		ResponseEntity<BarberServDTO> response = barberServController.getBarberService(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(barberServDTO, response.getBody());
	}

	@Test
	public void testGetAllBarberServices() {
		List<BarberServDTO> barberServDTOs = Collections.singletonList(new BarberServDTO());
		when(barberServService.getAllBarberServices()).thenReturn(barberServDTOs);

		ResponseEntity<List<BarberServDTO>> response = barberServController.getAllBarberServices();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(barberServDTOs, response.getBody());
	}

	@Test
	public void testUpdateBarberService() {
		BarberServDTO barberServDTO = new BarberServDTO();
		when(barberServService.updateBarberService(anyLong(), any(BarberServDTO.class))).thenReturn(Optional.of(barberServDTO));

		ResponseEntity<BarberServDTO> response = barberServController.updateBarberService(1L, barberServDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(barberServDTO, response.getBody());
	}

	@Test
	public void testDeleteBarberService() {
		doNothing().when(barberServService).deleteBarberService(anyLong());

		ResponseEntity<Void> response = barberServController.deleteBarberService(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}