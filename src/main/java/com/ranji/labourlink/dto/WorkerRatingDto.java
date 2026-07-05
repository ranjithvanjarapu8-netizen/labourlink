package com.ranji.labourlink.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WorkerRatingDto {
	@NotNull
    private Long requestId;
    
    @NotNull
    @Min(1)
    @Max(5)
    private Integer stars;

    public WorkerRatingDto() {
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}