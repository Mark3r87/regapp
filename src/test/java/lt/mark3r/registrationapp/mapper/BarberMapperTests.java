package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.model.Barber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarberMapperTests {

	@Test
	public void testToEntity() {
		BarberDTO dto = new BarberDTO();


		Barber barber = BarberMapper.toEntity(dto);

		assertEquals(dto.getId(), barber.getId());
		assertEquals(dto.getName(), barber.getName());
		assertEquals(dto.getLocation(), barber.getLocation());
		assertEquals(dto.getContactInformation(), barber.getContactInformation());
		assertEquals(dto.getRating(), barber.getRating());
		assertEquals(dto.getSpecialties(), barber.getSpecialties());
		assertEquals(dto.getServicesOffered(), barber.getServicesOffered());
		assertEquals(dto.isHasBeenRated(), barber.isHasBeenRated());
	}

	@Test
	public void testToDTO() {
		Barber barber = new Barber();

		BarberDTO dto = BarberMapper.toDTO(barber);

		assertEquals(barber.getId(), dto.getId());
		assertEquals(barber.getName(), dto.getName());
		assertEquals(barber.getLocation(), dto.getLocation());
		assertEquals(barber.getContactInformation(), dto.getContactInformation());
		assertEquals(barber.getRating(), dto.getRating());
		assertEquals(barber.getSpecialties(), dto.getSpecialties());
		assertEquals(barber.getServicesOffered(), dto.getServicesOffered());
		assertEquals(barber.isHasBeenRated(), dto.isHasBeenRated());
	}
}