package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class FjoEvent extends Event implements TimedEvent, ReferenceEvent {
    private Time time;
    private Reference ref;

    public FjoEvent(Time time, Reference ref) {
        super("Fjo");
        this.time = time;
        this.ref = ref;
    }

    @Override
    public void incrementRef(int increment) {
        this.ref.incrementRef(increment);
    }

    @Override
    public Reference getRef() {
        return this.ref;
    }

    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public Time getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return (time.toString() + ";Fjo;" + ref.toString());
    }

    @Override
    public String getDescription() {
        return "Finish and Join Other";
    }

    @Override
    public String getContextualDescription() {
        return ("Finishes and joins " + ref.toString() + " at time " + time.toString());
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof FjoEvent fjo) {
            return new FjoEvent(new Time(this.time), new Reference(this.ref));
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
    
}
