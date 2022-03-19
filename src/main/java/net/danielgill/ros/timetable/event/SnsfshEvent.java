package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class SnsfshEvent extends Event implements TimedEvent, ReferenceEvent, ShuttleEvent {
    private Time tm;
    private Reference ref;

    public SnsfshEvent(Time tm, Reference ref) {
        super("Sns-fsh");
        this.tm = tm;
        this.ref = ref;
    }

    @Override
    public void incrementRef(int increment) {
        ref.incrementRef(increment);
    }

    @Override
    public Reference getRef() {
        return this.ref;
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
    public String toString() {
        return (this.tm.toString() + ";Sns-fsh;" + ref.toString());
    }

    @Override
    public String getDescription() {
        return "Start new non-repeating shuttle finishing service.";
    }

    @Override
    public String getContextualDescription() {
        return ("Start a new non-repeating service at time " + tm.toString() + " from shuttle " + ref.toString());
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof SnsfshEvent newEvent) {
            return new SnsfshEvent(new Time(newEvent.tm), new Reference(newEvent.ref));
        } else {
            return null;
        }
    }

    @Override
    public void validateEvent() throws EventInvalidException {
        if(tm == null || ref == null) {
            throw new EventInvalidException(this.toString());
        }
    }
}
