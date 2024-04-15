package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.model.WorkingSchedule;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkingScheduleMapperTests {

	@Test
	public void testToEntity() {
		WorkingScheduleDTO dto = new WorkingScheduleDTO();
		dto.setId(1L);
		dto.setDate(LocalDate.now());
		dto.setTimeSlots(Arrays.asList(LocalTime.now()));
		dto.setBarberId(1L);

		WorkingSchedule workingSchedule = WorkingScheduleMapper.toEntity(dto);

		assertEquals(dto.getId(), workingSchedule.getId());
		assertEquals(dto.getDate(), workingSchedule.getDate());
		assertEquals(dto.getTimeSlots(), workingSchedule.getTimeSlots());

	}

	@Test
	public void testToDTO() {
		WorkingSchedule workingSchedule = new WorkingSchedule();
		workingSchedule.setId(1L);
		workingSchedule.setDate(LocalDate.now());
		workingSchedule.setTimeSlots(Arrays.asList(LocalTime.now()));
		// Set actual Barber object

		WorkingScheduleDTO dto = WorkingScheduleMapper.toDTO(workingSchedule);

		assertEquals(workingSchedule.getId(), dto.getId());
		assertEquals(workingSchedule.getDate(), dto.getDate());
		assertEquals(workingSchedule.getTimeSlots(), dto.getTimeSlots());
		assertEquals(workingSchedule.getBarber().getId(), dto.getBarberId());
	}
}
