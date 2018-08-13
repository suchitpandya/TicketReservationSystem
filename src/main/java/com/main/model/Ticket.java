/**
 * 
 */
package com.main.model;

import java.util.Date;

/**
 * @author Suchit
 *
 */
public class Ticket {
	private int ticketId;
	private int numSeats;
	private String ticketType;
	private String customerEmail;
	private Date reservationDate;
		
	/**
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}
	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * @return the numSeats
	 */
	public int getNumSeats() {
		return numSeats;
	}
	/**
	 * @param numSeats the numSeats to set
	 */
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}
	/**
	 * @return the ticketType
	 */
	public String getTicketType() {
		return ticketType;
	}
	/**
	 * @param ticketType the ticketType to set
	 */
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	/**
	 * @return the reservationDate
	 */
	public Date getReservationDate() {
		return reservationDate;
	}
	/**
	 * @param reservationDate the reservationDate to set
	 */
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
}
