package lt.mark3r.registrationapp.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Barber {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "barber")
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


	public Barber(Long id, String name, List<WorkingSchedule> workingSchedules, String location, List<String> specialties, String contactInformation, Double rating, List<TypeOfService> servicesOffered, boolean hasBeenRated) {
		this.id = id;
		this.name = name;
		this.workingSchedules = workingSchedules;
		this.location = location;
		this.specialties = specialties;
		this.contactInformation = contactInformation;
		this.rating = rating;
		this.servicesOffered = servicesOffered;
		this.hasBeenRated = hasBeenRated;
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
