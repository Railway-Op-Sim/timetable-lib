package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class FnsEvent extends Event implements ReferenceEvent, TimedEvent {
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
        if(event instanceof FnsEvent fnsevent) {
            return new FnsEvent(new Time(fnsevent.time), new Reference(fnsevent.ref));
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

    public Time getTime() {
        return this.time;
    }
    
    public Reference getRef() {
        return this.ref;
    }

    @Override
    public String getDescription() {
        return "Finish and Form New Service";
    }

    @Override
    public String getContextualDescription() {
        return ("Forms new service " + ref.toString() + " at " + time.toString());
    }
}
