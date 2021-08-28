package net.danielgill.ros.service.event;

public abstract class Event {
    private String type;
    
    public Event(String type) {
        this.type = type;
    }
    
    
    @Override
    public abstract String toString();

    public String getType() {
        return type;
    }
}
