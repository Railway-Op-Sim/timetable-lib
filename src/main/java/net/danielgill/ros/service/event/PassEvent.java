package net.danielgill.ros.service.event;

import net.danielgill.ros.service.location.NamedLocation;
import net.danielgill.ros.service.time.Time;

public class PassEvent extends TimedEvent {
    private Time time;
    private NamedLocation location;
    
    public PassEvent(Time time, NamedLocation location) {
        super("pas");
        this.time = time;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";pas;" + location.toString();
    }
    
    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public PassEvent newInstance(Event event) {
        if(event instanceof PassEvent) {
            PassEvent passevent = (PassEvent) event;
            return new PassEvent(new Time(passevent.time), passevent.location);
        } else {
            return null;
        }
    }
}
