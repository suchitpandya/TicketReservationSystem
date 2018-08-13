package com.main;

import java.util.concurrent.TimeUnit;

import com.main.model.SeatHold;
import com.main.service.TicketService;
import com.main.service.impl.TicketServiceImpl;
import com.main.util.Constants;


/**
 * Hello world!
 *
 */
public class MainTest 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	SeatHold orderDetails;
        System.out.println( "Welcome to reservation application. Total Seats: " + Constants.TOTAL_SEATS );
        System.out.println( "Test 1: Check Available seats" );
        TicketService ticketbookingOrder = new TicketServiceImpl(Constants.TOTAL_SEATS);
        System.out.println("numSeatsAvailable = " + ticketbookingOrder.numSeatsAvailable());
        System.out.println( "Test 1 result: " + (Constants.TOTAL_SEATS == ticketbookingOrder.numSeatsAvailable())); 
        
        
        System.out.println( "\nTest 2: Hold 1 seat" );
        orderDetails = ticketbookingOrder.findAndHoldSeats(1, "abc@gmail.com");
        System.out.println("numSeatsAvailable = " + ticketbookingOrder.numSeatsAvailable());
        System.out.print( "Test 2 result: " + (Constants.TOTAL_SEATS-1 == ticketbookingOrder.numSeatsAvailable()));
        
        System.out.println( "\nTest 3: Wait for "+Constants.HOLD_WAIT_TIME_SECONDS+" seconds and let hold timer expire" );
        TimeUnit.SECONDS.sleep(Constants.HOLD_WAIT_TIME_SECONDS+1);
        System.out.println("numSeatsAvailable = " + ticketbookingOrder.numSeatsAvailable());
        System.out.print( "Test 3 result: " + (Constants.TOTAL_SEATS == ticketbookingOrder.numSeatsAvailable()));
        
        System.out.println( "\nTest 4: try to reserve with expired hold id" );
        System.out.println(ticketbookingOrder.reserveSeats(orderDetails.getHoldId(), "abc@gmail.com"));
        System.out.println("numSeatsAvailable = " + ticketbookingOrder.numSeatsAvailable());
        System.out.print( "Test 4 result: " + (Constants.TOTAL_SEATS == ticketbookingOrder.numSeatsAvailable()));
        
        System.out.println( "\nTest 5: Hold 5 seats" );
        orderDetails = ticketbookingOrder.findAndHoldSeats(5, "def@gmail.com");
        System.out.print("totalSeats booked = " + orderDetails.getNumSeats());
        System.out.print(" ,booking email: " + orderDetails.getCustomerEmail());
        System.out.print(" ,Order number: " + orderDetails.getHoldId());
        System.out.println(" ,Order status: " + orderDetails.getStatus());
        System.out.println("numSeatsAvailable = " + ticketbookingOrder.numSeatsAvailable());
        System.out.print( "Test 5 result: " + (Constants.TOTAL_SEATS-5 == ticketbookingOrder.numSeatsAvailable()));
        
        System.out.println( "\nTest 6: reserve the same 5 seats" );
        System.out.println(ticketbookingOrder.reserveSeats(orderDetails.getHoldId(), "def@gmail.com"));
        System.out.println("numSeatsAvailable = " + ticketbookingOrder.numSeatsAvailable());
        TimeUnit.SECONDS.sleep(Constants.HOLD_WAIT_TIME_SECONDS+1);
        System.out.print( "Test 6 result: " + (Constants.TOTAL_SEATS-5 == ticketbookingOrder.numSeatsAvailable()));
       
    }
}
