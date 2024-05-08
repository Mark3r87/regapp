/**
 * File: GuestClientRepository.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the GuestClientRepository interface, which is responsible for providing database operations for
 * GuestClient objects.
 */

package lt.mark3r.registrationapp.repository;

import lt.mark3r.registrationapp.model.GuestClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GuestClientRepository extends JpaRepository<GuestClient, Long> {
	GuestClient findByPhoneNumber(String phoneNumber);

	GuestClient findByName(String name);
}
