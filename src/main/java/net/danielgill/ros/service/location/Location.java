package net.danielgill.ros.service.location;

public class Location {
    private int xID;
    private int yID;
    private String name;
    
    public Location(String location, String name) {
        String[] split = location.split("-");
        xID = Integer.parseInt(split[0]);
        yID = Integer.parseInt(split[1]);
        this.name = name;
    }
    public Location(String location) {
        String[] split = location.split("-");
        xID = Integer.parseInt(split[0]);
        yID = Integer.parseInt(split[1]);
        this.name = "";
    }
    
    @Override
    public String toString() {
        return xID + "-" + yID;
    }
}
