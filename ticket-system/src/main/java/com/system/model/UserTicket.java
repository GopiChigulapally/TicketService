package com.system.model;

import java.io.Serializable;
import java.util.List;

public class UserTicket implements Serializable {
	private static final long serialVersionUID = 2087736276017772636L;
	private String ticketId;
	private String balconyType;
	private List<Integer> seatNos;
	
	public UserTicket(String ticketId, String balconyType, List<Integer> seatNos) {
		super();
		this.ticketId = ticketId;
		this.balconyType = balconyType;
		this.seatNos = seatNos;
	}
	
	public String getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	public String getBalconyType() {
		return balconyType;
	}
	
	public void setBalconyType(String balconyType) {
		this.balconyType = balconyType;
	}
	
	public List<Integer> getSeatNos() {
		return seatNos;
	}
	
	public void setSeatNos(List<Integer> seatNos) {
		this.seatNos = seatNos;
	}
}
