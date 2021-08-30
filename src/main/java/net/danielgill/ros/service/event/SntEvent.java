package net.danielgill.ros.service.event;

import net.danielgill.ros.service.location.StartLocation;
import net.danielgill.ros.service.time.Time;

public class SntEvent extends Event {
    private Time time;
    private StartLocation location;
    
    public SntEvent(Time time, StartLocation location) {
        super("Snt");
        this.time = time;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Snt;" + location.toString();
    }
    
    public Time getTime() {
        return time;
    }
    public StartLocation getLocation() {
        return location;
    }
}
