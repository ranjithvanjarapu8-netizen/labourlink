package com.ranji.labourlink.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(nullable = false)
    private String profession;

    private Integer experience;

    private Double latitude;

    private Double longitude;

    private Boolean available = true;

    private Double rating = 0.0;

    private Integer totalJobs = 0;

    private String aadhaarNumber;

    private String profilePhoto;

    private LocalDateTime createdAt = LocalDateTime.now();
    public Worker() {
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
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

	public Worker(Long id, User user, String profession, Integer experience, Double latitude, Double longitude,
			Boolean available, Double rating, Integer totalJobs, String aadhaarNumber, String profilePhoto,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.profession = profession;
		this.experience = experience;
		this.latitude = latitude;
		this.longitude = longitude;
		this.available = available;
		this.rating = rating;
		this.totalJobs = totalJobs;
		this.aadhaarNumber = aadhaarNumber;
		this.profilePhoto = profilePhoto;
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Worker [id=" + id + ", user=" + user + ", profession=" + profession + ", experience=" + experience
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", available=" + available + ", rating="
				+ rating + ", totalJobs=" + totalJobs + ", aadhaarNumber=" + aadhaarNumber + ", profilePhoto="
				+ profilePhoto + ", createdAt=" + createdAt + "]";
	}

    // Getters and Setters
}