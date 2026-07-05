package com.ranji.labourlink.dto;

public class WorkDto {

    private Long id;
    private String name;
    private Integer price;
    private Integer estimatedHours;
    private String description;

    public WorkDto() {}

    public WorkDto(Long id, String name, Integer price,
                   Integer estimatedHours, String description) {
        this.id = id;
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

    // Getters & Setters
}
