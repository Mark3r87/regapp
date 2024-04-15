package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.model.GuestClient;
import lt.mark3r.registrationapp.repository.GuestClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GuestClientServiceImplTests {

	@InjectMocks
	GuestClientServiceImpl guestClientService;

	@Mock
	GuestClientRepository guestClientRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegisterGuestClient() {
		GuestClientDTO guestClientDTO = new GuestClientDTO();
		GuestClient guestClient = new GuestClient(); // Assuming you have a GuestClient class
		when(guestClientRepository.save(any())).thenReturn(guestClient);

		GuestClientDTO response = guestClientService.registerGuestClient(guestClientDTO);

		assertEquals(guestClientDTO, response);
	}

	@Test
	public void testGetGuestClient() {
		GuestClientDTO guestClientDTO = new GuestClientDTO();
		GuestClient guestClient = new GuestClient(); // Assuming you have a GuestClient class
		when(guestClientRepository.findById(anyLong())).thenReturn(Optional.of(guestClient));

		Optional<GuestClientDTO> response = guestClientService.getGuestClient(1L);

		assertEquals(guestClientDTO, response.get());
	}

	@Test
	public void testGetAllGuestClients() {
		List<GuestClientDTO> guestClientDTOs = Collections.singletonList(new GuestClientDTO());
		List<GuestClient> guestClients = Collections.singletonList(new GuestClient()); // Assuming you have a GuestClient class
		when(guestClientRepository.findAll()).thenReturn(guestClients);

		List<GuestClientDTO> response = guestClientService.getAllGuestClients();

		assertEquals(guestClientDTOs, response);
	}

	@Test
	public void testUpdateGuestClient() {
		GuestClientDTO guestClientDTO = new GuestClientDTO();
		GuestClient guestClient = new GuestClient(); // Assuming you have a GuestClient class
		when(guestClientRepository.existsById(anyLong())).thenReturn(true);
		when(guestClientRepository.save(any())).thenReturn(guestClient);

		Optional<GuestClientDTO> response = guestClientService.updateGuestClient(1L, guestClientDTO);

		assertEquals(guestClientDTO, response.get());
	}

	@Test
	public void testDeleteGuestClient() {
		doNothing().when(guestClientRepository).deleteById(anyLong());

		guestClientService.deleteGuestClient(1L);

		verify(guestClientRepository, times(1)).deleteById(anyLong());
	}
}