package com.ranji.labourlink.Model;

import jakarta.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
    
   
	@Column(name = "daily_wage")
    private Integer dailyWage;
	
	@OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WorkerProfession> workerProfessions = new ArrayList<>();
    
    public Profession() {
    	
    }
	public Profession(Long id, String name,String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Profession(Integer dailyWage) {
		super();
		this.dailyWage = dailyWage;
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
	public List<WorkerProfession> getWorkerProfessions() {
	    return workerProfessions;
	}

	public void setWorkerProfessions(List<WorkerProfession> workerProfessions) {
	    this.workerProfessions = workerProfessions;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	public Integer getDailyWage() {
		return dailyWage;
	}
	public void setDailyWage(Integer dailyWage) {
		this.dailyWage = dailyWage;
	}


	@Override
	public String toString() {
		return "Profession [id=" + id + ", name=" + name + ", description=" + description + ", dailyWage=" + dailyWage
				+ "]";
	}
    

}