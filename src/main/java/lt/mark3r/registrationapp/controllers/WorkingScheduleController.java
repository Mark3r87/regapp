package lt.mark3r.registrationapp.controllers;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.services.WorkingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workingschedules")
public class WorkingScheduleController {

	@Autowired
	private WorkingScheduleService workingScheduleService;

	@PostMapping
	public ResponseEntity<WorkingScheduleDTO> createWorkingSchedule(@RequestBody WorkingScheduleDTO workingScheduleDTO) {
		WorkingScheduleDTO createdWorkingSchedule = workingScheduleService.createWorkingSchedule(workingScheduleDTO);
		return new ResponseEntity<>(createdWorkingSchedule, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<WorkingScheduleDTO> getWorkingSchedule(@PathVariable Long id) {
		Optional<WorkingScheduleDTO> workingScheduleDTO = workingScheduleService.getWorkingSchedule(id);
		return workingScheduleDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<WorkingScheduleDTO>> getAllWorkingSchedules() {
		List<WorkingScheduleDTO> workingSchedules = workingScheduleService.getAllWorkingSchedules();
		if (!workingSchedules.isEmpty()) {
			return new ResponseEntity<>(workingSchedules, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<WorkingScheduleDTO> updateWorkingSchedule(@PathVariable Long id, @RequestBody WorkingScheduleDTO workingScheduleDTO) {
		Optional<WorkingScheduleDTO> updatedWorkingSchedule = workingScheduleService.updateWorkingSchedule(id, workingScheduleDTO);
		return updatedWorkingSchedule.map(updatedDto -> new ResponseEntity<>(updatedDto, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWorkingSchedule(@PathVariable Long id) {
		workingScheduleService.deleteWorkingSchedule(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
