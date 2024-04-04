package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.BarberServ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberServRepository extends JpaRepository<BarberServ, Long> {
	// custom methods...
}