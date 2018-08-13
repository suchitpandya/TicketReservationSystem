# TicketReservationSystem

This project is for reservation system. All configurations are in class /src/main/java/com/main/util/Constants.java.
Please update those constant values in order to change below defailt values.

| Variable Name            | Default Value |
|--------------------------|---------------|
| TOTAL_SEATS              | 10            |
| HOLD_WAIT_TIME_SECONDS   | 5             |

If one want to use this project jar than it can be done through TicketService.java interface. TicketServiceImpl is implementation of this interface.

This project provides 3 main functionalites.
  1) Finding available seat number
  2) Holding a seat
  3) Reserve the hold order
If customer takes more than 5 seconds after hold than hold order will get canceled.  

# How To run this project on command line.

Building a Jar file:
Project can be build by mvn clean install. It will generate a jar file which can be found in target folder. Jar file can be directly used for further testing.

Running main class thorught command line:
run command "mvn exec:java" which will execut inbuilt MainTest.class. This class contains basic 6 test cases.




