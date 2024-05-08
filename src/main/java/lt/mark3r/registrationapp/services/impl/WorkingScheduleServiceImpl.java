package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.mapper.WorkingScheduleMapper;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.WorkingSchedule;
import lt.mark3r.registrationapp.repository.BarberRepository;
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
	@Autowired
	private BarberRepository barberRepository;

	@Override
	public WorkingScheduleDTO createWorkingSchedule(Long barberId, WorkingScheduleDTO workingScheduleDTO) {
		Barber barber = barberRepository.findById(barberId).orElse(null);
		if (barber == null) {
			throw new RuntimeException("Barber not found");
		}
		WorkingSchedule workingSchedule = WorkingScheduleMapper.toEntity(workingScheduleDTO, barber);
		WorkingSchedule savedWorkingSchedule = workingScheduleRepository.save(workingSchedule);
		return WorkingScheduleMapper.toDTO(savedWorkingSchedule);
	}

	@Override
	public Optional<WorkingScheduleDTO> getWorkingSchedule(Long barberId, Long id) {
		return workingScheduleRepository.findByIdAndBarberId(id, barberId).map(WorkingScheduleMapper::toDTO);
	}

	@Override
	public List<WorkingScheduleDTO> getAllWorkingSchedules(Long barberId) {
		return workingScheduleRepository.findAllByBarberId(barberId).stream().map(WorkingScheduleMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<WorkingScheduleDTO> updateWorkingSchedule(Long barberId, Long id, WorkingScheduleDTO workingScheduleDTO) {
		if (workingScheduleRepository.existsById(id)) {
			Barber barber = barberRepository.findById(barberId).orElse(null);
			WorkingSchedule existingWorkingSchedule = workingScheduleRepository.findById(id).orElse(null);
			WorkingSchedule updatedWorkingSchedule = WorkingScheduleMapper.toEntity(workingScheduleDTO, barber, existingWorkingSchedule);
			WorkingSchedule savedWorkingSchedule = workingScheduleRepository.save(updatedWorkingSchedule);
			return Optional.of(WorkingScheduleMapper.toDTO(savedWorkingSchedule));
		}
		return Optional.empty();
	}

	@Override
	public void deleteWorkingSchedule(Long barberId, Long id) {
		if (workingScheduleRepository.existsByIdAndBarberId(id, barberId)) {
			workingScheduleRepository.deleteById(id);
		} else {
			throw new RuntimeException("Working schedule not found");
		}
	}
}
