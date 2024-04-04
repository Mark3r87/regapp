package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.GuestClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GuestClientRepository extends JpaRepository<GuestClient, Long> {
	GuestClient findByPhoneNumber(String phoneNumber);

	GuestClient findByEmail(String email);
}
