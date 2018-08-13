package com.main.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.main.TestConstants;
import com.main.model.Seat;
import com.main.model.SeatHold;
import com.main.service.TicketService;
import com.main.service.impl.TicketServiceImpl;

import junit.framework.Assert;

public class HoldOrderTimerTest {
	private int holdId;
	private Timer timer;
	TicketService ticketService;
	@Before
	public void setUp() throws Exception {
		clearStaticValues();
		timer = new Timer();
		ticketService = new TicketServiceImpl();
		holdId = ticketService.findAndHoldSeats(2, TestConstants.CUSTOMER_EMAIL).getHoldId();
	}

	@Test
	public void test_run_success() throws InterruptedException {
		HoldOrderTimer holdTimer = new HoldOrderTimer(holdId,timer);
		holdTimer.run();
		TimeUnit.SECONDS.sleep(Constants.HOLD_WAIT_TIME_SECONDS+1);
		Assert.assertEquals(1, TicketServiceImpl.holdSeatsMap.size());
		Assert.assertEquals(Constants.STATUS_EXPIRE, TicketServiceImpl.holdSeatsMap.get(holdId).getStatus());
	}
	
	@Test
	public void test_run_holdId_not_Present() {
		HoldOrderTimer holdTimer = new HoldOrderTimer(holdId+1,timer);
		holdTimer.run();
		Assert.assertEquals(1, TicketServiceImpl.holdSeatsMap.size());
		Assert.assertEquals(Constants.STATUS_HOLD, TicketServiceImpl.holdSeatsMap.get(holdId).getStatus());
	}
	
	public void clearStaticValues() {
		TicketServiceImpl.holdSeatsMap = new HashMap<Integer,SeatHold>();
		TicketServiceImpl.totalSeatsList = new ArrayList<Seat> ();
		TicketServiceImpl.availableSeatNumberList = new ArrayList<Integer>();
	}
	
	@After
	public void tearDown() throws Exception {
		timer = null;
	}

}
