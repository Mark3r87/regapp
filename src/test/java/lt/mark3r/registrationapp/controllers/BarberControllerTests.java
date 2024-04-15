package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.services.BarberService;
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

@WebMvcTest(BarberController.class)
public class BarberControllerTests {

	@Autowired
	private final MockMvc mockMvc;

	@MockBean
	private final BarberService barberService;

	public BarberControllerTests(MockMvc mockMvc, BarberService barberService) {
		this.mockMvc = mockMvc;
		this.barberService = barberService;
	}

	@Test
	public void testRegisterBarber() throws Exception {
		BarberDTO barberDTO = new BarberDTO();
		when(barberService.registerBarber(barberDTO)).thenReturn(barberDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/barbers")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetBarber() throws Exception {
		Long id = 1L;
		BarberDTO barberDTO = new BarberDTO();
		when(barberService.getBarber(id)).thenReturn(java.util.Optional.of(barberDTO));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/barbers/" + id))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetAllBarbers() throws Exception {
		when(barberService.getAllBarbers()).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/barbers"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void testUpdateBarber() throws Exception {
		Long id = 1L;
		BarberDTO barberDTO = new BarberDTO();
		when(barberService.updateBarber(id, barberDTO)).thenReturn(java.util.Optional.of(barberDTO));

		mockMvc.perform(MockMvcRequestBuilders.put("/api/barbers/" + id)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteBarber() throws Exception {
		long id = 1L;
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/barbers/" + id))
				.andExpect(status().isNoContent());
	}
}
