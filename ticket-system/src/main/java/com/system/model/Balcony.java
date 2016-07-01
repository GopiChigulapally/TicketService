package com.system.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Balcony implements Serializable {
	private static final long serialVersionUID = 808352622431649081L;
	private String type;
	private double price;
	private int level;
	private int seatsInRow;
	private int noOfRows;
	private List<List<Seat>> seats;
	
	public Balcony(String type, double price, int level, int seatsInRow, int noOfRows) {
		super();
		this.type = type;
		this.price = price;
		this.level = level;
		this.seatsInRow = seatsInRow;
		this.noOfRows = noOfRows;
		this.seats = new ArrayList<List<Seat>>();
		int seatNo = 1;
		for (int rowIndex = 0; rowIndex < noOfRows; rowIndex++) {
			List<Seat> seats = new ArrayList<Seat>();
			for (int colIndex = 0; colIndex < seatsInRow; colIndex++) {
				seats.add(new Seat(seatNo++));
			}
			this.seats.add(seats);
		}
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getSeatsInRow() {
		return seatsInRow;
	}
	
	public void setSeatsInRow(int seatsInRow) {
		this.seatsInRow = seatsInRow;
	}
	
	public int getNoOfRows() {
		return noOfRows;
	}
	
	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}
	
	public List<List<Seat>> getSeats() {
		return seats;
	}
	
	public void setSeats(List<List<Seat>> seats) {
		this.seats = seats;
	}
}
