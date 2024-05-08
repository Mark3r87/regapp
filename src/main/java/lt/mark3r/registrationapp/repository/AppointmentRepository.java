/**
 * File: AppointmentRepository.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the AppointmentRepository interface, which is responsible for providing database operations
 * for Appointment objects.
 */

package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.Appointment;
import lt.mark3r.registrationapp.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByBarberId(Long barberId);

	List<Appointment> findByDate(LocalDate date);

	List<Appointment> findByBarberIdAndDate(Long barberId, LocalDate date);

	List<Appointment> findByBarberAndDate(Barber barber, LocalDate date);
}
