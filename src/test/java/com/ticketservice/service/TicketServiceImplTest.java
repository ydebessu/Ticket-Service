package com.ticketservice.service;

import com.ticketservice.model.SeatHold;
import com.ticketservice.model.SeatHoldImpl;
import com.ticketservice.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by ydebessu on 11/8/2015.
 */
public class TicketServiceImplTest {
    TicketService ticketService;

    @Before
    public void initialize() {
        ticketService = new TicketServiceImpl();
    }

    /**
     * Test the available seats for all existing levels and non existing levels(returns 0)
     */

    @Test
    public void verifyNumberOfAvailableSeatsForLevel1() {
        Assert.assertEquals("Number of available seats for level1", 25*50, ticketService.numSeatsAvailable(
                        Optional.of(1)));
    }

    @Test
    public void verifyNumberOfAvailableSeatsForLevel2() {
        Assert.assertEquals("Number of available seats for level2", 20*100, ticketService.numSeatsAvailable(Optional.of(2)));
    }

    @Test
    public void verifyNumberOfAvailableSeatsForLevel3() {
        Assert.assertEquals("Number of available seats for level3", 15*100, ticketService.numSeatsAvailable(Optional.of(3)));
    }

    @Test
    public void verifyNumberOfAvailableSeatsForLevel4() {
        Assert.assertEquals("Number of available seats for level4", 15*100, ticketService.numSeatsAvailable(Optional.of(4)));
    }

    @Test
    public void verifyNumberOfAvailableSeatsForLevel5() {
        Assert.assertEquals("Number of available seats for level5", 0, ticketService.numSeatsAvailable(Optional.of(5)));
    }

    @Test
    public void verifyNumberOfAvailableSeatsEmptyRequest() {
        Assert.assertEquals("Number of available seats for level5", 0, ticketService.numSeatsAvailable(null));
    }

    /**
     * Test finding and holding seats seats for all existing levels and non existing levels(returns 0)
     */

    @Test
    public void verifyFindHoldForMultipleLevels() {
        int numberOfSeats = 3000;
        String customerEmail = "email@acme.com";
        SeatHold seatHold = ticketService.findAndHoldSeats(numberOfSeats, Optional.of(0), Optional.of(5),
                        customerEmail);
        Assert.assertEquals("Number of available seats for level2", 25*50 + 20*100 - 3000, ticketService.numSeatsAvailable(Optional.of(2)));
    }

    @Test
    public void verifyFindHoldForMultipleLevelsWithLargeRequest() {
        int numberOfSeats = 6000; //total we have 6250
        String customerEmail = "email@acme.com";
        SeatHold seatHold = ticketService.findAndHoldSeats(numberOfSeats, Optional.of(0), Optional.of(5),
                        customerEmail);
        Assert.assertEquals("Number of available seats for level4", 6250 - 6000, ticketService.numSeatsAvailable(Optional.of(4)));
    }

    @Test
    public void verifyFindHoldForMultipleLevelsWithTooBigRequest() {
        int numberOfSeats = 50000; //we have only 6250
        String customerEmail = "email@acme.com";
        SeatHold seatHold = ticketService.findAndHoldSeats(numberOfSeats, Optional.of(0), Optional.of(5),
                        customerEmail);
        Assert.assertNull("Seat Held", seatHold);
        Assert.assertEquals("Number of available seats for level1", 25*50, ticketService.numSeatsAvailable(
                        Optional.of(1)));
    }

    /**
     * Test reserving seats which are hold
     */

    @Test
    public void verifyReserveHoldSeats() {
        int numberOfSeats = 3000;
        String customerEmail = "email@acme.com";
        SeatHold seatHold = ticketService.findAndHoldSeats(numberOfSeats, Optional.of(0), Optional.of(5),
                        customerEmail);
        String reservationCode = ticketService.reserveSeats(seatHold.getId(),seatHold.getEmail());
        Assert.assertNotEquals("Reservation code: ", null, reservationCode);
        Assert.assertEquals("Number of available seats for level2", 25*50 + 20*100 - 3000, ticketService.numSeatsAvailable(Optional.of(2)));
    }

    /**
     * Test expiration of held seats
     */

    @Test
    public void verifyExpirationOfHeldSeats() throws InterruptedException {
        int numberOfSeats = 3000;
        String customerEmail = "email@acme.com";
        SeatHold seatHold = ticketService.findAndHoldSeats(numberOfSeats, Optional.of(0), Optional.of(5),
                        customerEmail);
        Assert.assertEquals("Number of available seats for level2", 25*50 + 20*100 - 3000, ticketService.numSeatsAvailable(Optional.of(2)));
        Thread.sleep(Constants.DELAY_BEFORE_HOLD_EXPIRATION + 2);
        Assert.assertEquals("Number of available seats for level2", 20*100, ticketService.numSeatsAvailable(Optional.of(2)));
    }
}
