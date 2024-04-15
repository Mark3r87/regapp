package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.model.WorkingSchedule;
import lt.mark3r.registrationapp.repository.WorkingScheduleRepository;
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

public class WorkingScheduleServiceImplTests {

	@InjectMocks
	WorkingScheduleServiceImpl workingScheduleService;

	@Mock
	WorkingScheduleRepository workingScheduleRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateWorkingSchedule() {
		WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
		WorkingSchedule workingSchedule = new WorkingSchedule(); // Assuming you have a WorkingSchedule class
		when(workingScheduleRepository.save(any())).thenReturn(workingSchedule);

		WorkingScheduleDTO response = workingScheduleService.createWorkingSchedule(workingScheduleDTO);

		assertEquals(workingScheduleDTO, response);
	}

	@Test
	public void testGetWorkingSchedule() {
		WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
		WorkingSchedule workingSchedule = new WorkingSchedule(); // Assuming you have a WorkingSchedule class
		when(workingScheduleRepository.findById(anyLong())).thenReturn(Optional.of(workingSchedule));

		Optional<WorkingScheduleDTO> response = workingScheduleService.getWorkingSchedule(1L);

		assertEquals(workingScheduleDTO, response.get());
	}

	@Test
	public void testGetAllWorkingSchedules() {
		List<WorkingScheduleDTO> workingScheduleDTOs = Collections.singletonList(new WorkingScheduleDTO());
		List<WorkingSchedule> workingSchedules = Collections.singletonList(new WorkingSchedule()); // Assuming you have a WorkingSchedule class
		when(workingScheduleRepository.findAll()).thenReturn(workingSchedules);

		List<WorkingScheduleDTO> response = workingScheduleService.getAllWorkingSchedules();

		assertEquals(workingScheduleDTOs, response);
	}

	@Test
	public void testUpdateWorkingSchedule() {
		WorkingScheduleDTO workingScheduleDTO = new WorkingScheduleDTO();
		WorkingSchedule workingSchedule = new WorkingSchedule(); // Assuming you have a WorkingSchedule class
		when(workingScheduleRepository.existsById(anyLong())).thenReturn(true);
		when(workingScheduleRepository.save(any())).thenReturn(workingSchedule);

		Optional<WorkingScheduleDTO> response = workingScheduleService.updateWorkingSchedule(1L, workingScheduleDTO);

		assertEquals(workingScheduleDTO, response.get());
	}

	@Test
	public void testDeleteWorkingSchedule() {
		doNothing().when(workingScheduleRepository).deleteById(anyLong());

		workingScheduleService.deleteWorkingSchedule(1L);

		verify(workingScheduleRepository, times(1)).deleteById(anyLong());
	}
}