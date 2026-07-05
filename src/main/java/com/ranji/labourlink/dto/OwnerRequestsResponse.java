package com.ranji.labourlink.dto;

import java.util.List;

public class OwnerRequestsResponse {

	private int totalRequests;
    private int upcomingCount;
    private int pastCount;

   
	private List<OwnerRequestDto> upcoming;
    private List<OwnerRequestDto> past;

    public OwnerRequestsResponse() {
    }

    public OwnerRequestsResponse(int totalRequests, int upcomingCount, int pastCount, List<OwnerRequestDto> upcoming,
			List<OwnerRequestDto> past) {
		super();
		this.totalRequests = totalRequests;
		this.upcomingCount = upcomingCount;
		this.pastCount = pastCount;
		this.upcoming = upcoming;
		this.past = past;
	}


    public int getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(int totalRequests) {
		this.totalRequests = totalRequests;
	}

	public int getUpcomingCount() {
		return upcomingCount;
	}

	public void setUpcomingCount(int upcomingCount) {
		this.upcomingCount = upcomingCount;
	}

	public int getPastCount() {
		return pastCount;
	}

	public void setPastCount(int pastCount) {
		this.pastCount = pastCount;
	}

	public List<OwnerRequestDto> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(List<OwnerRequestDto> upcoming) {
        this.upcoming = upcoming;
    }

    public List<OwnerRequestDto> getPast() {
        return past;
    }

    public void setPast(List<OwnerRequestDto> past) {
        this.past = past;
    }
}