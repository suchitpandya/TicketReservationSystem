/**
 * 
 */
package com.main.util;

/**
 * @author Ameesuchit
 *
 */
public class Constants {
	public static final String STATUS_EXPIRE = "Expired";
	public static final String STATUS_BOOKED = "Booked";
	public static final String STATUS_HOLD = "HOLD";
	public static final String STATUS_VALIDATION_ERROR = "VALIDATION_ERROR";
	public static final String STATUS_ERROR = "ERROR";
	public static final String STATUS_AVAILABLE = "AVAILABLE";
	public static final int HOLD_WAIT_TIME_SECONDS = 5;
	public static final int THOUSAND = 1000;
	public static final int ZERO = 0;
	public static final int TOTAL_SEATS = 10;
	
	public static final String BOOKING_SUCCESS_MESSAGE = "Congratulations, Your seats are reserved. Seat numbers are: ";
	public static final String BOOKING_EXPIRED_ERR_MESSAGE = "OrderNumber is expired";
	public static final String EMAIL_NOT_MATCHING_ERR_MESSAGE = "Email address is not matching";
	public static final String INVALID_BOOKINGNO_ERR_MESSAGE ="HoldId is incorect";
	public static final String HOLD_RELEASE_ERR_MESSAGE = "Error in releaseing hold order. OrderId = ";
	public static final String SEAT_NOT_AVAILABLE_ERR_ID = "1001";
	public static final String SEAT_NOT_AVAILABLE_ERR_MESSAGE = "Seats are not available";
	public static final String MISSING_EMAIL_ERR_ID = "1002";
	public static final String MISSING_EMAIL_ERR_MESSAGE = "Please provide email address";
	public static final String UNABLE_PROCESSING_ERR_MESSAGE = "System is unable to process right now. Please try after some time";
	public static final String UNABLE_PROCESSING_ERR_ID = "2001";
	
}
