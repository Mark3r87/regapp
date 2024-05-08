/**
 * File: BarberMapper.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the BarberMapper class, which is responsible for mapping between Barber and BarberDTO objects.
 */

package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.model.Barber;


/**
 * The BarberMapper class is responsible for mapping between Barber and BarberDTO objects.
 * It provides methods to map from a Barber entity to a BarberDTO, and vice versa.
 */
public class BarberMapper {

	/**
	 * Maps a BarberDTO to a Barber entity.
	 */
	public static Barber toEntity(BarberDTO dto) {
		Barber barber = new Barber();
		barber.setId(dto.getId());
		barber.setName(dto.getName());
		barber.setLocation(dto.getLocation());
		barber.setContactInformation(dto.getContactInformation());
		barber.setRating(dto.getRating());
		barber.setSpecialties(dto.getSpecialties());
		barber.setServicesOffered(dto.getServicesOffered());
		barber.setHasBeenRated(dto.isHasBeenRated());
		return barber;
	}

	/**
	 * Maps a Barber entity to a BarberDTO object.
	 */
	public static BarberDTO toDTO(Barber barber) {
		BarberDTO dto = new BarberDTO();
		dto.setId(barber.getId());
		dto.setName(barber.getName());
		dto.setLocation(barber.getLocation());
		dto.setContactInformation(barber.getContactInformation());
		dto.setRating(barber.getRating());
		dto.setSpecialties(barber.getSpecialties());
		dto.setServicesOffered(barber.getServicesOffered());
		dto.setHasBeenRated(barber.isHasBeenRated());
		return dto;
	}
}

