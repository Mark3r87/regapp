package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.BarberDTO;
import lt.mark3r.registrationapp.model.Barber;

public class BarberMapper {
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

