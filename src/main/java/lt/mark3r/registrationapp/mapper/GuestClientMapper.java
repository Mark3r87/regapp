/**
 * File: GuestClientMapper.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the GuestClientMapper class, which is responsible for mapping between GuestClient and
 * GuestClientDTO objects.
 */

package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.model.GuestClient;

/**
 * The GuestClientMapper class is responsible for mapping between GuestClient and GuestClientDTO objects.
 * It provides methods to map from a GuestClient entity to a GuestClientDTO, and vice versa.
 */
public class GuestClientMapper {

	/**
	 * Maps a GuestClientDTO to a GuestClient entity.
	 */
	public static GuestClient toEntity(GuestClientDTO dto) {
		GuestClient guestClient = new GuestClient();
		guestClient.setId(dto.getId());
		guestClient.setPhoneNumber(dto.getPhoneNumber());
		guestClient.setName(dto.getName());
		return guestClient;
	}


	/**
	 * Maps a GuestClient entity to a GuestClientDTO object.
	 */
	public static GuestClientDTO toDTO(GuestClient guestClient) {
		GuestClientDTO dto = new GuestClientDTO();
		dto.setId(guestClient.getId());
		dto.setPhoneNumber(guestClient.getPhoneNumber());
		dto.setName(guestClient.getName());
		return dto;
	}
}
