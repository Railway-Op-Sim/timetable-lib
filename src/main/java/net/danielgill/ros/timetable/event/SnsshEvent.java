package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class SnsshEvent extends Event implements ReferenceEvent, TimedEvent, ShuttleEvent {
    private Time tm;
    private Reference shuttleLinkRef;
    private Reference feederShuttleRef;

    public SnsshEvent(Time tm, Reference shuttleLinkRef, Reference feederShuttleRef) {
        super("Sns-sh");
        this.tm = tm;
        this.shuttleLinkRef = shuttleLinkRef;
        this.feederShuttleRef = feederShuttleRef;
    }

    @Override
    public void incrementTime(int minutes) {
        this.tm.addMinutes(minutes);
    }

    @Override
    public Time getTime() {
        return this.tm;
    }

    @Override
    public void incrementRef(int increment) {
        shuttleLinkRef.incrementRef(increment);
        feederShuttleRef.incrementRef(increment);
    }

    @Override
    public Reference getRef() {
        throw new UnsupportedOperationException("Unsupported method for SnsshEvent.");
    }

    @Override
    public String toString() {
        return (tm.toString() + ";Sns-sh;" + shuttleLinkRef.toString() + ";" + feederShuttleRef.toString());
    }

    @Override
    public String getDescription() {
        return "Start new shuttle service from a feeder.";
    }

    @Override
    public String getContextualDescription() {
        return ("Shuttle service starts from feeder " + feederShuttleRef.getRef() + " at time " + tm.toString() + " and becomes " + shuttleLinkRef.toString());
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof SnsshEvent snsshevent) {
            return new SnsshEvent(new Time(snsshevent.tm), new Reference(snsshevent.shuttleLinkRef), new Reference(snsshevent.feederShuttleRef));
        } else {
            return null;
        }
    }

    @Override
    public void validateEvent() throws EventInvalidException {
        if(tm == null || shuttleLinkRef == null || feederShuttleRef == null) {
            throw new EventInvalidException(this.toString());
        }
    }
    
}
