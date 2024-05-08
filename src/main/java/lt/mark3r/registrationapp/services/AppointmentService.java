/**
 * File: AppointmentService.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * <p>
 * The AppointmentService interface defines the contract for services related to appointments.
 * It declares methods for booking, retrieving, updating, and cancelling appointments, as well as for getting available
 * timeslots.
 * <p>
 * Methods:
 * - bookAppointment(AppointmentDTO appointmentDTO): Takes an AppointmentDTO object and books an appointment. Returns
 * the booked AppointmentDTO.
 * <p>
 * - getAppointment(Long id): Retrieves an appointment by its ID. Returns an Optional that contains the AppointmentDTO
 * if found, or is empty if not found.
 * <p>
 * - getAllAppointments(): Retrieves all appointments. Returns a List of AppointmentDTO objects.
 * <p>
 * - updateAppointment(AppointmentDTO appointmentDTO): Updates an appointment. Takes an AppointmentDTO object with the
 * updated details. Returns an Optional that contains the updated AppointmentDTO if the update was successful, or is
 * empty if not found.
 * <p>
 * - cancelAppointment(Long id): Cancels an appointment by its ID. Does not return a value.
 * <p>
 * - getAvailableTimeslots(LocalDate date, Long barberId): Gets the available timeslots for a specific date and barber.
 * Returns a List of Strings, where each String represents an available timeslot.
 */


package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.AppointmentDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

	AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO);

	Optional<AppointmentDTO> getAppointment(Long id);

	List<AppointmentDTO> getAllAppointments();

	Optional<AppointmentDTO> updateAppointment(AppointmentDTO appointmentDTO);

	void cancelAppointment(Long id);

	List<String> getAvailableTimeslots(LocalDate date, Long barberId);
}

