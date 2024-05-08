/**
 * File: AppointmentMapper.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AppointmentMapper class, which is responsible for mapping between Appointment and AppointmentDTO objects.
 */

package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.model.Appointment;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.GuestClient;
import lt.mark3r.registrationapp.repository.BarberRepository;
import lt.mark3r.registrationapp.repository.GuestClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The AppointmentMapper class is responsible for mapping between Appointment and AppointmentDTO objects.
 * It uses the BarberRepository and GuestClientRepository to retrieve Barber and GuestClient entities when mapping
 * from DTO to entity.
 */
public class AppointmentMapper {

	@Autowired
	private static BarberRepository barberRepository;

	@Autowired
	private static GuestClientRepository guestClientRepository;

	/**
	 * Maps an Appointment entity to an AppointmentDTO.
	 */
	public static AppointmentDTO toDTO(Appointment appointment) {
		AppointmentDTO dto = new AppointmentDTO();
		dto.setId(appointment.getId());
		dto.setDate(appointment.getDate());
		dto.setTime(appointment.getTime());
		if (appointment.getBarber() != null) {
			dto.setBarberId(appointment.getBarber().getId());
		}
		dto.setService(appointment.getService());
		if (appointment.getGuestClient() != null) {
			dto.setGuestClientId(appointment.getGuestClient().getId());
		}
		return dto;
	}

	/**
	 * Maps an AppointmentDTO to an Appointment entity.
	 * It uses the BarberRepository and GuestClientRepository to retrieve Barber and GuestClient entities.
	 */
	public static Appointment toEntity(AppointmentDTO dto) {
		Appointment appointment = new Appointment();
		appointment.setId(dto.getId());
		appointment.setDate(dto.getDate());
		appointment.setTime(dto.getTime());

		Barber barber = barberRepository.findById(dto.getBarberId())
				.orElseThrow(() -> new RuntimeException("Barber not found"));
		appointment.setBarber(barber);

		appointment.setService(dto.getService());

		GuestClient guestClient = guestClientRepository.findById(dto.getGuestClientId())
				.orElseThrow(() -> new RuntimeException("Guest client not found"));
		appointment.setGuestClient(guestClient);

		return appointment;
	}

	/**
	 * Maps an AppointmentDTO to an Appointment entity.
	 * It uses provided Barber and GuestClient entities.
	 */
	public static Appointment toEntity(AppointmentDTO dto, Barber barber, GuestClient guestClient) {
		Appointment appointment = new Appointment();
		appointment.setId(dto.getId());
		appointment.setDate(dto.getDate());
		appointment.setTime(dto.getTime());
		appointment.setBarber(barber);
		appointment.setService(dto.getService());
		appointment.setGuestClient(guestClient);

		return appointment;
	}
}
