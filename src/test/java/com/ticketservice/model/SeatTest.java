package com.ticketservice.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ydebessu on 11/8/2015.
 */
public class SeatTest {
    @Test
    public void verifyGetters() {
        Seats seats = new Seats(1,10);
        Assert.assertEquals("Venue Level Id: ", 1, seats.getVenueLevelId());
        Assert.assertEquals("number of seats: ", 10, seats.getNumberOfSeats());
    }
}
