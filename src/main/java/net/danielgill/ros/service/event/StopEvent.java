package net.danielgill.ros.service.event;

import net.danielgill.ros.service.NamedLocation;
import net.danielgill.ros.service.time.Time;

public class StopEvent extends Event {
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
}
