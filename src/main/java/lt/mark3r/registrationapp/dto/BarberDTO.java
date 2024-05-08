/**
 * File: BarberDTO.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the BarberDTO class, which is a Data Transfer Object (DTO) for barbers.
 */

package lt.mark3r.registrationapp.dto;

import lt.mark3r.registrationapp.model.TypeOfService;

import java.util.List;

/**
 * The BarberDTO class is a Data Transfer Object (DTO) for barbers.
 * It contains information about a barber, including its ID, name, location, specialties, contact information, rating,
 * services offered, rating status, and associated AppUser.
 */
public class BarberDTO {
	private Long id;
	private String name;
	private String location;
	private List<String> specialties;
	private String contactInformation;
	private Double rating;
	private List<TypeOfService> servicesOffered;
	private boolean hasBeenRated;
	private AppUserDTO appUser;

	/**
	 * Default constructor.
	 */
	public BarberDTO() {
	}

	/**
	 * Constructor with parameters.
	 *
	 * @param id                 The ID of the barber.
	 * @param name               The name of the barber.
	 * @param location           The location of the barber.
	 * @param specialties        The specialties of the barber.
	 * @param contactInformation The contact information of the barber.
	 * @param rating             The rating of the barber.
	 * @param servicesOffered    The services offered by the barber.
	 * @param hasBeenRated       The rating status of the barber.
	 * @param appUser            The associated AppUser of the barber.
	 */
	public BarberDTO(Long id, String name, String location,
	                 List<String> specialties, String contactInformation,
	                 Double rating, List<TypeOfService> servicesOffered,
	                 boolean hasBeenRated, AppUserDTO appUser) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.specialties = specialties;
		this.contactInformation = contactInformation;
		this.rating = rating;
		this.servicesOffered = servicesOffered;
		this.hasBeenRated = hasBeenRated;
		this.appUser = appUser;
	}

	/**
	 * Getters and setters, toString
	 */
	public AppUserDTO getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUserDTO appUser) {
		this.appUser = appUser;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<String> specialties) {
		this.specialties = specialties;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public List<TypeOfService> getServicesOffered() {
		return servicesOffered;
	}

	public void setServicesOffered(List<TypeOfService> servicesOffered) {
		this.servicesOffered = servicesOffered;
	}

	public boolean isHasBeenRated() {
		return hasBeenRated;
	}

	public void setHasBeenRated(boolean hasBeenRated) {
		this.hasBeenRated = hasBeenRated;
	}

	@Override
	public String toString() {
		return "BarberDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", location='" + location + '\'' +
				", specialties=" + specialties +
				", contactInformation='" + contactInformation + '\'' +
				", rating=" + rating +
				", servicesOffered=" + servicesOffered +
				", hasBeenRated=" + hasBeenRated +
				", appUser=" + appUser + // added this line
				'}';
	}
}
