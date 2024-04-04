package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.AppointmentDTO;
import lt.mark3r.registrationapp.mapper.AppointmentMapper;
import lt.mark3r.registrationapp.model.Appointment;
import lt.mark3r.registrationapp.repository.AppointmentRepository;
import lt.mark3r.registrationapp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public AppointmentDTO bookAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = AppointmentMapper.toEntity(appointmentDTO);
		Appointment savedAppointment = appointmentRepository.save(appointment);
		return AppointmentMapper.toDTO(savedAppointment);
	}

	@Override
	public Optional<AppointmentDTO> getAppointment(Long id) {
		return appointmentRepository.findById(id)
				.map(AppointmentMapper::toDTO);
	}

	@Override
	public List<AppointmentDTO> getAllAppointments() {
		return appointmentRepository.findAll()
				.stream()
				.map(AppointmentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<AppointmentDTO> updateAppointment(AppointmentDTO appointmentDTO) {
		Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentDTO.getId());
		if (existingAppointment.isPresent()) {
			Appointment updatedAppointment = appointmentRepository.save(AppointmentMapper.toEntity(appointmentDTO));
			return Optional.of(AppointmentMapper.toDTO(updatedAppointment));
		}
		return Optional.empty();
	}

	@Override
	public void cancelAppointment(Long id) {
		appointmentRepository.deleteById(id);
	}
}