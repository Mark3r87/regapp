package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.services.WorkingScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WorkingScheduleControllerTests {

	@InjectMocks
	WorkingScheduleController workingScheduleController;

	@Mock
	WorkingScheduleService workingScheduleService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateWorkingSchedule() {
		WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
		when(workingScheduleService.createWorkingSchedule(any(WorkingScheduleDTO.class))).thenReturn(workingScheduleDTO);

		ResponseEntity<WorkingScheduleDTO> response = workingScheduleController.createWorkingSchedule(workingScheduleDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(workingScheduleDTO, response.getBody());
	}

	@Test
	public void testGetWorkingSchedule() {
		WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
		when(workingScheduleService.getWorkingSchedule(anyLong())).thenReturn(Optional.of(workingScheduleDTO));

		ResponseEntity<WorkingScheduleDTO> response = workingScheduleController.getWorkingSchedule(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(workingScheduleDTO, response.getBody());
	}

	@Test
	public void testGetAllWorkingSchedules() {
		List<WorkingScheduleDTO> workingScheduleDTOs = Collections.singletonList(new WorkingScheduleDTO());
		when(workingScheduleService.getAllWorkingSchedules()).thenReturn(workingScheduleDTOs);

		ResponseEntity<List<WorkingScheduleDTO>> response = workingScheduleController.getAllWorkingSchedules();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(workingScheduleDTOs, response.getBody());
	}

	@Test
	public void testUpdateWorkingSchedule() {
		WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
		when(workingScheduleService.updateWorkingSchedule(anyLong(), any(WorkingScheduleDTO.class))).thenReturn(Optional.of(workingScheduleDTO));

		ResponseEntity<WorkingScheduleDTO> response = workingScheduleController.updateWorkingSchedule(1L, workingScheduleDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(workingScheduleDTO, response.getBody());
	}

	@Test
	public void testDeleteWorkingSchedule() {
		doNothing().when(workingScheduleService).deleteWorkingSchedule(anyLong());

		ResponseEntity<Void> response = workingScheduleController.deleteWorkingSchedule(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
