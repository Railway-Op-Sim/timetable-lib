package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class JboEvent extends Event implements TimedEvent, ReferenceEvent {
    private Time time;
    private Reference ref;
    
    public JboEvent(Time time, Reference ref) {
        super("jbo");
        this.time = time;
        this.ref = ref;
    }

    @Override
    public String toString() {
        return time.toString() + ";jbo;" + ref.toString();
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof JboEvent) {
            JboEvent jboevent = (JboEvent) event;
            return new JboEvent(new Time(jboevent.time), new Reference(jboevent.ref));
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
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public void incrementRef(int increment) {
        this.ref.incrementRef(increment);
    }
    
}
