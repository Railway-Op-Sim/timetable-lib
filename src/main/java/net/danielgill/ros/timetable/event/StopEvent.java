package net.danielgill.ros.timetable.event;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.location.NamedLocation;
import net.danielgill.ros.timetable.time.Time;

public class StopEvent extends Event implements TimedEvent {
    private ArrayList<Time> times;
    private NamedLocation location;
    
    public StopEvent(Time arrTime, Time depTime, NamedLocation location) {
        super("stop");
        times = new ArrayList<>();
        times.add(arrTime);
        times.add(depTime);
        this.location = location;
    }
    public StopEvent(Time arrTime, NamedLocation location) {
        super("stop");
        times = new ArrayList<>();
        times.add(arrTime);
        this.location = location;
    }
    public StopEvent(NamedLocation location, Time depTime) {
        super("stop");
        times = new ArrayList<>();
        times.add(depTime);
        this.location = location;
    }
    private StopEvent(ArrayList<Time> times, NamedLocation location) {
        super("stop");
        this.times = times;
        this.location = location;
    }
    
    @Override
    public String toString() {
        String output = "";
        for(Time time : times) {
            output += time.toString() + ";";
        }
        return output + location.toString();
    }

    @Override
    public void incrementTime(int minutes) {
        for(Time time : times) {
            time.addMinutes(minutes);
        }
    }

    @Override
    public StopEvent newInstance(Event event) {
        if(event instanceof StopEvent stopevent) {
            ArrayList<Time> tempTimes = new ArrayList<>();
            for(Time time : times) {
                tempTimes.add(new Time(time));
            }
            return new StopEvent(tempTimes, stopevent.location);
        } else {
            return null;
        }
    }

	@Override
	public void validateEvent() throws EventInvalidException {
        if(times.isEmpty() || location == null) {
            throw new EventInvalidException(this.toString());
        }
	}

    public List<Time> getTimes() {
        return this.times;
    }

    public NamedLocation getLoc() {
        return this.location;
    }
}
