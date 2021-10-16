package net.danielgill.ros.service.event;

public abstract class Event {
    private final String type;
    
    public Event(String type) {
        this.type = type;
    }
    
    @Override
    public abstract String toString();
    
    public abstract Event newInstance(Event event);

    public abstract void validateEvent() throws EventInvalidException;

    public String getType() {
        return type;
    }
}
