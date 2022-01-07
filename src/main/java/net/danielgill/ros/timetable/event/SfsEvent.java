package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class SfsEvent extends Event implements ReferenceEvent,TimedEvent {
    private Time time;
    private Reference ref;
    
    public SfsEvent(Time time, Reference ref) {
        super("Sfs");
        this.time = time;
        this.ref = ref;
    }

    @Override
    public String toString() {
        return time.toString() + ";Sfs;" + ref.toString();
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof SfsEvent) {
            SfsEvent sfsevent = (SfsEvent) event;
            return new SfsEvent(new Time(sfsevent.time), new Reference(sfsevent.ref));
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
    public void incrementRef(int increment) {
        this.ref.incrementRef(increment);
    }

    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }
    
}
