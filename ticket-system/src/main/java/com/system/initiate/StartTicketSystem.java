package com.system.initiate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.system.model.Balcony;
import com.system.model.Seat;
import com.system.model.StageShow;
import com.system.model.User;
import com.system.model.UserTicket;
import com.system.service.StageShowService;
import com.system.ui.Response;

public class StartTicketSystem extends Thread {
	private static StageShowService stageShowService;
	private static Map<Integer, Balcony> balonyCache = new HashMap<Integer, Balcony>();
	private static Integer balconyLevel = null;
	private static List<Integer> selectedSeats = null;
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		stageShowService = (StageShowService) context.getBean("stageShowService");
		//
		Thread t = new StartTicketSystem();
		t.setDaemon(true);
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			// System.out.println("Scheduler started (will unlock the expired
			// seats)");
			try {
				Thread.sleep(5000);
				stageShowService.unLockSelectedSeatsScheduler();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		//
		Integer option = null;
		do {
			System.out
					.println(" 1: ShowDetails \n 2: Select Seats \n 3: Confirm Seats \n 4: User tickets \n 5: Exit \n");
			System.out.println("Select Options (1,2,3,4,5): ");
			Scanner sc = new Scanner(System.in);
			String userOption = sc.next();
			try {
				option = Integer.parseInt(userOption);
				switch (option) {
					case 1:
						getShowDetails();
						break;
					case 2:
						holdSeats(sc);
						break;
					case 3:
						confirmSeats(sc);
						break;
					case 4:
						printUserTickets(sc);
						break;
					case 5:
						System.out.println("Service Shutting down");
						System.exit(0);
						sc.close();
						break;
					default:
						System.out.println("Enter Valid option");
						break;
				}
			} catch (NumberFormatException e) {
			}
		} while (option != null);
	}
	
	private static void printUserTickets(Scanner sc) {
		System.out.println("Enter your emailId (user for booking) :");
		String emailId = sc.next();
		User user = stageShowService.getTicketInfo(emailId);
		if (user != null) {
			List<UserTicket> userTickets = user.getUserTicket();
			if (userTickets != null && !userTickets.isEmpty()) {
				for (UserTicket ticket : userTickets) {
					List<Integer> seatNos = ticket.getSeatNos();
					StringBuffer sb = new StringBuffer();
					for (int seatNo : seatNos) {
						sb.append(seatNo + ",");
					}
					System.out.print("Ticket-Id :" + ticket.getTicketId() + "\n" + "Level Name :"
							+ ticket.getBalconyType() + " Selected seats :" + sb.toString());
				}
			} else {
				System.out.println("No tickets found for this email-id");
			}
		} else
			System.out.println("No User found with this mail-id");
		System.out.println();
	}
	
	private static void confirmSeats(Scanner sc) {
		/* ### 4. CONFIRM TICKETS ### */
		System.out.println("Confirm selected tickets by entering following details :");
		System.out.println("Enter email address : ");
		String emailAddress = null;
		Boolean isValid = null;
		do {
			isValid = true;
			emailAddress = sc.next().trim();
			if (emailAddress == null || emailAddress.isEmpty()) {
				System.out.println("Please enter Valid Email-Id");
				isValid = false;
			}
		} while (!isValid);
		System.out.println("Enter userName : ");
		String userName = null;
		do {
			isValid = true;
			userName = sc.next().trim();
			if (userName == null || userName.isEmpty()) {
				System.out.println("Please enter Valid userName");
				isValid = false;
			}
		} while (userName == null);
		Response reserveSeats = stageShowService.reserveSeats(emailAddress, userName,
				balonyCache.get(balconyLevel).getType(), selectedSeats);
		System.out.println(reserveSeats.getMessage());
	}
	
	private static void holdSeats(Scanner sc) {
		/* ###### 2. SELECT BALCONY LEVEL##### */
		do {
			System.out.println("Select Balcony Level : ");
			String userSelectedBalconyLevel = sc.next();
			try {
				balconyLevel = Integer.parseInt(userSelectedBalconyLevel);
			} catch (NumberFormatException e) {
				StringBuffer balconyMsg = new StringBuffer();
				for (Integer level : balonyCache.keySet())
					balconyMsg.append(level + " ");
				System.out.println("Invalid balcony level. Available balconies : " + balconyMsg);
			}
		} while (balconyLevel == null || !isValidBalcony(balconyLevel));
		// System.out.println(balconyLevel);
		System.out.println("Enter selected seat numbers (separated by comma) : ");
		Boolean isValid = null;
		selectedSeats = new ArrayList<Integer>();
		do {
			isValid = true;
			String seatNos = sc.next();
			StringTokenizer tokenizer = new StringTokenizer(seatNos, ",");
			while (tokenizer.hasMoreTokens() && isValid) {
				String nextSeat = tokenizer.nextToken().trim();
				try {
					selectedSeats.add(Integer.parseInt(nextSeat));
				} catch (NumberFormatException e) {
					System.out.println("Select valid seats");
					isValid = false;
				}
			}
		} while (!isValid);
		/* ### 3. LOCK SEATS ### */
		System.out.println(
				"Do you want to hold the seats (press 'Y' for confirmation / any other key for changing the option)");
		String confirmation = null;
		confirmation = sc.next();
		if (confirmation == null || !isValidConfirmation(confirmation))
			return;
		Response holdSeats = stageShowService.holdSeats(balonyCache.get(balconyLevel).getType(), selectedSeats);
		System.out.println(holdSeats.getMessage());
	}
	
	private static void getShowDetails() {
		/* ### 1. GET SHOW DETAILS #### */
		balonyCache = new HashMap<Integer, Balcony>();
		balconyLevel = null;
		selectedSeats = null;
		StageShow showDetails = stageShowService.getShowDetails();
		List<Balcony> availableBalconies = showDetails.getAvailableBalconies();
		System.out.println("Balcony Details");
		if (availableBalconies != null && !availableBalconies.isEmpty()) {
			System.out.println("Level ||  Level Name   || Price  ||  Rows   || Seats in Row");
			for (Balcony balcony : availableBalconies) {
				balonyCache.put(balcony.getLevel(), balcony);
				System.out.println(balcony.getLevel() + "       " + balcony.getType() + "        " + balcony.getPrice()
						+ "         " + balcony.getNoOfRows() + "       " + +balcony.getSeatsInRow());
				System.out.println("Available seats : ");
				List<List<Seat>> seats = balcony.getSeats();
				int rowIndex = 0;
				for (List<Seat> rowSeats : seats) {
					System.out.print(rowIndex++ + " ");
					for (Seat seat : rowSeats) {
						if (seat.isLocked() || seat.isBooked())
							System.out.print("  ");
						else
							System.out.print(seat.getSeatNo() + "  ");
					}
					System.out.println();
				}
				System.out.println("##################");
			}
		}
	}
	
	private static boolean isValidConfirmation(String confirmation) {
		return confirmation != null && confirmation.equalsIgnoreCase("Y");
	}
	
	private static boolean isValidBalcony(Integer balconyLevel) {
		if (balonyCache == null || balonyCache.isEmpty())
			populateBalconyCache();
		return balonyCache.containsKey(balconyLevel);
	}
	
	private static void populateBalconyCache() {
		StageShow showDetails = stageShowService.getShowDetails();
		List<Balcony> availableBalconies = showDetails.getAvailableBalconies();
		if (availableBalconies != null && !availableBalconies.isEmpty())
			for (Balcony balcony : availableBalconies) {
				balonyCache.put(balcony.getLevel(), balcony);
			}
	}
}
