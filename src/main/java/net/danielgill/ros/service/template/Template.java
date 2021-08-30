package net.danielgill.ros.service.template;

import java.util.ArrayList;
import net.danielgill.ros.service.event.Event;
import net.danielgill.ros.service.time.Time;

public class Template {
    private final String description;
    
    private ArrayList<Event> events;
    
    public Template(String description) {
        this.description = description;
        
        events = new ArrayList<>();
    }
    
    public Template(String description, Time defaultTime) {
        this.description = description;
        
        events = new ArrayList<>();
    }
    
    public void addEvent(Event event) {
        this.events.add(event);
    }
    
    public Event getEventByIndex(int index) {
        return this.events.get(index);
    }
    public int getEventCount() {
        return events.size();
    }
    public String getDescription() {
        return description;
    }
}
