package lt.mark3r.registrationapp.services;

import lt.mark3r.registrationapp.dto.GuestClientDTO;

import java.util.List;
import java.util.Optional;

public interface GuestClientService {
	GuestClientDTO registerGuestClient(GuestClientDTO guestClientDTO);

	Optional<GuestClientDTO> getGuestClient(Long id);

	List<GuestClientDTO> getAllGuestClients();

	Optional<GuestClientDTO> updateGuestClient(Long id, GuestClientDTO guestClientDTO);

	void deleteGuestClient(Long id);
}
