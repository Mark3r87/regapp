package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.AppointmentDTO;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

	AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO);

	Optional<AppointmentDTO> getAppointment(Long id);

	List<AppointmentDTO> getAllAppointments();

	Optional<AppointmentDTO> updateAppointment(AppointmentDTO appointmentDTO);

	void cancelAppointment(Long id);
}

