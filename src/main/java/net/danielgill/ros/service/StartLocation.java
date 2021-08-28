package net.danielgill.ros.service;

public class StartLocation {
    private Location[] locations;
    
    public StartLocation(String rear, String front) {
        locations = new Location[]{new Location(rear), new Location(front)};
    }
    public StartLocation(String location) {
        String[] locationSplit = location.split(" ");
        locations = new Location[]{new Location(locationSplit[0]), new Location(locationSplit[1])};
    }
    
    @Override
    public String toString() {
        return locations[0].toString() + " " + locations[1].toString();
    }
}
