package net.danielgill.ros.service.event;

import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.time.Time;

public class FspEvent extends Event implements TimedEvent, ReferenceEvent {
    private Time time;
    private Reference ref;
    
    public FspEvent(Time time, Reference ref) {
        super("fsp");
        this.time = time;
        this.ref = ref;
    }

    @Override
    public String toString() {
        return time.toString() + ";fsp;" + ref.toString();
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof FspEvent) {
            FspEvent fspevent = (FspEvent) event;
            return new FspEvent(new Time(fspevent.time), new Reference(fspevent.ref));
        } else {
            return null;
        }
    }

    @Override
    public void validateEvent() throws EventInvalidException {
        if(time == null || ref == null) {
            throw new EventInvalidException(this.toString());
        }
    }

    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public void incrementRef(int increment) {
        this.ref.incrementRef(increment);
    }
    
}
