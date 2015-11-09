package com.ticketservice.model;

import java.util.List;

/**
 * Created by ydebessu on 11/8/2015.
 */
public interface SeatHold {
    /**
     * Returns uniqueIdentifier
     * @return the Id
     */
    public int getId();

    /**
     * Returns email
     * @return the email
     */
    public String getEmail();

    /**
     * Returns expiration status
     * @return true/false
     */
    public boolean isExpired();

    /**
     * Returns the list of Seats (level, numberof seats in that level)
     * @return list
     */
    public List<Seats> getSeatsList();
}
