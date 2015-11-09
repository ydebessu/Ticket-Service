package com.ticketservice.venue;

/**
 * Created by ydebessu on 11/8/2015.
 */
public interface IVenue {
    /**
     * Get the number of available seats in the given levelId
     * @param venueLevel levelId
     * @return number
     */
    public int getAvailableSeats(int venueLevel);

    /**
     * Reserve a given number of seats in a given levelId
     * @param numberOfSeats the requested number
     * @return reserved number
     */

    /**
     * Reserve a given number of seats in a given levelId
     * @param venueLevelId levelId
     * @param numberOfSeats the requested number
     * @return
     */
    public int reserveSeats(int venueLevelId, int numberOfSeats);

    /**
     * Hold a given number of seats for a given level Id
     * @param venueLevelId levelId
     * @param numberOfSeats
     * @return number of seats successfully held
     */
    public int holdSeats(int venueLevelId, int numberOfSeats);

    /**
     * Release a given number of seats in a level that were held
     *
     * @param venueLevelId venueLevel id
     * @param numberOfSeats
     * @return number of seats successfully released
     */
    public int releaseHoldSeats(int venueLevelId, int numberOfSeats);

}
