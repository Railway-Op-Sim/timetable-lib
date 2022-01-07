package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class RspEvent extends Event implements TimedEvent, ReferenceEvent {
    private Time time;
    private Reference ref;
    
    public RspEvent(Time time, Reference ref) {
        super("rsp");
        this.time = time;
        this.ref = ref;
    }

    @Override
    public String toString() {
        return time.toString() + "rsp" + ref.toString();
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof RspEvent) {
            RspEvent rspevent = (RspEvent) event;
            return new RspEvent(new Time(rspevent.time), new Reference(rspevent.ref));
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
