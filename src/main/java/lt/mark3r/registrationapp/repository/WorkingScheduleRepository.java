/**
 * File: WorkingScheduleRepository.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the WorkingScheduleRepository interface, which is responsible for providing database operations
 * for WorkingSchedule objects.
 */

package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.WorkingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkingScheduleRepository extends JpaRepository<WorkingSchedule, Long> {
	List<WorkingSchedule> findByBarberId(Long barberId);

	Optional<WorkingSchedule> findByIdAndBarberId(Long id, Long barberId);

	List<WorkingSchedule> findByDate(LocalDate date);

	List<WorkingSchedule> findByBarberIdAndDate(Long barberId, LocalDate date);

	boolean existsByIdAndBarberId(Long id, Long barberId);

	List<WorkingSchedule> findAllByBarberId(Long barberId);

	Optional<WorkingSchedule> findByBarberAndDate(Barber barber, LocalDate date);

}
