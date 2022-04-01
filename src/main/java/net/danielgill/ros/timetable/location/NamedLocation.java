package net.danielgill.ros.timetable.location;

/**
 * A class which stores a location based on a name String.
 * @author Daniel Gill
 */
public class NamedLocation {
    private String name;

    /**
     * Creates a new NamedLocation instance from a location string.
     * @param name The String representing the location.
     */
    public NamedLocation(String name) {
        this.name = name;
    }

    /**
     * Returns the location as a String.
     * @return The location as a String.
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof NamedLocation) {
            NamedLocation other = (NamedLocation) o;
            return this.name.equals(other.name);
        }
        return false;
    }
}
