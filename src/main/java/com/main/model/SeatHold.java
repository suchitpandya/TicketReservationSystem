/**
 * 
 */
package com.main.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Suchit
 *
 */
public class SeatHold {
	
	private int holdId;
	private int numSeats;
	private int[] arrSeatNumbers;
	private String customerEmail;
	private String status;
	private String error_msg;
	private String error_id;
	
	/**
	 * @return the holdId
	 */
	public int getHoldId() {
		return holdId;
	}
	/**
	 * @param holdId the holdId to set
	 */
	public void setHoldId(int holdId) {
		this.holdId = holdId;
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
	 * @return the seatNumbers
	 */
	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	/**
	 * @return the arrSeatNumbers
	 */
	public int[] getArrSeatNumbers() {
		return arrSeatNumbers;
	}
	/**
	 * @param arrSeatNumbers the arrSeatNumbers to set
	 */
	public void setArrSeatNumbers(int[] arrSeatNumbers) {
		this.arrSeatNumbers = arrSeatNumbers;
	}
	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the error_msg
	 */
	public String getError_msg() {
		return error_msg;
	}
	/**
	 * @param error_msg the error_msg to set
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	/**
	 * @return the error_id
	 */
	public String getError_id() {
		return error_id;
	}
	/**
	 * @param error_id the error_id to set
	 */
	public void setError_id(String error_id) {
		this.error_id = error_id;
	}
	
}
