package com.ticketservice.venue;

import com.ticketservice.utils.Storage;
import java.util.HashMap;

/**
 * Created by ydebessu on 11/8/2015.
 */
public class VenueBuilder {
    public static Venue BuildVenue(String name) {
        Venue venue = new Venue();
        HashMap<Integer, IVenueLevel> levels = new HashMap<Integer, IVenueLevel>();
        String[] specifications = Storage.VENUES_DATABASE.get(name);
        if(specifications != null && specifications.length != 0) {
            for(String specification:specifications) {
                IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel(specification);
                if(venueLevel != null) {
                    levels.put(venueLevel.getLevelId(), venueLevel);
                }
            }
            venue.setVenueLevels(levels);
            return venue;
        }
        return null;
    }

    /**
     * Created by ydebessu on 11/8/2015.
     */
    private static class Venue implements IVenue{
        private HashMap<Integer, IVenueLevel> venueLevels;

        @Override
        public int getAvailableSeats(int venueLevelId) {
            IVenueLevel venueLevel = venueLevels.get(venueLevelId);
            if(venueLevel == null) {
                return 0;
            } else {
                return venueLevel.getAvailableSeats();
            }
        }

        @Override
        public int reserveSeats(int venueLevelId, int numberOfSeats) {
            IVenueLevel venueLevel = venueLevels.get(venueLevelId);
            if(venueLevel == null) {
                return 0;
            } else {
                return venueLevel.reserveSeats(numberOfSeats);
            }
        }

        @Override
        public int holdSeats(int venueLevelId, int numberOfSeats) {
            IVenueLevel venueLevel = venueLevels.get(venueLevelId);
            if(venueLevel == null) {
                return 0;
            } else {
                return venueLevel.holdSeats(numberOfSeats);
            }
        }

        @Override
        public int releaseHoldSeats(int venueLevelId, int numberOfSeats) {
            IVenueLevel venueLevel = venueLevels.get(venueLevelId);
            if(venueLevel == null) {
                return 0;
            } else {
                return venueLevel.releaseHoldSeats(numberOfSeats);
            }
        }

        public void setVenueLevels(HashMap<Integer, IVenueLevel> venueLevels) {
            this.venueLevels = venueLevels;
        }
    }
}
