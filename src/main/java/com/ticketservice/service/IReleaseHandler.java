package com.ticketservice.service;

import com.ticketservice.model.SeatHold;

/**
 * Created by ydebessu on 11/8/2015.
 */
public interface IReleaseHandler {
    void release(SeatHold seatHold);
}
