package net.danielgill.ros.timetable.event;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.location.NamedLocation;
import net.danielgill.ros.timetable.time.Time;

public class StopEvent extends Event implements TimedEvent {
    private ArrayList<Time> times;
    private NamedLocation location;
    private boolean arrival;
    private boolean departure;
    
    public StopEvent(Time arrTime, Time depTime, NamedLocation location) {
        super("stop");
        times = new ArrayList<>();
        times.add(arrTime);
        times.add(depTime);
        this.location = location;
        arrival = true;
        departure = true;
    }
    public StopEvent(Time arrTime, NamedLocation location) {
        super("stop");
        times = new ArrayList<>();
        times.add(arrTime);
        this.location = location;
        arrival = true;
        departure = false;
    }
    public StopEvent(NamedLocation location, Time depTime) {
        super("stop");
        times = new ArrayList<>();
        times.add(depTime);
        this.location = location;
        arrival = false;
        departure = true;
    }
    private StopEvent(ArrayList<Time> times, NamedLocation location) {
        super("stop");
        this.times = times;
        this.location = location;
        arrival = false;
        departure = true;
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

    @Override
    public Time getTime() {
        throw new UnsupportedOperationException("Unsupported method for StopEvent.");
    }

    @Override
    public String getDescription() {
        return "Stop at Station";
    }

    @Override
    public String getContextualDescription() {
        if(arrival && departure) {
            return ("Stops at " + location.toString() + " from " + times.get(0).toString() + " to " + times.get(1));
        } else if(arrival) {
            return ("Arrives at " + location.toString() + " at time " + times.get(0).toString());
        } else if(departure) {
            return ("Departs from " + location.toString() + " at time " + times.get(0).toString());
        } else {
            return ("Stops at " + location.toString() + " from " + times.get(0).toString() + " to " + times.get(1));
        }
    }
}
