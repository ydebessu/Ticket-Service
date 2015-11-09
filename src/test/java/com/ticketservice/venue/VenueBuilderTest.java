package com.ticketservice.venue;

import com.ticketservice.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ydebessu on 11/8/2015.
 */
public class VenueBuilderTest {
    /**
     * VenuBuilder BuildVenue method should return null if string parameter is null
     */
    @Test
    public void venuBuilderReturnsNullIfStringParamIsNull() {
        VenueBuilder venueBuilder = new VenueBuilder();
        IVenue venue = venueBuilder.BuildVenue(null);
        Assert.assertNull("Venue built with null input param:", venue);
    }

    /**
     * VenuBuilder BuildVenue method should return a Venue containing four levels if string parameter is
     * "Reston". Total seats in four levels is 1250
     */
    @Test
    public void venueBuilderReturnsVenueIfStringParamIsValid() {
        int totalSeatsLevel1 = 1250;
        VenueBuilder venueBuilder = new VenueBuilder();
        IVenue venue = venueBuilder.BuildVenue(Constants.RESTON);
        Assert.assertEquals("Venue available seats:", totalSeatsLevel1, venue.getAvailableSeats(1));
    }

    /**
     * reserveSeats method should return a the number of successfully reserved seats
     */
    @Test
    public void reserveSeatsReturnsNumberOfReservdeSeatsForGivenRequest() {
        int levelId = 4;
        int numberOfSeats = 100;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);

        int reserved = venue.reserveSeats(levelId, numberOfSeats);
        Assert.assertEquals("Reserved seats:", numberOfSeats, reserved);
    }

    /**
     * reserveSeats method should return a the number of successfully reserved seats
     */
    @Test
    public void reserveSeatsReturnsNumberOfActualReservdeSeats() {
        int venueLevel = 4;
        int numberOfSeatsRequested = 2000;
        int maxAvailavble = 1500;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
        int reserved = venue.reserveSeats(venueLevel, numberOfSeatsRequested);
        Assert.assertEquals("Reserved seats:", maxAvailavble, reserved);
    }

    /**
     * reserveSeats method should return 0 if the requested level doesn't exist
     */
    @Test
    public void reserveSeatsReturnsZeroForNonExistingLevel() {
        int nonExistingVenueLevel = 5;
        int numberOfSeats = 100;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
        int reserved = venue.reserveSeats(nonExistingVenueLevel, numberOfSeats); // 5 is non existing id
        Assert.assertEquals("Reserved seats:", 0, reserved);
    }

    /**
     * holdSeats method should return a the number of successfully held seats
     */
    @Test
    public void holdSeatsReturnsNumberOfHeldSeatsForGivenRequest() {
        int levelId = 4;
        int numberOfSeats = 100;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);

        int held = venue.holdSeats(levelId, numberOfSeats);
        Assert.assertEquals("Held seats:", numberOfSeats, held);
    }

    /**
     * holdSeats method should return a the number of successfully held seats
     * which is the max available.
     */
    @Test
    public void holdSeatsReturnsNumberOfActualReservdeSeats() {
        int venueLevel = 4;
        int numberOfSeatsRequested = 2000;
        int numberOfSeatsReserved = 500;
        int maxAvailavbleForHold = 1000; //1500-500
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
        venue.reserveSeats(venueLevel, numberOfSeatsReserved);
        int held = venue.holdSeats(venueLevel, numberOfSeatsRequested);
        Assert.assertEquals("Held seats:", maxAvailavbleForHold, held);
    }

    /**
     * holdSeats method should return 0 if the requested level doesn't exist
     */
    @Test
    public void holdSeatsReturnsZeroForNonExistingLevel() {
        int nonExistingVenueLevel = 5;
        int numberOfSeats = 100;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
        int reserved = venue.holdSeats(nonExistingVenueLevel, numberOfSeats);
        Assert.assertEquals("Held seats:", 0, reserved);
    }

    /**
     * releaseHoldSeats method should return the number of successfully released seats
     */
    @Test
    public void releaseHoldSeatsReturnsNumberOfReleasedSeatsForGivenRequest() {
        int levelId = 4;
        int numberOfSeats = 100;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
        int held = venue.holdSeats(levelId, numberOfSeats);
        int released = venue.releaseHoldSeats(levelId,held+100); //extra 100
        Assert.assertEquals("Held seats:", held, released);
    }

    /**
     * releaseHoldSeats method should return the number of successfully released seats
     */
    @Test
    public void releaseHoldSeatsReturnsZeroForNonExistingLevel() {
        int levelId = 4;
        int numberOfSeats = 100;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
        int held = venue.holdSeats(levelId, numberOfSeats);
        int released = venue.releaseHoldSeats(levelId,held-50); //release only part of held
        Assert.assertEquals("Held seats:", held-50 , released);
    }

    /**
     * releaseHoldSeats method should return 0 if the requested level doesn't exist
     */
    @Test
    public void releaseHoldSeatsReturnsOnlyNumberOfReleasedSeatsForGivenRequest() {
        int levelId = 5;
        int numberOfSeats = 100;
        IVenue venue = VenueBuilder.BuildVenue(Constants.RESTON);
        int released = venue.releaseHoldSeats(levelId,numberOfSeats); //release only part of held
        Assert.assertEquals("Held seats:", 0 , released);
    }
}
