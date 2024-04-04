package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.WorkingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface WorkingScheduleRepository extends JpaRepository<WorkingSchedule, Long> {
	List<WorkingSchedule> findByBarberId(Long barberId);

	List<WorkingSchedule> findByDate(LocalDate date);

	List<WorkingSchedule> findByBarberIdAndDate(Long barberId, LocalDate date);
}
