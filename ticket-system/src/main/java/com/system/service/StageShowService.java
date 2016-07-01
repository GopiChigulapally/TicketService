package com.system.service;

import java.util.List;

import com.system.model.StageShow;
import com.system.model.User;
import com.system.ui.Response;

public interface StageShowService {
	public StageShow getShowDetails();
	
	public Response holdSeats(String balconyType, List<Integer> seatNos);
	
	public Response reserveSeats(String emailId, String userName, String balconyType, List<Integer> seatNos);
	
	public User getTicketInfo(String emailId);
	
	public void unLockSelectedSeatsScheduler();
}
