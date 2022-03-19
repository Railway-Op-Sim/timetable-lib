package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class FnsshEvent extends Event implements TimedEvent, ReferenceEvent, ShuttleEvent {
    private Time tm;
    private Reference shuttleLinkRef;
    private Reference finishRef;

    public FnsshEvent(Time tm, Reference shuttleLinkRef, Reference finishRef) {
        super("Fns-sh");
        this.tm = tm;
        this.shuttleLinkRef = shuttleLinkRef;
        this.finishRef = finishRef;
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
        finishRef.incrementRef(increment);
    }

    @Override
    public Reference getRef() {
        throw new UnsupportedOperationException("Unsupported method for FnsshEvent.");
    }

    @Override
    public String toString() {
        return (tm.toString() + ";Fns-sh;" + shuttleLinkRef.toString() + ";" + finishRef.toString());
    }

    @Override
    public String getDescription() {
        return "Finish and repeat shuttle, finally form a finishing service.";
    }

    @Override
    public String getContextualDescription() {
        return ("Finish and repeat shuttle at time " + tm.toString() + " with " + shuttleLinkRef.getRef() + " finally becomes " + finishRef.toString());
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof FnsshEvent fnsshevent) {
            return new FnsshEvent(new Time(fnsshevent.tm), new Reference(fnsshevent.shuttleLinkRef), new Reference(fnsshevent.finishRef));
        } else {
            return null;
        }
    }

    @Override
    public void validateEvent() throws EventInvalidException {
        if(tm == null || shuttleLinkRef == null || finishRef == null) {
            throw new EventInvalidException(this.toString());
        }
    }
    
    
}
