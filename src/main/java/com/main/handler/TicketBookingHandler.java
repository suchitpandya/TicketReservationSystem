/**
 * 
 */
package com.main.handler;
import java.util.ArrayList;

import com.main.model.Seat;

/**
 * @author Suchit
 *
 */
public class TicketBookingHandler {
	
	private static ArrayList<Seat> totalSeatsList = new ArrayList<Seat> ();
	private static ArrayList<Integer> availableSeatNumberList = new ArrayList<Integer>();
	private static ArrayList<Seat> holdSeatsList = new ArrayList<Seat> ();
	
	private TicketBookingHandler() {
		
	}
	
	public TicketBookingHandler(int totalSeats) {
		Seat seat;
		int seatNumber=0;
		for(; seatNumber<totalSeats; seatNumber++) {
			seat = new Seat();
			seat.setSeatNumber(seatNumber);
			seat.setSeatStatus("available");
			totalSeatsList.add(seat);
			availableSeatNumberList.add(seatNumber);
		}
	}
	
	public int getAvailableSeats() {
		return availableSeatNumberList.size();
	}
	
}
