package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.TypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
	List<Barber> findByServicesOfferedContains(TypeOfService service);
}
