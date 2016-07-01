package com.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.system.constants.CommonConstants;
import com.system.model.Balcony;
import com.system.model.StageShow;
import com.system.model.User;

public class ShowSystem {
	private List<StageShow> stageShows = new ArrayList<StageShow>();
	private Map<String, User> systemUser = new HashMap<String, User>();
	private long seatLockingTime;
	
	public void init() {
		List<Balcony> balconies = new ArrayList<Balcony>();
		String availableBusinessTypes = PropertyUtil.getDisplayName(CommonConstants.BALCONY_TYPES);
		StringTokenizer tokens = new StringTokenizer(availableBusinessTypes, ",");
		while (tokens.hasMoreTokens()) {
			String bolconyType = tokens.nextToken();
			double price = Double.parseDouble(PropertyUtil.getDisplayName(bolconyType + ".price"));
			int level = Integer.parseInt(PropertyUtil.getDisplayName(bolconyType + ".level"));
			int seatsInRow = Integer.parseInt(PropertyUtil.getDisplayName(bolconyType + ".seatsInRow"));
			int noOfRows = Integer.parseInt(PropertyUtil.getDisplayName(bolconyType + ".rows"));
			Balcony balcony = new Balcony(bolconyType, price, level, seatsInRow, noOfRows);
			balconies.add(balcony);
		}
		StageShow show = new StageShow(balconies);
		stageShows.add(show);
		seatLockingTime = Long.parseLong(PropertyUtil.getDisplayName(CommonConstants.SEAT_LOCK_TIME));
	}
	
	public StageShow getStageShow() {
		if (stageShows == null || stageShows.isEmpty())
			return null;
		return stageShows.get(0);
	}
	
	public User getUser(String email) {
		User user = systemUser.get(email);
		return user;
	}
	
	public User addUser(String email) {
		if (systemUser.containsKey(email))
			return systemUser.get(email);
		User user = new User(email);
		systemUser.put(email, user);
		return user;
	}
	
	public long getSeatLockingTime() {
		return seatLockingTime;
	}
	
	public void setSeatLockingTime(long seatLockingTime) {
		this.seatLockingTime = seatLockingTime;
	}
}
