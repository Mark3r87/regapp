package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.model.Appointment;

public class AppointmentMapper {
	public static Appointment toEntity(AppointmentDTO dto) {
		Appointment appointment = new Appointment();
		appointment.setId(dto.getId());
		appointment.setDate(dto.getDate());
		appointment.setTime(dto.getTime());

		return appointment;
	}

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
}
