/**
 * 
 */
package com.main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.main.TestConstants;
import com.main.model.Seat;
import com.main.model.SeatHold;
import com.main.util.Constants;

import junit.framework.TestCase;

/**
 * @author Suchit
 *
 */
public class TicketServiceImplTest extends TestCase {
	private TicketServiceImpl ticketServiceImpl;
	private int holdId;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		clearStaticValues();
		ticketServiceImpl = new TicketServiceImpl(TestConstants.SEAT_COUNT);
		holdId = ticketServiceImpl.holdOrderId;
	}
	
	@Test
	public void testNumSeatsAvailable_success() {
		int totalSeats = ticketServiceImpl.numSeatsAvailable();
		Assert.assertEquals(TestConstants.SEAT_COUNT, totalSeats);
	}
	
	@Test
	public void testFindAndHoldSeats_success() {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.BOOK_SEAT_COUNT, TestConstants.CUSTOMER_EMAIL);
		Assert.assertEquals(TestConstants.BOOK_SEAT_COUNT, holdSeats.getNumSeats());
		Assert.assertEquals(TestConstants.NUM_OF_BOOKINGS, ticketServiceImpl.holdSeatsMap.size());
		Assert.assertEquals(holdId, ticketServiceImpl.holdSeatsMap.get(holdId).getHoldId());
	}
	
	@Test
	public void testFindAndHoldSeats_Error_Null_Email() {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.BOOK_SEAT_COUNT, null);
		Assert.assertEquals(Constants.STATUS_VALIDATION_ERROR, holdSeats.getStatus());
		Assert.assertEquals(Constants.MISSING_EMAIL_ERR_MESSAGE, holdSeats.getError_msg());
		Assert.assertEquals(Constants.MISSING_EMAIL_ERR_ID, holdSeats.getError_id());
	}
	
	@Test
	public void testFindAndHoldSeats_Error_SEAT_UNAVAILABLE() {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.SEAT_COUNT+1, TestConstants.CUSTOMER_EMAIL);
		Assert.assertEquals(Constants.STATUS_VALIDATION_ERROR, holdSeats.getStatus());
		Assert.assertEquals(Constants.SEAT_NOT_AVAILABLE_ERR_MESSAGE, holdSeats.getError_msg());
		Assert.assertEquals(Constants.SEAT_NOT_AVAILABLE_ERR_ID, holdSeats.getError_id());
	}
	
	@Test
	public void testFindAndHoldSeats_Error_OrderTimeOut() throws InterruptedException {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.BOOK_SEAT_COUNT, TestConstants.CUSTOMER_EMAIL);
		Assert.assertEquals(TestConstants.BOOK_SEAT_COUNT, holdSeats.getNumSeats());
		Assert.assertEquals(TestConstants.NUM_OF_BOOKINGS, ticketServiceImpl.holdSeatsMap.size());
		Assert.assertEquals(holdId, ticketServiceImpl.holdSeatsMap.get(holdId).getHoldId());
		TimeUnit.SECONDS.sleep(Constants.HOLD_WAIT_TIME_SECONDS+1);
		Assert.assertEquals(TestConstants.NUM_OF_BOOKINGS, ticketServiceImpl.holdSeatsMap.size());
		Assert.assertEquals(Constants.STATUS_EXPIRE, ticketServiceImpl.holdSeatsMap.get(holdId).getStatus());
	}
	
	@Test
	public void testReserveSeats_Success() {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.BOOK_SEAT_COUNT, TestConstants.CUSTOMER_EMAIL);
		String statusMsg = ticketServiceImpl.reserveSeats(holdSeats.getHoldId(), TestConstants.CUSTOMER_EMAIL);
		Assert.assertTrue(statusMsg.contains(Constants.BOOKING_SUCCESS_MESSAGE));
	}
	
	@Test
	public void testReserveSeats_Error_Invalid_HoldId() {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.BOOK_SEAT_COUNT, TestConstants.CUSTOMER_EMAIL);
		String statusMsg = ticketServiceImpl.reserveSeats(TestConstants.INVALID_HOLDID, TestConstants.CUSTOMER_EMAIL);
		Assert.assertEquals(Constants.INVALID_BOOKINGNO_ERR_MESSAGE,statusMsg);
	}
	
	@Test
	public void testReserveSeats_Error_Invalid_Email() {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.BOOK_SEAT_COUNT, TestConstants.CUSTOMER_EMAIL);
		String statusMsg = ticketServiceImpl.reserveSeats(holdSeats.getHoldId(), TestConstants.INVALID_CUSTOMER_EMAIL);
		Assert.assertEquals(Constants.EMAIL_NOT_MATCHING_ERR_MESSAGE,statusMsg);
	}
	
	@Test
	public void testReserveSeats_Error_Expired_HoldId() throws InterruptedException {
		SeatHold holdSeats = ticketServiceImpl.findAndHoldSeats(TestConstants.BOOK_SEAT_COUNT, TestConstants.CUSTOMER_EMAIL);
		TimeUnit.SECONDS.sleep(Constants.HOLD_WAIT_TIME_SECONDS+1);
		String statusMsg = ticketServiceImpl.reserveSeats(holdSeats.getHoldId(), TestConstants.CUSTOMER_EMAIL);
		Assert.assertEquals(Constants.BOOKING_EXPIRED_ERR_MESSAGE,statusMsg);
	}
	
	public void clearStaticValues() {
		ticketServiceImpl.holdSeatsMap = new HashMap<Integer,SeatHold>();
		ticketServiceImpl.totalSeatsList = new ArrayList<Seat> ();
		ticketServiceImpl.availableSeatNumberList = new ArrayList<Integer>();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		ticketServiceImpl = null;
		super.tearDown();
	}

}
