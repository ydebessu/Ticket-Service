package com.ticketservice.utils;

import java.util.HashMap;

/**
 * Created by ydebessu on 11/7/2015.
 */
public class Storage {

    /*
    * Information about the levels available for "Reston Performance Venue"
    * Level Id, Level Name, Price, Rows, Seats in Row, reserved, held
    * */
    public static final String[] RESTON_PERFORMANCE_VENUE = {
                    "1 Orchestra 100.00 25 50 0 0",
                    "2 Main 75.00 20 100 0 0",
                    "3 Balcony1 50.00 15 100 0 0",
                    "4 Balcony2 40.00 15 100 0 0"
    };

    public static HashMap<String, String[]> VENUES_DATABASE = new HashMap<String, String[]>();

    static
    {
        VENUES_DATABASE.put(Constants.RESTON, RESTON_PERFORMANCE_VENUE);
    }
}
