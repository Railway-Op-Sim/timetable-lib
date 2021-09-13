package net.danielgill.ros.service.location;

public class Location {
    private final int xID;
    private final int yID;
    
    public Location(String location) {
        String[] split = location.split("-");
        xID = Integer.parseInt(split[0]);
        yID = Integer.parseInt(split[1]);
    }
    
    @Override
    public String toString() {
        return xID + "-" + yID;
    }
}
