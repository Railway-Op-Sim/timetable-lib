package net.danielgill.ros.service.event;

public abstract class TimedEvent extends Event {
    public TimedEvent(String type) {
        super(type);
    }
    
    public abstract void incrementTime(int minutes);
}
