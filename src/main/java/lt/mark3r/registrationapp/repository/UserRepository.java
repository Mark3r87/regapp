/**
 * File: UserRepository.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the UserRepository interface, which is responsible for providing database operations for
 * AppUser objects.
 */


package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	boolean existsByEmail(String email);

	Optional<AppUser> findByEmail(String email);
}