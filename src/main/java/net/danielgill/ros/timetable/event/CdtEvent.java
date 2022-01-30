package net.danielgill.ros.timetable.event;

import net.danielgill.ros.timetable.time.Time;

public class CdtEvent extends Event implements TimedEvent {
    private Time time;
    
    public CdtEvent(Time time) {
        super("cdt");
        this.time = time;
    }
    
    public String toString() {
        return time.toString() + ";cdt";
    }

    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public CdtEvent newInstance(Event event) {
        if(event instanceof CdtEvent cdtevent) {
            return new CdtEvent(new Time(cdtevent.time));
        } else {
            return null;
        }
    }

    @Override
    public void validateEvent() throws EventInvalidException {
        if(time == null) {
            throw new EventInvalidException(this.toString());
        }
    }

    public Time getTime() {
        return this.time;
    }
}
