package com.ticketservice.model;

/**
 * Created by ydebessu on 11/8/2015.
 */
public class Seats {
    private int venueLevelId;
    private int numberOfSeats;

    public Seats(int venueLevelId, int numberOfSeats) {
        this.venueLevelId = venueLevelId;
        this.numberOfSeats = numberOfSeats;
    }

    public int getVenueLevelId() {
        return venueLevelId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
