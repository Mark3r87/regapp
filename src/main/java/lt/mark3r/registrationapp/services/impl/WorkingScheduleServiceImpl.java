package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.mapper.WorkingScheduleMapper;
import lt.mark3r.registrationapp.model.WorkingSchedule;
import lt.mark3r.registrationapp.repository.WorkingScheduleRepository;
import lt.mark3r.registrationapp.services.WorkingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkingScheduleServiceImpl implements WorkingScheduleService {
	@Autowired
	private WorkingScheduleRepository workingScheduleRepository;

	@Override
	public WorkingScheduleDTO createWorkingSchedule(WorkingScheduleDTO workingScheduleDTO) {
		WorkingSchedule workingSchedule = WorkingScheduleMapper.toEntity(workingScheduleDTO);
		WorkingSchedule savedWorkingSchedule = workingScheduleRepository.save(workingSchedule);
		return WorkingScheduleMapper.toDTO(savedWorkingSchedule);
	}

	@Override
	public Optional<WorkingScheduleDTO> getWorkingSchedule(Long id) {
		return workingScheduleRepository.findById(id).map(WorkingScheduleMapper::toDTO);
	}

	@Override
	public List<WorkingScheduleDTO> getAllWorkingSchedules() {
		return workingScheduleRepository.findAll().stream().map(WorkingScheduleMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<WorkingScheduleDTO> updateWorkingSchedule(Long id, WorkingScheduleDTO workingScheduleDTO) {
		if (workingScheduleRepository.existsById(id)) {
			workingScheduleDTO.setId(id);
			WorkingSchedule updatedWorkingSchedule = WorkingScheduleMapper.toEntity(workingScheduleDTO);
			WorkingSchedule savedWorkingSchedule = workingScheduleRepository.save(updatedWorkingSchedule);
			return Optional.of(WorkingScheduleMapper.toDTO(savedWorkingSchedule));
		}
		return Optional.empty();
	}

	@Override
	public void deleteWorkingSchedule(Long id) {
		workingScheduleRepository.deleteById(id);
	}
}

