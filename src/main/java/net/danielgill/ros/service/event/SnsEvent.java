package net.danielgill.ros.service.event;

import net.danielgill.ros.service.time.Time;

public class SnsEvent extends TimedEvent {
    private Time time;
    private String ref;
    
    public SnsEvent(Time time, String ref) {
        super("Sns");
        this.time = time;
        this.ref = ref;
    }
    
    @Override
    public String toString() {
        return time.toString() + ";Sns;" + ref;
    }
    
    public Time getTime() {
        return time;
    }
    public String getRef() {
        return ref;
    }

    @Override
    public void incrementTime(int minutes) {
        this.time.addMinutes(minutes);
    }

    @Override
    public SnsEvent newInstance(Event event) {
        if(event instanceof SnsEvent) {
            SnsEvent snsEvent = (SnsEvent) event;
            return new SnsEvent(new Time(snsEvent.time), snsEvent.ref);
        } else {
            return null;
        }
    }
}
