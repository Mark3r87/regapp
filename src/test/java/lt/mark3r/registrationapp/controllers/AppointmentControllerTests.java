package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.services.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTests {

	@Autowired
	private final MockMvc mockMvc;

	@MockBean
	private final AppointmentService appointmentService;

	public AppointmentControllerTests(MockMvc mockMvc, AppointmentService appointmentService) {
		this.mockMvc = mockMvc;
		this.appointmentService = appointmentService;
	}

	@Test
	public void testBookAppointment() throws Exception {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		when(appointmentService.bookAppointment(appointmentDTO)).thenReturn(appointmentDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/appointments")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetAppointment() throws Exception {
		Long id = 1L;
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		when(appointmentService.getAppointment(id)).thenReturn(java.util.Optional.of(appointmentDTO));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments/" + id))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetAllAppointments() throws Exception {
		when(appointmentService.getAllAppointments()).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void testUpdateAppointment() throws Exception {
		long id = 1L;
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		when(appointmentService.updateAppointment(appointmentDTO)).thenReturn(java.util.Optional.of(appointmentDTO));

		mockMvc.perform(MockMvcRequestBuilders.put("/api/appointments/" + id)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testCancelAppointment() throws Exception {
		long id = 1L;
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/appointments/" + id))
				.andExpect(status().isNoContent());
	}
}