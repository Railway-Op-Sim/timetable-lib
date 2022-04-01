package net.danielgill.ros.timetable.location;

/**
 * Stores a single location on a route.
 * @author Daniel Gill
 */
public class Location {
    private final String xID;
    private final String yID;
    private final String label;

    /**
     * Creates a location instance from a given String.
     * @param location A location string in form "00-00".
     */
    public Location(String location) {
        String[] split = location.split("-");
        xID = split[0];
        yID = split[1];
        this.label = "";
    }

    public Location(String location, String label) {
        String[] split = location.split("-");
        xID = split[0];
        yID = split[1];
        this.label = label;
    }

    /**
     * Returns the location in a string format.
     * @return The location in the .ttb file format. ("00-00")
     */
    @Override
    public String toString() {
        return xID + "-" + yID;
    }

    public String getLabel() {
        return this.label;
    }

    public String getXLocation() {
        return this.xID;
    }

    public String getYLocation() {
        return this.yID;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Location) {
            Location loc = (Location) o;
            if(loc.xID.equals(this.xID) && loc.yID.equals(this.yID)) {
                return true;
            }
        }
        return false;
    }
}
