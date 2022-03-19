package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class FnshsEvent extends Event implements TimedEvent, ReferenceEvent, ShuttleEvent {
    private Time tm;
    private Reference ref;

    public FnshsEvent(Time tm, Reference ref) {
        super("F-nshs");
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
        return (this.tm.toString() + ";F-nshs;" + ref.toString());
    }

    @Override
    public String getDescription() {
        return "Finish non-repeating shuttle feeder service.";
    }

    @Override
    public String getContextualDescription() {
        return ("Finishes a shuttle feeder service at time " + tm.toString() + " to form shuttle " + ref.toString());
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof FnshsEvent newEvent) {
            return new FnshsEvent(new Time(newEvent.tm), new Reference(newEvent.ref));
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
