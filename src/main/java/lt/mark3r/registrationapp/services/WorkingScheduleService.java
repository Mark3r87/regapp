package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;

import java.util.List;
import java.util.Optional;

public interface WorkingScheduleService {
	WorkingScheduleDTO createWorkingSchedule(WorkingScheduleDTO workingScheduleDTO);

	Optional<WorkingScheduleDTO> getWorkingSchedule(Long id);

	List<WorkingScheduleDTO> getAllWorkingSchedules();

	Optional<WorkingScheduleDTO> updateWorkingSchedule(Long id, WorkingScheduleDTO workingScheduleDTO);

	void deleteWorkingSchedule(Long id);
}

