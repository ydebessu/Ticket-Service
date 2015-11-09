package com.ticketservice.model;

import java.util.List;

/**
 * Created by ydebessu on 11/9/2015.
 */
public class SeatReserved {
    private String email;
    private List<Seats> seatsList;

    public SeatReserved(String email, List<Seats> seatsList) {
        this.email = email;
        this.seatsList = seatsList;
    }

    public String getEmail() {
        return email;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }
}
