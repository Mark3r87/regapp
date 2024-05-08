/**
 * File: WorkingScheduleDTO.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the WorkingScheduleDTO class, which is a Data Transfer Object (DTO) for working schedules.
 */

package lt.mark3r.registrationapp.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The WorkingScheduleDTO class is a Data Transfer Object (DTO) for working schedules.
 * It contains information about a working schedule, including its ID, date, time slots, and barber ID.
 */
public class WorkingScheduleDTO {
	private Long id;
	private LocalDate date;
	private List<LocalTime> timeSlots;
	private Long barberId;

	/**
	 * Default constructor.
	 */
	public WorkingScheduleDTO() {
	}

	/**
	 * Constructor with parameters.
	 *
	 * @param id        The ID of the working schedule.
	 * @param date      The date of the working schedule.
	 * @param timeSlots The time slots of the working schedule.
	 * @param barberId  The ID of the barber associated with the working schedule.
	 */
	public WorkingScheduleDTO(Long id, LocalDate date, List<LocalTime> timeSlots, Long barberId) {
		super();
		this.id = id;
		this.date = date;
		this.timeSlots = timeSlots;
		this.barberId = barberId;
	}

	/**
	 * Getters and setters, toString
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<LocalTime> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<LocalTime> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public Long getBarberId() {
		return barberId;
	}

	public void setBarberId(Long barberId) {
		this.barberId = barberId;
	}

	@Override
	public String toString() {
		return "WorkingScheduleDTO{" +
				"id=" + id +
				", date=" + date +
				", timeSlots=" + timeSlots +
				", barberId=" + barberId +
				'}';
	}
}