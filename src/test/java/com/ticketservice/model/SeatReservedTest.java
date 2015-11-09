package com.ticketservice.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydebessu on 11/9/2015.
 */
public class SeatReservedTest {
    @Test
    public void verifyGetters() {
        List<Seats> seatsList = new ArrayList<Seats>();
        String email = "email";
        SeatReserved seatReserved = new SeatReserved(email,seatsList);
        Assert.assertEquals("List", email, seatReserved.getEmail());
        Assert.assertEquals("List", seatsList, seatReserved.getSeatsList());
    }
}
