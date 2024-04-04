package lt.mark3r.registrationapp.mapper;

import lt.mark3r.registrationapp.dto.BarberServDTO;
import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.BarberServ;

public class BarberServMapper {
	public static BarberServ toEntity(BarberServDTO dto, Barber barber) {
		BarberServ barberService = new BarberServ();
		barberService.setBarber(barber);
		barberService.setService(dto.getService());
		barberService.setDefaultDurationInMinutes(dto.getDefaultDurationInMinutes());
		return barberService;
	}

	public static BarberServDTO toDTO(BarberServ barberService) {
		BarberServDTO dto = new BarberServDTO();
		if (barberService.getBarber() != null) {
			dto.setBarberId(barberService.getBarber().getId());
		}
		dto.setService(barberService.getService());
		dto.setDefaultDurationInMinutes(barberService.getDefaultDurationInMinutes());
		return dto;
	}
}