package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.model.GuestClient;

public class GuestClientMapper {
	public static GuestClient toEntity(GuestClientDTO dto) {
		GuestClient guestClient = new GuestClient();
		guestClient.setId(dto.getId());
		guestClient.setPhoneNumber(dto.getPhoneNumber());
		guestClient.setName(dto.getName());
		return guestClient;
	}

	public static GuestClientDTO toDTO(GuestClient guestClient) {
		GuestClientDTO dto = new GuestClientDTO();
		dto.setId(guestClient.getId());
		dto.setPhoneNumber(guestClient.getPhoneNumber());
		dto.setName(guestClient.getName());
		return dto;
	}
}
