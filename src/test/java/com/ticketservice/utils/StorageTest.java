package com.ticketservice.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ydebessu on 11/7/2015.
 */
public class StorageTest {
    @Test
    public void verifyRestonPerformanceVenueInformationIsAccessible() {
        Assert.assertEquals("Number of levels is not matching", 4,
                        Storage.RESTON_PERFORMANCE_VENUE.length);
    }

    @Test
    public void verifyRestonPerformanceVenueInformationIsInHashMap() {
        Assert.assertEquals("Information in HashMap is not correct. Number of levels:", 4,
                        Storage.VENUES_DATABASE.get(Constants.RESTON).length);
    }

    @Test
    public void verifyRestonPerformanceVenueInformationIsAccessibleThroughObject() {
        Storage storage = new Storage();
        Assert.assertEquals("Number of levels is not matching", 4, storage.RESTON_PERFORMANCE_VENUE.length);
    }

    @Test
    public void verifyConstantsExists() {
        Assert.assertNotNull(new Constants());
    }
}
