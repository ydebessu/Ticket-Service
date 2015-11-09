package com.ticketservice.venue;

/**
 * Created by ydebessu on 11/7/2015.
 */
public interface IVenueLevel {
    /**
     * Get the number of available seats in the given level
     * @return number
     */
    public int getAvailableSeats();

    /**
     * Reserve a given number of seats in a level
     * @param numberOfSeats the requested number
     * @return reserved number
     */
    public int reserveSeats(int numberOfSeats);

    /**
     * Hold a given number of seats in a level
     * @param numberOfseats
     * @return number of seats successfully held
     */
    public int holdSeats(int numberOfseats);

    /**
     * Release a given number of seats in a level that were held
     * @param numberOfseats
     * @return number of seats successfully released
     */
    public int releaseHoldSeats(int numberOfseats);

    /**
     * Get the Id of the level
     * @return id
     */
    public int getLevelId();

    /**
     * Get name of the level
     * @return name
     */
    public String getLevelName();

    /**
     * Get price per seat
     * @return price
     */
    public double getSeatPrice();
}
