package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.BarberServ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarberServMapperTests {

	@Test
	public void testToEntity() {
		BarberServDTO dto = new BarberServDTO();

		Barber barber = new Barber();

		BarberServ barberService = BarberServMapper.toEntity(dto, barber);

		assertEquals(dto.getService(), barberService.getService());
		assertEquals(dto.getDefaultDurationInMinutes(), barberService.getDefaultDurationInMinutes());
		assertEquals(barber, barberService.getBarber());
	}

	@Test
	public void testToDTO() {
		BarberServ barberService = new BarberServ();

		BarberServDTO dto = BarberServMapper.toDTO(barberService);

		assertEquals(barberService.getService(), dto.getService());
		assertEquals(barberService.getDefaultDurationInMinutes(), dto.getDefaultDurationInMinutes());
		assertEquals(barberService.getBarber().getId(), dto.getBarberId());
	}
}