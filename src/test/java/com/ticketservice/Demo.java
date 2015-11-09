package com.ticketservice;

import com.ticketservice.model.SeatHold;
import com.ticketservice.service.TicketServiceImpl;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by ydebessu on 11/9/2015.
 */
public class Demo {
    @Test
    public void demoTest() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        ticketService.displayStateOfVenue();
        SeatHold seatHold1 = ticketService.findAndHoldSeats(500, Optional.of(1), Optional.of(1),
                        "myemail@abc.com");

        SeatHold seatHold2 = ticketService.findAndHoldSeats(2000, Optional.of(1), Optional.of(2),
                        "myemail2@abc.com");

        ticketService.reserveSeats(seatHold1.getId(),seatHold1.getEmail());

        ticketService.displayStateOfVenue();
    }
}
