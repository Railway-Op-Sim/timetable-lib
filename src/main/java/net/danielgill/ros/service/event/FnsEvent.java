package net.danielgill.ros.service.event;

import net.danielgill.ros.service.Time;

public class FnsEvent extends Event {
    private Time time;
    private String ref;
    
    public FnsEvent(Time time, String ref) {
        super("Fns");
        this.time = time;
        this.ref = ref;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Fns;" + ref;
    }
}
