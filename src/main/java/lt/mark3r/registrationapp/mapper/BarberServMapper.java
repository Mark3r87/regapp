/**
 * File: BarberServMapper.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the BarberServMapper class, which is responsible for mapping between BarberServ and BarberServDTO
 * objects.
 */

package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.BarberServ;

public class BarberServMapper {

	/**
	 * The BarberServMapper class is responsible for mapping between BarberServ and BarberServDTO objects.
	 * It provides methods to map from a BarberServ entity to a BarberServDTO, and vice versa.
	 */
	public static BarberServ toEntity(BarberServDTO dto, Barber barber) {
		BarberServ barberService = new BarberServ();
		barberService.setBarber(barber);
		barberService.setService(dto.getService());
		barberService.setDefaultDurationInMinutes(dto.getDefaultDurationInMinutes());
		return barberService;
	}

	/**
	 * Maps a BarberServDTO, a Barber entity, and an existing BarberServ entity to a BarberServ entity.
	 */
	public static BarberServ toEntity(BarberServDTO dto, Barber barber, BarberServ existingBarberServ) {
		existingBarberServ.setBarber(barber);
		existingBarberServ.setService(dto.getService());
		existingBarberServ.setDefaultDurationInMinutes(dto.getDefaultDurationInMinutes());
		return existingBarberServ;
	}

	/**
	 * Maps a BarberServ entity to a BarberServDTO object.
	 */
	public static BarberServDTO toDTO(BarberServ barberService) {
		BarberServDTO dto = new BarberServDTO();
		dto.setId(barberService.getId());
		if (barberService.getBarber() != null) {
			dto.setBarberId(barberService.getBarber().getId());
		}
		dto.setService(barberService.getService());
		dto.setDefaultDurationInMinutes(barberService.getDefaultDurationInMinutes());
		return dto;
	}
}