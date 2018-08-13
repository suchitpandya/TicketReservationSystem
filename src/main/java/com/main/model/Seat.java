/**
 * 
 */
package com.main.model;

import java.util.ArrayList;

/**
 * @author Suchit
 * Description: This class holds the seat information (e.g seat number, row, status etc). 
 * 			    It provides basic functions like add seat, delete seat, get seat status etc.
 *
 */
public class Seat {	
	
	private int seatNumber;
	//rowNumber is defined for future use.
	private int rowNumber;
	private int ticketId;
	// seat condition is for future use, in to mark broken seats as unavailable
	private int seatCondition;
	private String seatStatus;
	
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * @return the seatCondition
	 */
	public int getSeatCondition() {
		return seatCondition;
	}
	/**
	 * @param seatCondition the seatCondition to set
	 */
	public void setSeatCondition(int seatCondition) {
		this.seatCondition = seatCondition;
	}
	/**
	 * @return the seatStatus
	 */
	public String getSeatStatus() {
		return seatStatus;
	}
	/**
	 * @param seatStatus the seatStatus to set
	 */
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
	

}
