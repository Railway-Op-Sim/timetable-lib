package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.location.NamedLocation;
import net.danielgill.ros.timetable.time.Time;

public class PassEvent extends Event implements TimedEvent {
    private Time time;
    private NamedLocation location;
    
    public PassEvent(Time time, NamedLocation location) {
        super("pas");
        this.time = time;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";pas;" + location.toString();
    }
    
    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public PassEvent newInstance(Event event) {
        if(event instanceof PassEvent) {
            PassEvent passevent = (PassEvent) event;
            return new PassEvent(new Time(passevent.time), passevent.location);
        } else {
            return null;
        }
    }

	@Override
	public void validateEvent() throws EventInvalidException {
        if(time == null || location == null) {
            throw new EventInvalidException(this.toString());
        }
	}
}
