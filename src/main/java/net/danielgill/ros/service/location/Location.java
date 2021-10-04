package net.danielgill.ros.service.location;

public class Location {
    private final String xID;
    private final String yID;
    
    public Location(String location) {
        String[] split = location.split("-");
        xID = split[0];
        yID = split[1];
    }
    
    @Override
    public String toString() {
        return xID + "-" + yID;
    }
}
