package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.location.Location;
import net.danielgill.ros.timetable.time.Time;

public class FerEvent extends Event implements TimedEvent {
    private Time time;
    private Location location;
    
    public FerEvent(Time time, Location location) {
        super("Fer");
        this.time = time;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Fer;" + location.toString();
    }

    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public FerEvent newInstance(Event event) {
        if(event instanceof FerEvent) {
            FerEvent ferevent = (FerEvent) event;
            return new FerEvent(new Time(ferevent.time), ferevent.location);
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
