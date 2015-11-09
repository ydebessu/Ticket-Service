package com.ticketservice.model;

import com.ticketservice.service.IReleaseHandler;
import com.ticketservice.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SeatHoldImpl implements SeatHold{
    private static Object mutex = new Object();
    private static int id = 0;
    private String email;
    private int uniqueId;
    private boolean isExpired = false;
    private boolean isReserved = false;
    private List<Seats> seatsList = new ArrayList<Seats>();
    private Timer timer;
    private IReleaseHandler callback;

    public SeatHoldImpl(String email, List<Seats> seatsList, IReleaseHandler callback) {
        synchronized (mutex) {
            this.uniqueId = id;
            id++;
        }
        this.email = email;
        this.seatsList = seatsList;
        this.callback = callback;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override public void run() {
                if(!isReserved()) {
                    isExpired = true;
                    release();
                }
            }
        }, Constants.DELAY_BEFORE_HOLD_EXPIRATION);
    }

    protected void release() {
        callback.release(this);
    }

    public String getEmail() {
        return email;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }

    @Override
    public int getId() {
        return uniqueId;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
}
