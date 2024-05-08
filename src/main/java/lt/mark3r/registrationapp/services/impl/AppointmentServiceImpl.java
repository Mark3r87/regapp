/**
 * File: AppointmentServiceImpl.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 */

package lt.mark3r.registrationapp.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.mapper.AppointmentMapper;
import lt.mark3r.registrationapp.model.Appointment;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.GuestClient;
import lt.mark3r.registrationapp.model.WorkingSchedule;
import lt.mark3r.registrationapp.repository.AppointmentRepository;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.GuestClientRepository;
import lt.mark3r.registrationapp.repository.WorkingScheduleRepository;
import lt.mark3r.registrationapp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The AppointmentServiceImpl class is an implementation of the AppointmentService interface.
 * It provides the business logic for managing appointments in the application.
 * Annotations:
 * - @Service: Indicates that the class is a service and should be automatically detected by Spring's component
 * scanning.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private BarberRepository barberRepository;

	@Autowired
	private WorkingScheduleRepository workingScheduleRepository;

	@Autowired
	private GuestClientRepository guestClientRepository;


	/**
	 * Books an appointment for a guest client with a specific barber.
	 * It throws an EntityNotFoundException if the barber or guest client is not found.
	 */
	@Override
	public AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO) {
		Barber barber = barberRepository.findById(appointmentDTO.getBarberId())
				.orElseThrow(() -> new EntityNotFoundException("Barber not found with id: " + appointmentDTO.getBarberId()));
		GuestClient guestClient = guestClientRepository.findById(appointmentDTO.getGuestClientId())
				.orElseThrow(() -> new EntityNotFoundException("Guest client not found with id: " + appointmentDTO.getGuestClientId()));

		Appointment appointment = AppointmentMapper.toEntity(appointmentDTO, barber, guestClient);
		Appointment savedAppointment = appointmentRepository.save(appointment);
		return AppointmentMapper.toDTO(savedAppointment);
	}

	/**
	 * Retrieves an appointment by its ID.
	 */
	@Override
	public Optional<AppointmentDTO> getAppointment(Long id) {
		return appointmentRepository.findById(id)
				.map(AppointmentMapper::toDTO);
	}

	/**
	 * Retrieves all appointments.
	 */
	@Override
	public List<AppointmentDTO> getAllAppointments() {
		return appointmentRepository.findAll()
				.stream()
				.map(AppointmentMapper::toDTO)
				.collect(Collectors.toList());
	}


	/**
	 * Updates an existing appointment.
	 * It throws an EntityNotFoundException if the appointment is not found.
	 */
	@Override
	public Optional<AppointmentDTO> updateAppointment(AppointmentDTO appointmentDTO) {
		Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentDTO.getId());
		if (existingAppointment.isPresent()) {
			Appointment updatedAppointment = appointmentRepository.save(AppointmentMapper.toEntity(appointmentDTO));
			return Optional.of(AppointmentMapper.toDTO(updatedAppointment));
		}
		return Optional.empty();
	}


	/**
	 * Cancels an appointment by its ID.
	 */
	@Override
	public void cancelAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}


	/**
	 * Retrieves available timeslots for a given date and barber.
	 * It throws a RuntimeException if the barber or working schedule is not found.
	 */
	@Override
	public List<String> getAvailableTimeslots(LocalDate date, Long barberId) {
		Barber barber = barberRepository.findById(barberId).orElseThrow(() -> new RuntimeException("Barber not found"));
		WorkingSchedule workingSchedule = workingScheduleRepository.findByBarberAndDate(barber, date)
				.orElseThrow(() -> new RuntimeException("Working schedule not found for the given date"));

		List<String> workingHours = workingSchedule.getTimeSlots().stream()
				.map(LocalTime::toString)
				.collect(Collectors.toList());

		List<Appointment> appointments = appointmentRepository.findByBarberAndDate(barber, date);

		List<String> bookedSlots = appointments.stream()
				.map(Appointment::getTimeSlot)
				.collect(Collectors.toList());

		List<String> availableSlots = workingHours.stream()
				.filter(slot -> !bookedSlots.contains(slot))
				.collect(Collectors.toList());

		return availableSlots;
	}
}