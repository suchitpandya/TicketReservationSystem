/**
 * 
 */
package com.main.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.main.model.SeatHold;
import com.main.service.impl.TicketServiceImpl;

/**
 * @author Suchit
 *
 */
public class HoldOrderTimer extends TimerTask  {
	
	private int holdId;
	private Timer timer;
	private static final Logger logger = Logger.getLogger(HoldOrderTimer.class.getName());
	
	private HoldOrderTimer() {
		
	}
	
	public HoldOrderTimer (int holdId, Timer timer) {
		this.holdId = holdId;
		this.timer=timer;
	}

	@Override
	public void run() {
		try {
			SeatHold seat = TicketServiceImpl.holdSeatsMap.get(this.holdId);
			if(seat != null && Constants.STATUS_HOLD.equalsIgnoreCase(seat.getStatus())) {
				seat.setStatus(Constants.STATUS_EXPIRE);
				int[] arrSeats = seat.getArrSeatNumbers();
				for (int i=0; i<seat.getNumSeats(); i++) {
					TicketServiceImpl.availableSeatNumberList.add(arrSeats[i]);
				}
//				TicketServiceImpl.holdSeatsMap.remove(this.holdId);
				logger.info("order on hold has expired. order id=" + this.holdId);
			}
		}
		catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		catch(NullPointerException nullEx) {
			nullEx.printStackTrace();
		}
		finally {
			this.cancel();
			this.timer.cancel();
			this.timer=null;
		}
		
		
	}

}
