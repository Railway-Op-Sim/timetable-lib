package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class SnsEvent extends Event implements ReferenceEvent,TimedEvent {
    private Time time;
    private Reference ref;
    
    public SnsEvent(Time time, Reference ref) {
        super("Sns");
        this.time = time;
        this.ref = ref;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Sns;" + ref.toString();
    }
    
    public Time getTime() {
        return time;
    }
    public Reference getRef() {
        return ref;
    }

    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public SnsEvent newInstance(Event event) {
        if(event instanceof SnsEvent snsEvent) {
            return new SnsEvent(new Time(snsEvent.time), new Reference(snsEvent.ref));
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
    public String getDescription() {
        return "Start from Another Service";
    }

    @Override
    public String getContextualDescription() {
        return ("Starts from " + ref.toString() + " at time " + time.toString());
    }
}
