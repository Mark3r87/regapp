package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.repository.BarberRepository;
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

public class BarberServiceImplTests {

	@InjectMocks
	BarberServiceImpl barberService;

	@Mock
	BarberRepository barberRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegisterBarber() {
		BarberDTO barberDTO = new BarberDTO();
		Barber barber = new Barber(); // Assuming you have a Barber class
		when(barberRepository.save(any())).thenReturn(barber);

		BarberDTO response = barberService.registerBarber(barberDTO);

		assertEquals(barberDTO, response);
	}

	@Test
	public void testGetBarber() {
		BarberDTO barberDTO = new BarberDTO();
		Barber barber = new Barber(); // Assuming you have a Barber class
		when(barberRepository.findById(anyLong())).thenReturn(Optional.of(barber));

		Optional<BarberDTO> response = barberService.getBarber(1L);

		assertEquals(barberDTO, response.get());
	}

	@Test
	public void testGetAllBarbers() {
		List<BarberDTO> barberDTOs = Collections.singletonList(new BarberDTO());
		List<Barber> barbers = Collections.singletonList(new Barber()); // Assuming you have a Barber class
		when(barberRepository.findAll()).thenReturn(barbers);

		List<BarberDTO> response = barberService.getAllBarbers();

		assertEquals(barberDTOs, response);
	}

	@Test
	public void testUpdateBarber() {
		BarberDTO barberDTO = new BarberDTO();
		Barber barber = new Barber(); // Assuming you have a Barber class
		when(barberRepository.existsById(anyLong())).thenReturn(true);
		when(barberRepository.save(any())).thenReturn(barber);

		Optional<BarberDTO> response = barberService.updateBarber(1L, barberDTO);

		assertEquals(barberDTO, response.get());
	}

	@Test
	public void testDeleteBarber() {
		doNothing().when(barberRepository).deleteById(anyLong());

		barberService.deleteBarber(1L);

		verify(barberRepository, times(1)).deleteById(anyLong());
	}
}
