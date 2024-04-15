package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.model.Appointment;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.GuestClient;
import lt.mark3r.registrationapp.model.TypeOfService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentMapperTests {

	@Test
	public void testToEntity() {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setId(1L);
		dto.setDate(LocalDate.now());
		dto.setTime(LocalTime.now());
		dto.setBarberId(1L);
		dto.setService(TypeOfService.HAIRCUT);
		dto.setGuestClientId(1L);

		Appointment appointment = AppointmentMapper.toEntity(dto);

		assertEquals(dto.getId(), appointment.getId());
		assertEquals(dto.getDate(), appointment.getDate());
		assertEquals(dto.getTime(), appointment.getTime());

	}

	@Test
	public void testToDTO() {
		Appointment appointment = new Appointment();
		appointment.setId(1L);
		appointment.setDate(LocalDate.now());
		appointment.setTime(LocalTime.now());
		appointment.setBarber(new Barber());
		appointment.setService(TypeOfService.HAIRCUT);
		appointment.setGuestClient(new GuestClient());

		AppointmentDTO dto = AppointmentMapper.toDTO(appointment);

		assertEquals(appointment.getId(), dto.getId());
		assertEquals(appointment.getDate(), dto.getDate());
		assertEquals(appointment.getTime(), dto.getTime());
		assertEquals(appointment.getBarber().getId(), dto.getBarberId());
		assertEquals(appointment.getService(), dto.getService());
		assertEquals(appointment.getGuestClient().getId(), dto.getGuestClientId());
	}
}