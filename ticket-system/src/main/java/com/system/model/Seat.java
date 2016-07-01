package com.system.model;

import java.io.Serializable;

public class Seat implements Serializable {
	private static final long serialVersionUID = 9039540904740684426L;
	private int seatNo;
	private String ticketId;
	private boolean isLocked;
	private boolean isBooked;
	private Long lockedTime;
	
	public String getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	public Seat(int seatNo) {
		this.seatNo = seatNo;
	}
	
	public int getSeatNo() {
		return seatNo;
	}
	
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	public boolean isLocked() {
		return isLocked;
	}
	
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	public boolean isBooked() {
		return isBooked;
	}
	
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	public Long getLockedTime() {
		return lockedTime;
	}
	
	public void setLockedTime(Long lockedTime) {
		this.lockedTime = lockedTime;
	}
}
