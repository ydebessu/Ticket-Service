package com.ticketservice.venue;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ydebessu on 11/7/2015.
 */
public class VenueLevelBuilder {
    private static final int NUMBER_OF_PARAMS = 7;
    private static final Logger logger =
                    Logger.getLogger(VenueLevelBuilder.class.getName());

    public static VenueLevel BuildLevel(String info) {
        VenueLevel venueLevel = new VenueLevel();
        if(info != null && !info.isEmpty()) {
            String[] values = info.split(" ");
            if(values.length != NUMBER_OF_PARAMS) {
                logger.log(Level.SEVERE, "Corrupted message");
                return null;
            }
            else {
                venueLevel.setLevelId(Integer.parseInt(values[0]));
                venueLevel.setLevelName(values[1]);
                venueLevel.setSeatPrice(Double.parseDouble(values[2]));
                venueLevel.setRows(Integer.parseInt(values[3]));
                venueLevel.setSeatsPerRow(Integer.parseInt(values[4]));
                venueLevel.setReserved(Integer.parseInt(values[5]));
                venueLevel.setHeld(Integer.parseInt(values[6]));
                return venueLevel;
            }
        }
        return null;
    }

    /**
     * Created by ydebessu on 11/7/2015.
     */
    private static class VenueLevel implements  IVenueLevel {
        private int levelId;
        private String levelName;
        private double seatPrice;
        private int rows;
        private int seatsPerRow;
        private int reserved;
        private int held;

        @Override
        public int getAvailableSeats() {
            return getSeatsPerRow() * getRows() - getReserved() - getHeld();
        }

        @Override
        public int reserveSeats(int numberOfSeats) {
            int availableForReservation = getAvailableSeats() >= numberOfSeats ? numberOfSeats:getAvailableSeats();
            reserved = reserved + availableForReservation;
            return availableForReservation;
        }

        @Override
        public int holdSeats(int numberOfSeats) {
            int availableToHold = getAvailableSeats() >= numberOfSeats ? numberOfSeats:getAvailableSeats();
            held = held + availableToHold;
            return availableToHold;
        }

        @Override
        public int releaseHoldSeats(int numberOfSeats) {
            int availableToRelease = getHeld() >= numberOfSeats ? numberOfSeats:getHeld();
            held = held - availableToRelease;
            return availableToRelease;
        }

        public int getLevelId() {
            return levelId;
        }

        public void setLevelId(int levelId) {
            this.levelId = levelId;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public double getSeatPrice() {
            return seatPrice;
        }

        public void setSeatPrice(double seatPrice) {
            this.seatPrice = seatPrice;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getSeatsPerRow() {
            return seatsPerRow;
        }

        public void setSeatsPerRow(int seatsPerRow) {
            this.seatsPerRow = seatsPerRow;
        }

        public int getReserved() {
            return reserved;
        }

        public void setReserved(int reserved) {
            this.reserved = reserved;
        }

        public int getHeld() {
            return held;
        }

        public void setHeld(int held) {
            this.held = held;
        }
    }
}
