package net.danielgill.ros.service.event;

import net.danielgill.ros.service.Location;
import net.danielgill.ros.service.Time;

public class FerEvent extends Event {
    private Time time;
    private Location location;
    
    public FerEvent(Time time, Location location) {
        super("Fer");
        this.time = time;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Fer;" + location.toString();
    }
}
