package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.model.GuestClient;

public class GuestClientMapper {
	public static GuestClient toEntity(GuestClientDTO dto) {
		GuestClient guestClient = new GuestClient();
		guestClient.setId(dto.getId());
		guestClient.setPhoneNumber(dto.getPhoneNumber());
		guestClient.setEmail(dto.getEmail());
		// Assuming Appointment is managed elsewhere
		return guestClient;
	}

	public static GuestClientDTO toDTO(GuestClient guestClient) {
		GuestClientDTO dto = new GuestClientDTO();
		dto.setId(guestClient.getId());
		dto.setPhoneNumber(guestClient.getPhoneNumber());
		dto.setEmail(guestClient.getEmail());
		// Assuming you handle Appointment separately
		return dto;
	}
}
