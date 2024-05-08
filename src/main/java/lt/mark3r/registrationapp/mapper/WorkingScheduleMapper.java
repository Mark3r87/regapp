/**
 * File: WorkingScheduleMapper.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the WorkingScheduleMapper class, which is responsible for mapping between WorkingSchedule and
 * WorkingScheduleDTO objects.
 */

package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.WorkingScheduleDTO;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.WorkingSchedule;

/**
 * The WorkingScheduleMapper class is responsible for mapping between WorkingSchedule and WorkingScheduleDTO objects.
 * It provides methods to map from a WorkingSchedule entity to a WorkingScheduleDTO, and vice versa.
 */
public class WorkingScheduleMapper {

	/**
	 * Maps a WorkingScheduleDTO and a Barber entity to a WorkingSchedule entity.
	 */
	public static WorkingSchedule toEntity(WorkingScheduleDTO dto, Barber barber) {
		WorkingSchedule workingSchedule = new WorkingSchedule();
		workingSchedule.setId(dto.getId());
		workingSchedule.setDate(dto.getDate());
		workingSchedule.setTimeSlots(dto.getTimeSlots());
		workingSchedule.setBarber(barber);
		return workingSchedule;
	}

	/**
	 * Maps a WorkingSchedule entity to a WorkingScheduleDTO object.
	 */
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

	/**
	 * Maps a WorkingScheduleDTO, a Barber entity, and an existing WorkingSchedule entity to a WorkingSchedule entity.
	 */
	public static WorkingSchedule toEntity(WorkingScheduleDTO dto, Barber barber,
	                                       WorkingSchedule existingWorkingSchedule) {
		WorkingSchedule workingSchedule =
				existingWorkingSchedule != null ? existingWorkingSchedule : new WorkingSchedule();
		workingSchedule.setId(dto.getId());
		workingSchedule.setDate(dto.getDate());
		workingSchedule.setTimeSlots(dto.getTimeSlots());
		workingSchedule.setBarber(barber);
		return workingSchedule;
	}
}


