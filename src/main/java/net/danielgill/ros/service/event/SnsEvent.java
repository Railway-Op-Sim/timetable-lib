package net.danielgill.ros.service.event;

import net.danielgill.ros.service.Time;

public class SnsEvent extends Event {
    private Time time;
    private String ref;
    
    public SnsEvent(Time time, String ref) {
        super("Sns");
        this.time = time;
        this.ref = ref;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Sns;" + ref;
    }
    
    public Time getTime() {
        return time;
    }
    public String getRef() {
        return ref;
    }
}
