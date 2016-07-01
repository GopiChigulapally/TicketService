package com.system.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = -3190445945441712836L;
	private List<UserTicket> userTicket;
	private String emailId;
	
	public User(String emailId) {
		super();
		this.emailId = emailId;
	}
	
	public List<UserTicket> getUserTicket() {
		return userTicket;
	}
	
	public void setUserTicket(List<UserTicket> userTicket) {
		this.userTicket = userTicket;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public void addUserTicket(UserTicket userTicket) {
		if (this.userTicket == null)
			this.userTicket = new ArrayList<UserTicket>();
		this.userTicket.add(userTicket);
	}
}
