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
        if(event instanceof FerEvent ferevent) {
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

    public Time getTime() {
        return this.time;
    }

    public Location getLocation() {
        return this.location;
    }

    @Override
    public String getDescription() {
        return "Finish and Exit Railway";
    }

    @Override
    public String getContextualDescription() {
        return ("Exits at " + location.toString() + " at time " + time.toString());
    }
}
