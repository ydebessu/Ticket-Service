package com.ticketservice.service;

import com.ticketservice.model.SeatHold;
import com.ticketservice.model.SeatHoldImpl;
import com.ticketservice.model.SeatReserved;
import com.ticketservice.model.Seats;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by ydebessu on 11/9/2015.
 */
public class DataStoreService {

    private HashMap<Integer,SeatHold> heldSeatsTable = new HashMap<Integer, SeatHold>();
    private HashMap<String, SeatReserved> reservedSeatsTable = new HashMap<String, SeatReserved>();

    public void putSeatHold(SeatHold seatHold) {
        heldSeatsTable.put(Integer.valueOf(seatHold.getId()),seatHold);
    }

    public String putSeatReserved(SeatReserved seatReserved) {
        String key = UUID.randomUUID().toString();
        reservedSeatsTable.put(key,seatReserved);
        return key;
    }

    public SeatHold removeHoldToReserve(int seatHoldId, String customerEmail) {
        SeatHoldImpl seatHold = (SeatHoldImpl) heldSeatsTable.remove(seatHoldId);
        if (seatHold == null) {
            return null;
        }
        if(seatHold.getEmail().equalsIgnoreCase(customerEmail)) {
            seatHold.setReserved(true);
            return  seatHold;
        } else {
            putSeatHold(seatHold);
        }
        return null;
    }

    public boolean removeHold(int id) {
        SeatHold seatHold = heldSeatsTable.remove(id);
        return seatHold!=null;
    }

    /**
     * For demonstration purposes only
     */
    public void displayOnHold() {
        String[] names = {"Orchestra", "Main", "Balcony1", "Balcony2"};
        Collection<SeatHold> seatHoldList = heldSeatsTable.values();
        for(SeatHold seatHold:seatHoldList){
            System.out.printf("\tFor %s:\n", seatHold.getEmail());
            List<Seats> seatsList = seatHold.getSeatsList();
            for(Seats seats:seatsList) {
                System.out.printf("\t %s: %d\n", names[seats.getVenueLevelId()-1], seats.getNumberOfSeats());
            }
        }
    }

    /**
     * For demonstration purposes only
     */
    public void displayReserved() {
        String[] names = {"Orchestra", "Main", "Balcony1", "Balcony2"};
        Collection<SeatReserved> seatReservedList = reservedSeatsTable.values();
        for(SeatReserved seatReserved:seatReservedList){
            System.out.printf("\tFor %s:\n", seatReserved.getEmail());
            List<Seats> seatsList = seatReserved.getSeatsList();
            for(Seats seats:seatsList) {
                System.out.printf("\t %s: %d\n", names[seats.getVenueLevelId()-1], seats.getNumberOfSeats());
            }
        }
    }
}
