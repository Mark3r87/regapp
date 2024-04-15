package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.model.BarberServ;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.BarberServRepository;
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

public class BarberServServiceImplTests {

	@InjectMocks
	BarberServServiceImpl barberServService;

	@Mock
	BarberServRepository barberServRepository;

	@Mock
	BarberRepository barberRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateBarberService() {
		BarberServDTO barberServDTO = new BarberServDTO();
		BarberServ barberServ = new BarberServ(); // Assuming you have a BarberServ class
		when(barberServRepository.save(any())).thenReturn(barberServ);

		BarberServDTO response = barberServService.createBarberService(barberServDTO);

		assertEquals(barberServDTO, response);
	}

	@Test
	public void testGetBarberService() {
		BarberServDTO barberServDTO = new BarberServDTO();
		BarberServ barberServ = new BarberServ(); // Assuming you have a BarberServ class
		when(barberServRepository.findById(anyLong())).thenReturn(Optional.of(barberServ));

		Optional<BarberServDTO> response = barberServService.getBarberService(1L);

		assertEquals(barberServDTO, response.get());
	}

	@Test
	public void testGetAllBarberServices() {
		List<BarberServDTO> barberServDTOs = Collections.singletonList(new BarberServDTO());
		List<BarberServ> barberServs = Collections.singletonList(new BarberServ()); // Assuming you have a BarberServ class
		when(barberServRepository.findAll()).thenReturn(barberServs);

		List<BarberServDTO> response = barberServService.getAllBarberServices();

		assertEquals(barberServDTOs, response);
	}

	@Test
	public void testUpdateBarberService() {
		BarberServDTO barberServDTO = new BarberServDTO();
		BarberServ barberServ = new BarberServ(); // Assuming you have a BarberServ class
		when(barberServRepository.existsById(anyLong())).thenReturn(true);
		when(barberServRepository.save(any())).thenReturn(barberServ);

		Optional<BarberServDTO> response = barberServService.updateBarberService(1L, barberServDTO);

		assertEquals(barberServDTO, response.get());
	}

	@Test
	public void testDeleteBarberService() {
		doNothing().when(barberServRepository).deleteById(anyLong());

		barberServService.deleteBarberService(1L);

		verify(barberServRepository, times(1)).deleteById(anyLong());
	}
}
