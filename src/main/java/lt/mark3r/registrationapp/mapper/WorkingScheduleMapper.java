package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.model.WorkingSchedule;

public class WorkingScheduleMapper {
	public static WorkingSchedule toEntity(WorkingScheduleDTO dto) {
		WorkingSchedule workingSchedule = new WorkingSchedule();
		workingSchedule.setId(dto.getId());
		workingSchedule.setDate(dto.getDate());
		workingSchedule.setTimeSlots(dto.getTimeSlots());
		// Assuming Barber is resolved elsewhere
		return workingSchedule;
	}

	public static WorkingScheduleDTO toDTO(WorkingSchedule workingSchedule) {
		WorkingScheduleDTO dto = new WorkingScheduleDTO();
		dto.setId(workingSchedule.getId());
		dto.setDate(workingSchedule.getDate());
		dto.setTimeSlots(workingSchedule.getTimeSlots());
		if (workingSchedule.getBarber() != null) {
			dto.setBarberId(workingSchedule.getBarber().getId());
		}
		return dto;
	}
}

