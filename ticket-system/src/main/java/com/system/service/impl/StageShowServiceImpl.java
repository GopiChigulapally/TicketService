package com.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.constants.CommonConstants;
import com.system.model.Balcony;
import com.system.model.Seat;
import com.system.model.StageShow;
import com.system.model.User;
import com.system.model.UserTicket;
import com.system.service.StageShowService;
import com.system.ui.Response;
import com.system.util.ShowSystem;

public class StageShowServiceImpl implements StageShowService {
	@Autowired
	private ShowSystem showSystem;
	
	public void setShowSystem(ShowSystem showSystem) {
		this.showSystem = showSystem;
	}
	
	public StageShow getShowDetails() {
		return showSystem.getStageShow();
	}
	
	public Response holdSeats(String balconyType, List<Integer> seatNos) {
		StageShow stageShow = showSystem.getStageShow();
		Balcony balcony = stageShow.getBalcony(balconyType);
		if (balcony != null) {
			List<List<Seat>> allSeats = balcony.getSeats();
			for (List<Seat> seats : allSeats) {
				for (Seat seat : seats) {
					if (seatNos.contains(seat.getSeatNo()))
						if (!seat.isLocked() && !seat.isBooked()) {
							seat.setLocked(true);
							seat.setLockedTime(new Date().getTime());
						} else {
							return new Response("Few/All selected seats were  locked/already reserved",
									CommonConstants.FAILED);
						}
				}
			}
		}
		return new Response("Locked selected seats", CommonConstants.SUCCESS);
	}
	
	public Response reserveSeats(String emailId, String userName, String balconyType, List<Integer> seatNos) {
		if (seatNos == null || seatNos.isEmpty())
			return new Response("No seats selected", CommonConstants.FAILED);
		StageShow stageShow = showSystem.getStageShow();
		Balcony balcony = stageShow.getBalcony(balconyType);
		List<Seat> confirmingSeats = new ArrayList<Seat>();
		if (balcony != null) {
			List<List<Seat>> allSeats = balcony.getSeats();
			for (List<Seat> seats : allSeats) {
				for (Seat seat : seats) {
					if (seatNos.contains(seat.getSeatNo()) && seat.isLocked()) {
						seat.setBooked(true);
						seat.setLocked(false);
						seat.setLockedTime(null);
						confirmingSeats.add(seat);
					}
				}
			}
		}
		if (confirmingSeats.isEmpty())
			return new Response("No seats selected", CommonConstants.FAILED);
		User user = showSystem.getUser(emailId);
		if (user == null)
			user = showSystem.addUser(emailId);
		String ticketId = UUID.randomUUID().toString();
		UserTicket userTicket = new UserTicket(ticketId, balconyType, seatNos);
		user.addUserTicket(userTicket);
		return new Response("Confirmed, TicketId :" + ticketId, CommonConstants.SUCCESS);
	}
	
	public User getTicketInfo(String emailId) {
		User user = showSystem.getUser(emailId);
		return user;
	}
	
	public void unLockSelectedSeatsScheduler() {
		StageShow stageShow = showSystem.getStageShow();
		List<Balcony> balconies = stageShow.getAvailableBalconies();
		if (balconies != null) {
			for (Balcony balcony : balconies) {
				List<List<Seat>> allSeats = balcony.getSeats();
				for (List<Seat> seats : allSeats) {
					for (Seat seat : seats) {
						if (isSeatExpired(seat)) {
							seat.setLocked(false);
							seat.setLockedTime(null);
						}
					}
				}
			}
		}
	}
	
	private boolean isSeatExpired(Seat seat) {
		if (seat.isBooked())
			return false;
		if (seat.isLocked()) {
			long lockedTime = seat.getLockedTime();
			long seatLockingTime = showSystem.getSeatLockingTime();
			if (new Date().getTime() - lockedTime > seatLockingTime)
				return true;
		}
		return false;
	}
}
