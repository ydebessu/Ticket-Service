package com.ticketservice.service;

import com.ticketservice.model.SeatHold;
import com.ticketservice.model.SeatHoldImpl;
import com.ticketservice.model.SeatReserved;
import com.ticketservice.model.Seats;
import com.ticketservice.utils.Constants;
import com.ticketservice.venue.IVenue;
import com.ticketservice.venue.VenueBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ydebessu on 11/8/2015.
 */
public class TicketServiceImpl implements TicketService, IReleaseHandler {
    private IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
    private DataStoreService dataStore = new DataStoreService();

    @Override
    public synchronized void release(SeatHold seatHold) {
        dataStore.removeHold(seatHold.getId());
        List<Seats> seatsList = seatHold.getSeatsList();
        for(Seats seats:seatsList) {
            venue.releaseHoldSeats(seats.getVenueLevelId(),seats.getNumberOfSeats());
        }
    }

    @Override
    public int numSeatsAvailable(Optional<Integer> venueLevel) {
        if (venueLevel != null && venueLevel.isPresent()) {
            int venueLevelId = venueLevel.get();
            return venue.getAvailableSeats(venueLevelId);
        }
        return 0;
    }

    @Override
    public synchronized SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel,
                    Optional<Integer> maxLevel, String customerEmail) {
        int minLevelId = minLevel.isPresent() ? minLevel.get() : Constants.RESTON_MIN_LEVEL;
        int maxLevelId = maxLevel.isPresent() ? maxLevel.get() : Constants.RESTON_MAX_LEVEL;
        if (isCapacity(numSeats, minLevelId, maxLevelId)) {
            int seatsToHold = numSeats;
            List<Seats> seatsList = new ArrayList<Seats>();
            int levelId = minLevelId;
            while (seatsToHold > 0 && levelId <= maxLevelId) {
                int held = venue.holdSeats(levelId, seatsToHold);
                seatsToHold -= held;
                Seats seats = new Seats(levelId, held);
                seatsList.add(seats);
                levelId++;
            }

            if (seatsToHold == 0) {
                SeatHold seatHold = new SeatHoldImpl(customerEmail, seatsList, this);
                dataStore.putSeatHold(seatHold);
                return seatHold;
            }
       }
        return null;
    }

    @Override
    public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
        SeatHold seatHold = dataStore.removeHoldToReserve(seatHoldId, customerEmail);
        if(seatHold == null){
            return null;
        }

        List<Seats> seatsList = seatHold.getSeatsList();
        if(seatHold.isExpired()) {
            for(Seats seats:seatsList) {
                venue.releaseHoldSeats(seats.getVenueLevelId(),seats.getNumberOfSeats());
            }
            return null;
        } else {
            for(Seats seats:seatsList) {
                venue.releaseHoldSeats(seats.getVenueLevelId(),seats.getNumberOfSeats());
                venue.reserveSeats(seats.getVenueLevelId(), seats.getNumberOfSeats());
            }
            SeatReserved seatReserved = new SeatReserved(seatHold.getEmail(),seatsList);
            String reservesionCode = dataStore.putSeatReserved(seatReserved);
            return reservesionCode;
        }

    }

    private boolean isCapacity(int numSeats, int minLevelId, int maxLevelId) {
        int totalCapacity = 0;
        for(int i = minLevelId; i <= maxLevelId; i++) {
            totalCapacity += venue.getAvailableSeats(i);
        }
        return totalCapacity >= numSeats;
    }

    /**
     * This method is for demonstration purposes only.
     *
     */
    public void displayStateOfVenue() {
        System.out.printf("\nCurrent state of the venue:\n");
        System.out.printf("===============================:\n");
        System.out.printf("Available Seats per level:\n");
        System.out.printf("\tOrchestra: %d\n", venue.getAvailableSeats(1));
        System.out.printf("\tMain: %d\n", venue.getAvailableSeats(2));
        System.out.printf("\tBalcony1: %d\n", venue.getAvailableSeats(3));
        System.out.printf("\tBalcony2: %d\n", venue.getAvailableSeats(4));

        System.out.printf("\nOn hold seats:\n");
        dataStore.displayOnHold();

        System.out.printf("\nReserved seats:\n");
        dataStore.displayReserved();

    }
}
