package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.services.GuestClientService;
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

public class GuestClientControllerTests {

	@InjectMocks
	GuestClientController guestClientController;

	@Mock
	GuestClientService guestClientService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegisterGuestClient() {
		GuestClientDTO guestClientDTO = new GuestClientDTO();
		when(guestClientService.registerGuestClient(any(GuestClientDTO.class))).thenReturn(guestClientDTO);

		ResponseEntity<GuestClientDTO> response = guestClientController.registerGuestClient(guestClientDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(guestClientDTO, response.getBody());
	}

	@Test
	public void testGetGuestClient() {
		GuestClientDTO guestClientDTO = new GuestClientDTO();
		when(guestClientService.getGuestClient(anyLong())).thenReturn(Optional.of(guestClientDTO));

		ResponseEntity<GuestClientDTO> response = guestClientController.getGuestClient(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(guestClientDTO, response.getBody());
	}

	@Test
	public void testGetAllGuestClients() {
		List<GuestClientDTO> guestClientDTOs = Collections.singletonList(new GuestClientDTO());
		when(guestClientService.getAllGuestClients()).thenReturn(guestClientDTOs);

		ResponseEntity<List<GuestClientDTO>> response = guestClientController.getAllGuestClients();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(guestClientDTOs, response.getBody());
	}

	@Test
	public void testUpdateGuestClient() {
		GuestClientDTO guestClientDTO = new GuestClientDTO();
		when(guestClientService.updateGuestClient(anyLong(), any(GuestClientDTO.class))).thenReturn(Optional.of(guestClientDTO));

		ResponseEntity<GuestClientDTO> response = guestClientController.updateGuestClient(1L, guestClientDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(guestClientDTO, response.getBody());
	}

	@Test
	public void testDeleteGuestClient() {
		doNothing().when(guestClientService).deleteGuestClient(anyLong());

		ResponseEntity<Void> response = guestClientController.deleteGuestClient(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
