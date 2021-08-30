package net.danielgill.ros.service.event;

import net.danielgill.ros.service.location.NamedLocation;
import net.danielgill.ros.service.time.Time;

public class PassEvent extends TimedEvent {
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
    
    @Override
    public void incrementTime(int minutes) {
        this.passTime.addMinutes(minutes);
    }
}
