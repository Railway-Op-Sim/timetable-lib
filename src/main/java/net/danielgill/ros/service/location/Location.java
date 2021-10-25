package net.danielgill.ros.service.location;

/**
 * Stores a single location on a route.
 * @author Daniel Gill
 */
public class Location {
    private final String xID;
    private final String yID;

    /**
     * Creates a location instance from a given String.
     * @param location A location string in form "00-00".
     */
    public Location(String location) {
        String[] split = location.split("-");
        xID = split[0];
        yID = split[1];
    }

    /**
     * Returns the location in a string format.
     * @return The location in the .ttb file format. ("00-00")
     */
    @Override
    public String toString() {
        return xID + "-" + yID;
    }
}
