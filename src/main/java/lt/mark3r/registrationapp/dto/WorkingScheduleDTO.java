package lt.mark3r.registrationapp.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class WorkingScheduleDTO {
	private Long id;
	private LocalDate date;
	private List<LocalTime> timeSlots;
	private Long barberId;

	public WorkingScheduleDTO() {
	}

	public WorkingScheduleDTO(Long id, LocalDate date, List<LocalTime> timeSlots, Long barberId) {
		super();
		this.id = id;
		this.date = date;
		this.timeSlots = timeSlots;
		this.barberId = barberId;
	}

	// Getters and Setters


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