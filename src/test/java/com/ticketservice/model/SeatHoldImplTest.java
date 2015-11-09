package com.ticketservice.model;

import com.ticketservice.service.IReleaseHandler;
import com.ticketservice.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydebessu on 11/8/2015.
 */
public class SeatHoldImplTest {

    /**
     * this verifies hold seats fiels values are set
     */
    @Test
    public void holdSeatShouldGetinstantiated() throws InterruptedException {
        List<Seats> seatsList = new ArrayList<Seats>();
        String email = "email";
        MockReleaseHandler callback = new MockReleaseHandler();

        SeatHoldImpl seatHold = new SeatHoldImpl(email,seatsList,callback);
        Assert.assertEquals("email", email, seatHold.getEmail());
        Assert.assertEquals("List", seatsList, seatHold.getSeatsList());
    }

    /**
     * this verifies hold seats are expiring
     */
    @Test
    public void holdSeatShouldExpireAfterGivenSeconds() throws InterruptedException {
        List<Seats> seatsList = new ArrayList<Seats>();
        String email = "email";
        MockReleaseHandler callback = new MockReleaseHandler();

        SeatHoldImpl seatHold = new SeatHoldImpl(email,seatsList,callback);

        Assert.assertFalse("Expire immediately:", seatHold.isExpired());
        Thread.sleep(Constants.DELAY_BEFORE_HOLD_EXPIRATION + 2);
        Assert.assertTrue("Expire after expiration time:",seatHold.isExpired());
        Assert.assertTrue("Callback called:", callback.isCalled());
    }

    class MockReleaseHandler implements IReleaseHandler {
        private boolean isCalled = false;
        @Override
        public void release(SeatHold seatHold) {
            isCalled = true;
        }
        public boolean isCalled() {
            return isCalled;
        }
    }
}
