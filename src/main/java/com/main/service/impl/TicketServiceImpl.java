/**
 * 
 */
package com.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.logging.Logger;

import com.main.model.Seat;
import com.main.model.SeatHold;
import com.main.service.TicketService;
import com.main.util.Constants;
import com.main.util.HoldOrderTimer;

/**
 * @author Suchit
 *
 */
public class TicketServiceImpl implements TicketService  {
	
	public static ArrayList<Seat> totalSeatsList = new ArrayList<Seat> ();
	public static ArrayList<Integer> availableSeatNumberList = new ArrayList<Integer>();
	public static HashMap<Integer,SeatHold> holdSeatsMap = new HashMap<Integer,SeatHold> ();
	public static Integer holdOrderId = 100;
	private static final Logger logger = Logger.getLogger(HoldOrderTimer.class.getName());
	
	public TicketServiceImpl() {
		Seat seat;
		for(int seatNumber=0; seatNumber<Constants.TOTAL_SEATS; seatNumber++) {
			seat = new Seat();
			seat.setSeatNumber(seatNumber);
			seat.setSeatStatus(Constants.STATUS_AVAILABLE);
			totalSeatsList.add(seat);
			availableSeatNumberList.add(seatNumber);
		}
	}
	
	public TicketServiceImpl (int totalSeats) {
		Seat seat;
		for(int seatNumber=0; seatNumber<totalSeats; seatNumber++) {
			seat = new Seat();
			seat.setSeatNumber(seatNumber);
			seat.setSeatStatus(Constants.STATUS_AVAILABLE);
			totalSeatsList.add(seat);
			availableSeatNumberList.add(seatNumber);
		}
	}

	/* (non-Javadoc)
	 * @see com.main.service.TicketService#numSeatsAvailable()
	 */
	public int numSeatsAvailable() {
		return availableSeatNumberList.size();
	}

	/* (non-Javadoc)
	 * @see com.main.service.TicketService#findAndHoldSeats(int, java.lang.String)
	 */
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		logger.info("Inside find and hold seats");
		Timer timer;
		SeatHold holdOrder = new SeatHold();
		try {
		if(numSeats>numSeatsAvailable()) {
			holdOrder.setStatus(Constants.STATUS_VALIDATION_ERROR);
			holdOrder.setError_msg(Constants.SEAT_NOT_AVAILABLE_ERR_MESSAGE);
			holdOrder.setError_id(Constants.SEAT_NOT_AVAILABLE_ERR_ID);
		}
		else if (customerEmail == null ) {
			holdOrder.setStatus(Constants.STATUS_VALIDATION_ERROR);
			holdOrder.setError_msg(Constants.MISSING_EMAIL_ERR_MESSAGE);
			holdOrder.setError_id(Constants.MISSING_EMAIL_ERR_ID);
		}
		else {
			holdOrder.setNumSeats(numSeats);
			holdOrder.setHoldId(holdOrderId);
			holdOrder.setCustomerEmail(customerEmail);
			holdOrder.setStatus(Constants.STATUS_HOLD);
			holdSeatsMap.put(holdOrderId, holdOrder);
			int[] arrSeatNumbers = new int[numSeats];
			
			for (int i=0; i<numSeats; i++) {
				arrSeatNumbers[i] = availableSeatNumberList.get(Constants.ZERO);
				availableSeatNumberList.remove(Constants.ZERO);
			}
			holdOrder.setArrSeatNumbers(arrSeatNumbers);
			timer = new Timer();
			HoldOrderTimer holdtimer = new HoldOrderTimer(holdOrderId, timer);
			timer.schedule(holdtimer, Constants.HOLD_WAIT_TIME_SECONDS*Constants.THOUSAND);
			timer = null;
		}
		logger.info("find and hold seats success: order held");
		return holdOrder;
		}
		catch(Exception e) {
			logger.severe("Exception while holding the oder. holdOrderId = 	"+holdOrderId);
			e.printStackTrace();
			holdOrder.setStatus(Constants.STATUS_ERROR);
			holdOrder.setError_id(Constants.UNABLE_PROCESSING_ERR_ID);
			holdOrder.setError_msg(Constants.UNABLE_PROCESSING_ERR_MESSAGE);
			return holdOrder;
		}
		finally {
			holdOrderId++;
			timer = null;
		}
	}

	/* (non-Javadoc)
	 * @see com.main.service.TicketService#reserveSeats(int, java.lang.String)
	 */
	public String reserveSeats(int seatHoldId, String customerEmail) {
		logger.info("Inside reserveSeats");
		SeatHold holdOrder = holdSeatsMap.get(seatHoldId);
		
		if (holdOrder == null) {
			return Constants.INVALID_BOOKINGNO_ERR_MESSAGE;
		}
		else if (Constants.STATUS_EXPIRE.equalsIgnoreCase(holdOrder.getStatus())) {
			return Constants.BOOKING_EXPIRED_ERR_MESSAGE;
		}
		else if (customerEmail == null || !customerEmail.equalsIgnoreCase(holdOrder.getCustomerEmail())) {
			return Constants.EMAIL_NOT_MATCHING_ERR_MESSAGE;
		}
		else {
			int numSeats= holdOrder.getNumSeats();
			int[] arrSeats = holdOrder.getArrSeatNumbers();
			Seat seat;
			String bookedNumbers = "";
			for (int i=0; i<numSeats; i++) {
				seat = totalSeatsList.get(arrSeats[i]);
				seat.setTicketId(seatHoldId);
				seat.setSeatStatus(Constants.STATUS_BOOKED);
				totalSeatsList.set(arrSeats[i], seat);
				bookedNumbers = bookedNumbers + arrSeats[i];
				if (i<numSeats-1) {
					bookedNumbers = bookedNumbers + ",";
				}
			}
			holdOrder.setStatus(Constants.STATUS_BOOKED);
			holdSeatsMap.put(seatHoldId, holdOrder);
//			holdSeatsMap.remove(seatHoldId);
			return Constants.BOOKING_SUCCESS_MESSAGE + bookedNumbers;
		}
	}

}
