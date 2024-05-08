/**
 * File: Barber.java
 * Author: Gediminas Kaminskas
 * Date: 2024-05-08
 * This file contains the Barber class, which represents a barber in the system.
 * Each barber is associated with a list of working schedules, a list of specialties, and a list of services offered.
 */

package lt.mark3r.registrationapp.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Barber {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "app_user_id", referencedColumnName = "id")
	private AppUser appUser;
	@OneToMany(mappedBy = "barber", cascade = CascadeType.ALL)
	private List<WorkingSchedule> workingSchedules;
	private String location;
	@ElementCollection
	private List<String> specialties;
	private String contactInformation;
	private Double rating;
	@ElementCollection(targetClass = TypeOfService.class)
	@Enumerated(EnumType.STRING)
	private List<TypeOfService> servicesOffered;
	private boolean hasBeenRated;


	public Barber() {
	}


	public Barber(Long id, String name, List<WorkingSchedule> workingSchedules,
	              String location, List<String> specialties,
	              String contactInformation, Double rating,
	              List<TypeOfService> servicesOffered, boolean hasBeenRated, AppUser appUser) {
		this.id = id;
		this.name = name;
		this.workingSchedules = workingSchedules;
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
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
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

	public List<WorkingSchedule> getWorkingSchedules() {
		return workingSchedules;
	}

	public void setWorkingSchedules(List<WorkingSchedule> workingSchedules) {
		this.workingSchedules = workingSchedules;
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
		return "Barber{" +
				"id=" + id +
				", name='" + name + '\'' +
				", workingSchedules=" + workingSchedules +
				", location='" + location + '\'' +
				", specialties=" + specialties +
				", contactInformation='" + contactInformation + '\'' +
				", rating=" + rating +
				", servicesOffered=" + servicesOffered +
				", hasBeenRated=" + hasBeenRated +
				'}';
	}
}
