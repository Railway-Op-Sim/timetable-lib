package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.location.StartLocation;
import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class SntshEvent extends Event implements TimedEvent, ReferenceEvent, ShuttleEvent {
    private Time tm;
    private StartLocation location;
    private Reference ref;


    public SntshEvent(Time tm, StartLocation location, Reference ref) {
        super("Snt-sh");
        this.tm = tm;
        this.location = location;
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
        this.tm.addMinutes(minutes);
    }

    @Override
    public Time getTime() {
        return this.tm;
    }

    @Override
    public String toString() {
        return (tm.toString() + ";Snt-sh;" + location.toString() + ";" + ref.toString());
    }

    @Override
    public String getDescription() {
        return "Start new shuttle train at a timetabled stop.";
    }

    @Override
    public String getContextualDescription() {
        return ("Shuttle service starts at " + location.toString() + " at time " + tm.toString() + " and becomes " + ref.toString());
    }

    @Override
    public Event newInstance(Event event) {
        if(event instanceof SntshEvent sntshevent) {
            return new SntshEvent(new Time(sntshevent.tm), new StartLocation(sntshevent.location.toString()), new Reference(sntshevent.ref));
        } else {
            return null;
        }
    }

    @Override
    public void validateEvent() throws EventInvalidException {
        if(tm == null || ref == null || location == null) {
            throw new EventInvalidException(this.toString());
        }
    }
    
}
