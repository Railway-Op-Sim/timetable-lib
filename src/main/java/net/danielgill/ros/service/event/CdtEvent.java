package net.danielgill.ros.service.event;

import net.danielgill.ros.service.time.Time;

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
        if(event instanceof CdtEvent) {
            CdtEvent cdtevent = (CdtEvent) event;
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
}
