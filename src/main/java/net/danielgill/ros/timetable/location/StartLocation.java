package net.danielgill.ros.timetable.location;

/**
 * A class to represent a StartLocation, which has two Location values.
 * @author Daniel Gill
 */
public class StartLocation {
    private Location[] locations;
    private String label;

    /**
     * Creates a new instance from two location strings.
     * @param rear The element at the rear.
     * @param front The element at the front.
     */
    public StartLocation(String rear, String front) {
        locations = new Location[]{new Location(rear), new Location(front)};
        this.label = "";
    }

    public StartLocation(String rear, String front, String label) {
        locations = new Location[]{new Location(rear), new Location(front)};
        this.label = label;
    }

    /**
     * Creates a new instance from a single string.
     * @param location A string which contains the whole start location. ("00-00 00-00")
     */
    public StartLocation(String location) {
        String[] locationSplit = location.split(" ");
        locations = new Location[]{new Location(locationSplit[0]), new Location(locationSplit[1])};
    }

    /**
     * Returns the StartLocation in the correct format.
     * @return The String in the correct format for printing to a .ttb file.
     */
    @Override
    public String toString() {
        return locations[0].toString() + " " + locations[1].toString();
    }

    public String getLabel() {
        return this.label;
    }

    public Location getRearLocation() {
        return this.locations[0];
    }

    public Location getFrontLocation() {
        return this.locations[1];
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof StartLocation) {
            StartLocation other = (StartLocation) o;
            return this.locations[0].equals(other.getRearLocation()) && this.locations[1].equals(other.getFrontLocation());
        }
        return false;
    }
}
