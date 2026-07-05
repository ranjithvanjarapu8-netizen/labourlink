package com.ranji.labourlink.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WrkWage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="profession_id")
    private Profession profession;

    private String name;

    private Integer price;

    private Integer estimatedHours;

    private String description;
    public WrkWage() {
    	
    }
	public WrkWage(Long id, Profession profession, String name, Integer price, Integer estimatedHours,
			String description) {
		super();
		this.id = id;
		this.profession = profession;
		this.name = name;
		this.price = price;
		this.estimatedHours = estimatedHours;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(Integer estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "WrkWage [id=" + id + ", profession=" + profession + ", name=" + name + ", price=" + price
				+ ", estimatedHours=" + estimatedHours + ", description=" + description + "]";
	}
    
    
}