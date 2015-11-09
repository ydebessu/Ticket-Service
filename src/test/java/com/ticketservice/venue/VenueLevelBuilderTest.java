package com.ticketservice.venue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ydebessu on 11/7/2015.
 */
public class VenueLevelBuilderTest {

    @Test
    public void verifyBuilderReturnsNonNullVenueObject() {
        VenueLevelBuilder builder = new VenueLevelBuilder();
        IVenueLevel venueLevel = builder.BuildLevel("1 Orchestra 100.00 25 50 0 0");
        Assert.assertNotNull(venueLevel);
    }

    @Test
    public void verifyBuilderReturnsVenueLevelObject() {
        IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel("1 Orchestra 100.00 25 50 0 0");
        Assert.assertEquals("Level Id", 1, venueLevel.getLevelId());
        Assert.assertEquals("Level name", "Orchestra", venueLevel.getLevelName());
        Assert.assertEquals("Level seat price", 100.00, venueLevel.getSeatPrice(), 0.0000001);
        Assert.assertEquals("Level rows", 1250, venueLevel.getAvailableSeats());
    }

    @Test
    public void verifyBuilderReturnsNullIfInputIsEmpty() {
        IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel("");
        Assert.assertNull(venueLevel);
    }

    @Test
    public void verifyBuilderReturnsNullIfInputHasTooFewParams() {
        IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel("1 Orchestra 100.00 25");
        Assert.assertNull(venueLevel);
    }

    @Test
    public void verifyVenueLevelReservesSeatsCorrectly() {
        IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel("1 Orchestra 100.00 25 50 0 0");
        int heldSeats = venueLevel.holdSeats(50);
        int heldSeats2 = venueLevel.holdSeats(1250);
        Assert.assertEquals("Held seats", 50, heldSeats);
        Assert.assertEquals("Held seats", 1200, heldSeats2);
    }

    @Test
    public void verifyVenueLevelHoldSeatsCorrectly() {
        IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel("1 Orchestra 100.00 25 50 0 0");
        int reservedSeats = venueLevel.reserveSeats(100);
        int reservedSeats2 = venueLevel.reserveSeats(1250);
        Assert.assertEquals("Held seats", 100, reservedSeats);
        Assert.assertEquals("Held seats", 1150, reservedSeats2);
    }

    @Test
    public void verifyVenueLevelReleasesSeatsCorrectly() {
        IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel("1 Orchestra 100.00 25 50 0 0");
        venueLevel.holdSeats(100);
        venueLevel.reserveSeats(200);
        int releasedseats = venueLevel.releaseHoldSeats(200);
        Assert.assertEquals("Released seats", 100, releasedseats);
    }

    @Test
    public void verifyVenueLevelReleasesSeatsCorrectlyWhenAskedLess() {
        IVenueLevel venueLevel = VenueLevelBuilder.BuildLevel("1 Orchestra 100.00 25 50 0 0");
        venueLevel.holdSeats(100);
        venueLevel.reserveSeats(200);
        int releasedseats = venueLevel.releaseHoldSeats(50);
        Assert.assertEquals("Released seats", 50, releasedseats);
    }

}
