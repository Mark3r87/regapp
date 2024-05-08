/**
 * File: WorkingScheduleService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * <p>
 * The WorkingScheduleService interface defines the contract for services related to working schedules.
 * It declares methods for creating, retrieving, updating, and deleting working schedules.
 * Methods:
 * - createWorkingSchedule(Long barberId, WorkingScheduleDTO workingScheduleDTO): Takes a barber ID and
 * a WorkingScheduleDTO object and creates a working schedule. Returns the created WorkingScheduleDTO.
 * - getWorkingSchedule(Long barberId, Long id): Retrieves a working schedule by its ID and the barber's ID.
 * Returns an Optional that contains the WorkingScheduleDTO if found, or is empty if not found.
 * - getAllWorkingSchedules(Long barberId): Retrieves all working schedules for a specific barber by the barber's ID.
 * Returns a List of WorkingScheduleDTO objects.
 * - updateWorkingSchedule(Long barberId, Long id, WorkingScheduleDTO workingScheduleDTO): Updates a working schedule.
 * Takes a barber ID, a schedule ID, and a WorkingScheduleDTO object with the updated details. Returns an Optional
 * that contains the updated WorkingScheduleDTO if the update was successful, or is empty if not found.
 * - deleteWorkingSchedule(Long barberId, Long id): Deletes a working schedule by its ID and the barber's ID.
 * Does not return a value.
 */

package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;

import java.util.List;
import java.util.Optional;

public interface WorkingScheduleService {
	WorkingScheduleDTO createWorkingSchedule(Long barberId, WorkingScheduleDTO workingScheduleDTO);

	Optional<WorkingScheduleDTO> getWorkingSchedule(Long barberId, Long id);

	List<WorkingScheduleDTO> getAllWorkingSchedules(Long barberId);

	Optional<WorkingScheduleDTO> updateWorkingSchedule(Long barberId, Long id, WorkingScheduleDTO workingScheduleDTO);

	void deleteWorkingSchedule(Long barberId, Long id);
}