package net.danielgill.ros.service.event;

import net.danielgill.ros.service.location.StartLocation;
import net.danielgill.ros.service.time.Time;

public class SntEvent extends TimedEvent {
    private Time time;
    private StartLocation location;
    
    public SntEvent(Time time, StartLocation location) {
        super("Snt");
        this.time = time;
        this.location = location;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Snt;" + location.toString();
    }
    
    public Time getTime() {
        return time;
    }
    public StartLocation getLocation() {
        return location;
    }
    
    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public SntEvent newInstance(Event event) {
        if(event instanceof SntEvent) {
            SntEvent snsevent = (SntEvent) event;
            return new SntEvent(new Time(snsevent.time), snsevent.location);
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
