/**
 * File: WorkingSchedule.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the WorkingSchedule class, which represents a working schedule of a barber in the system.
 * Each WorkingSchedule is associated with a specific Barber.
 */


package lt.mark3r.registrationapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class WorkingSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	@ElementCollection
	private List<LocalTime> timeSlots;
	@ManyToOne
	@JoinColumn(name = "barber_id", nullable = false)
	private Barber barber;

	public WorkingSchedule() {
	}

	public WorkingSchedule(Long id, LocalDate date, List<LocalTime> timeSlots, Barber barber) {
		this.id = id;
		this.date = date;
		this.timeSlots = timeSlots;
		this.barber = barber;
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

	public Barber getBarber() {
		return barber;
	}

	public void setBarber(Barber barber) {
		this.barber = barber;
	}

	@Override
	public String toString() {
		return "WorkingSchedule{" +
				"id=" + id +
				", date=" + date +
				", timeSlots=" + timeSlots +
				", barber=" + barber +
				'}';
	}
}
