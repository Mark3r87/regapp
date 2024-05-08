/**
 * File: BarberServRepository.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the BarberServRepository interface, which is responsible for providing database operations for
 * BarberServ objects.
 */

package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.Barber;
import lt.mark3r.registrationapp.model.BarberServ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BarberServRepository extends JpaRepository<BarberServ, Long> {

	List<BarberServ> findAllByBarberId(Long barberId);

	Optional<BarberServ> findByIdAndBarberId(Long id, Long barberId);

	boolean existsByIdAndBarberId(Long id, Long barberId);

	Optional<BarberServ> findByIdAndBarber(Long id, Barber barber);
}
