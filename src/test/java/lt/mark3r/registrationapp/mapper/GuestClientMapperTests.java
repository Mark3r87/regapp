package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.model.GuestClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuestClientMapperTests {

	@Test
	public void testToEntity() {
		GuestClientDTO dto = new GuestClientDTO();
		dto.setId(1L);
		dto.setPhoneNumber("1234567890");
		dto.setName("Test Name");
		dto.setAppointmentId(1L);

		GuestClient guestClient = GuestClientMapper.toEntity(dto);

		assertEquals(dto.getId(), guestClient.getId());
		assertEquals(dto.getPhoneNumber(), guestClient.getPhoneNumber());
		assertEquals(dto.getName(), guestClient.getName());

	}

	@Test
	public void testToDTO() {
		GuestClient guestClient = new GuestClient();
		guestClient.setId(1L);
		guestClient.setPhoneNumber("1234567890");
		guestClient.setName("Test Name");


		GuestClientDTO dto = GuestClientMapper.toDTO(guestClient);

		assertEquals(guestClient.getId(), dto.getId());
		assertEquals(guestClient.getPhoneNumber(), dto.getPhoneNumber());
		assertEquals(guestClient.getName(), dto.getName());
		assertEquals(guestClient.getAppointment().getId(), dto.getAppointmentId());
	}
}