package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class FrhshEvent extends Event implements TimedEvent, ReferenceEvent, ShuttleEvent {
    private Time tm;
    private Reference ref;

    public FrhshEvent(Time tm, Reference ref) {
        super("Frh-sh");
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
        return (this.tm.toString() + ";Frh-sh;" + ref.toString());
    }

    @Override
    public String getDescription() {
        return "Finish and repeat shuttle, finally remain here.";
    }

    @Override
    public String getContextualDescription() {
        return ("Finish and repeat shuttle at time " + tm.toString() + " with " + ref.toString() + " and finally finish and remain here");
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof FrhshEvent newEvent) {
            return new FrhshEvent(new Time(newEvent.tm), new Reference(newEvent.ref));
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
