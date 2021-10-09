package net.danielgill.ros.service.event;

import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.time.Time;

public class FnsEvent extends ReferenceEvent {
    private Time time;
    private Reference ref;
    
    public FnsEvent(Time time, Reference ref) {
        super("Fns");
        this.time = time;
        this.ref = ref;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Fns;" + ref.toString();
    }
    
    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public FnsEvent newInstance(Event event) {
        if(event instanceof FnsEvent) {
            FnsEvent fnsevent = (FnsEvent) event;
            return new FnsEvent(new Time(fnsevent.time), fnsevent.ref);
        } else {
            return null;
        }
    }
    
    @Override
    public void incrementRef(int increment) {
        this.ref.incrementRef(increment);
    }
}
