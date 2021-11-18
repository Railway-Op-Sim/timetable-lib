package net.danielgill.ros.service.event;

import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.time.Time;

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
        if(event instanceof SnsEvent) {
            SnsEvent snsEvent = (SnsEvent) event;
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
}
