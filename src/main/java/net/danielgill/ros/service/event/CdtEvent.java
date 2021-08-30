package net.danielgill.ros.service.event;

import net.danielgill.ros.service.time.Time;

public class CdtEvent extends Event {
    private Time time;
    
    public CdtEvent(Time time) {
        super("cdt");
        this.time = time;
    }
    
    public String toString() {
        return time.toString() + ";cdt";
    }
}
