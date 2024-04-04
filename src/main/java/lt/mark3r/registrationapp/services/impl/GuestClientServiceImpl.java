package lt.mark3r.registrationapp.services.impl;

import lt.mark3r.registrationapp.dto.GuestClientDTO;
import lt.mark3r.registrationapp.mapper.GuestClientMapper;
import lt.mark3r.registrationapp.model.GuestClient;
import lt.mark3r.registrationapp.repository.GuestClientRepository;
import lt.mark3r.registrationapp.services.GuestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestClientServiceImpl implements GuestClientService {
	@Autowired
	private GuestClientRepository guestClientRepository;

	@Override
	public GuestClientDTO registerGuestClient(GuestClientDTO guestClientDTO) {
		GuestClient guestClient = GuestClientMapper.toEntity(guestClientDTO);
		GuestClient savedGuestClient = guestClientRepository.save(guestClient);
		return GuestClientMapper.toDTO(savedGuestClient);
	}

	@Override
	public Optional<GuestClientDTO> getGuestClient(Long id) {
		return guestClientRepository.findById(id).map(GuestClientMapper::toDTO);
	}

	@Override
	public List<GuestClientDTO> getAllGuestClients() {
		return guestClientRepository.findAll().stream().map(GuestClientMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<GuestClientDTO> updateGuestClient(Long id, GuestClientDTO guestClientDTO) {
		if (guestClientRepository.existsById(id)) {
			guestClientDTO.setId(id);
			GuestClient updatedGuestClient = GuestClientMapper.toEntity(guestClientDTO);
			GuestClient savedGuestClient = guestClientRepository.save(updatedGuestClient);
			return Optional.of(GuestClientMapper.toDTO(savedGuestClient));
		}
		return Optional.empty();
	}

	@Override
	public void deleteGuestClient(Long id) {
		guestClientRepository.deleteById(id);
	}
}
