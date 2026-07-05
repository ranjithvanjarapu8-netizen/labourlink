package com.ranji.labourlink.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkerProfession> workerProfessions = new ArrayList<>();
    private Integer experience;

    private Double latitude;

    private Double longitude;
    private String city;

    private String district;

    private String state;

    private Boolean available = true;

    private Double rating = 0.0;
    private Integer totalRatings = 0;
    private Integer totalJobs = 0;
    private String languages;
    
    private String aadhaarNumber;

    private String profilePhoto;
    
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();
    public Worker() {
    }
    
	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<WorkerProfession> getWorkerProfessions() {
	    return workerProfessions;
	}

	public void setWorkerProfessions(List<WorkerProfession> workerProfessions) {
	    this.workerProfessions = workerProfessions;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getTotalJobs() {
		return totalJobs;
	}

	public void setTotalJobs(Integer totalJobs) {
		this.totalJobs = totalJobs;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Worker(Long id, User user, List<WorkerProfession> workerProfessions, Integer experience, Double latitude,
			Double longitude, String city, String district, String state, Boolean available, Double rating,
			Integer totalRatings, Integer totalJobs, String languages, String aadhaarNumber, String profilePhoto,
			String description, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.workerProfessions = workerProfessions;
		this.experience = experience;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.district = district;
		this.state = state;
		this.available = available;
		this.rating = rating;
		this.totalRatings = totalRatings;
		this.totalJobs = totalJobs;
		this.languages = languages;
		this.aadhaarNumber = aadhaarNumber;
		this.profilePhoto = profilePhoto;
		this.description = description;
		this.createdAt = createdAt;
	}

	
	public Integer getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(Integer totalRatings) {
		this.totalRatings = totalRatings;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Worker(String description) {
		super();
		this.description = description;
	}

	@Override
	public String toString() {
		return "Worker [id=" + id + ", user=" + user + ", workerProfessions=" + workerProfessions + ", experience=" + experience
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", city=" + city + ", district=" + district
				+ ", state=" + state + ", available=" + available + ", rating=" + rating + ", totalRatings="
				+ totalRatings + ", totalJobs=" + totalJobs + ", languages=" + languages + ", aadhaarNumber="
				+ aadhaarNumber + ", profilePhoto=" + profilePhoto + ", description=" + description + ", createdAt="
				+ createdAt + "]";
	}

    // Getters and Setters
}