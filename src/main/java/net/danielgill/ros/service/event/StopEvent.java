package net.danielgill.ros.service.event;

import net.danielgill.ros.service.location.NamedLocation;
import net.danielgill.ros.service.time.Time;

public class StopEvent extends TimedEvent {
    private Time arrTime;
    private Time depTime;
    private NamedLocation location;
    
    public StopEvent(Time arrTime, Time depTime, NamedLocation location) {
        super("stop");
        this.arrTime = arrTime;
        this.depTime = depTime;
        this.location = location;
    }
    public StopEvent(Time arrTime, NamedLocation location) {
        super("stop");
        this.arrTime = arrTime;
        this.location = location;
    }
    public StopEvent(NamedLocation location, Time depTime) {
        super("stop");
        this.depTime = depTime;
        this.location = location;
    }
    
    @Override
    public String toString() {
        String output = "";
        if(arrTime != null) {
            output += arrTime.toString() + ";";
        }
        if(depTime != null) {
            output += depTime.toString() + ";";
        }
        return output + location.toString();
    }

    @Override
    public void incrementTime(int minutes) {
        if(arrTime != null) {
            this.arrTime.addMinutes(minutes);
        }
        if(depTime != null) {
            this.depTime.addMinutes(minutes);
        }
    }

    @Override
    public StopEvent newInstance(Event event) {
        if(event instanceof StopEvent) {
            StopEvent stopevent = (StopEvent) event;
            return new StopEvent(new Time(stopevent.arrTime), new Time(stopevent.depTime), stopevent.location);
        } else {
            return null;
        }
    }
}
