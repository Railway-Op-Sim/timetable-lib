package net.danielgill.ros.service.event;

public abstract class ReferenceEvent extends TimedEvent {
    public ReferenceEvent(String type) {
        super(type);
    }
    
    public abstract void incrementRef(int increment);
}
