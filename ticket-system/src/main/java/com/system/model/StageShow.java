package com.system.model;

import java.io.Serializable;
import java.util.List;

public class StageShow implements Serializable {
	private static final long serialVersionUID = 6426010556071101687L;
	private String showId;
	List<Balcony> availableBalconies;
	
	public String getShowId() {
		return showId;
	}
	
	public void setShowId(String showId) {
		this.showId = showId;
	}
	
	public StageShow(List<Balcony> availableBalconies) {
		super();
		this.availableBalconies = availableBalconies;
	}
	
	public List<Balcony> getAvailableBalconies() {
		return availableBalconies;
	}
	
	public void setAvailableBalconies(List<Balcony> availableBalconies) {
		this.availableBalconies = availableBalconies;
	}
	
	public Balcony getBalcony(String balconyType) {
		if (availableBalconies != null)
			for (Balcony balcony : availableBalconies) {
				if (balcony.getType().equals(balconyType))
					return balcony;
			}
		return null;
	}
}
