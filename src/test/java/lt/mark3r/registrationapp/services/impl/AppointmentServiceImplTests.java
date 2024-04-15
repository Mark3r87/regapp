package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.model.Appointment;
import lt.mark3r.registrationapp.repository.AppointmentRepository;
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

public class AppointmentServiceImplTests {

	@InjectMocks
	AppointmentServiceImpl appointmentService;

	@Mock
	AppointmentRepository appointmentRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBookAppointment() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		Appointment appointment = new Appointment(); // Assuming you have an Appointment class
		when(appointmentRepository.save(any())).thenReturn(appointment);

		AppointmentDTO response = appointmentService.bookAppointment(appointmentDTO);

		assertEquals(appointmentDTO, response);
	}

	@Test
	public void testGetAppointment() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		Appointment appointment = new Appointment(); // Assuming you have an Appointment class
		when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));

		Optional<AppointmentDTO> response = appointmentService.getAppointment(1L);

		assertEquals(appointmentDTO, response.get());
	}

	@Test
	public void testGetAllAppointments() {
		List<AppointmentDTO> appointmentDTOs = Collections.singletonList(new AppointmentDTO());
		List<Appointment> appointments = Collections.singletonList(new Appointment()); // Assuming you have an Appointment class
		when(appointmentRepository.findAll()).thenReturn(appointments);

		List<AppointmentDTO> response = appointmentService.getAllAppointments();

		assertEquals(appointmentDTOs, response);
	}


	@Test
	public void testUpdateAppointment1() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		Appointment appointment = new Appointment(); // Assuming you have an Appointment class
		when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
		when(appointmentRepository.save(any())).thenReturn(appointment);

		Optional<AppointmentDTO> response = appointmentService.updateAppointment(appointmentDTO);

		assertEquals(appointmentDTO, response.get());
	}

	@Test
	public void testUpdateAppointment2() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		Appointment appointment = new Appointment(); // Assuming you have an Appointment class
		when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
		when(appointmentRepository.save(any())).thenReturn(appointment);

		Optional<AppointmentDTO> response = appointmentService.updateAppointment(appointmentDTO);

		assertEquals(appointmentDTO, response.get());
	}


	@Test
	public void testCancelAppointment() {
		doNothing().when(appointmentRepository).deleteById(anyLong());

		appointmentService.cancelAppointment(1L);

		verify(appointmentRepository, times(1)).deleteById(anyLong());
	}
}
