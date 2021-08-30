package net.danielgill.ros.service.event;

import net.danielgill.ros.service.NamedLocation;
import net.danielgill.ros.service.time.Time;

public class PassEvent extends Event {
    private Time passTime;
    private NamedLocation location;
    
    public PassEvent(Time passTime, NamedLocation location) {
        super("pas");
        this.passTime = passTime;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return passTime.toString() + ";pas;" + location.toString();
    }
}
