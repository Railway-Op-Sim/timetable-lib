package net.danielgill.ros.service.template;

import java.util.ArrayList;
import net.danielgill.ros.service.event.*;

public class Template {
    private final String description;
    private ArrayList<Event> events;
    
    public Template(String description) {
        this.description = description;
        
        events = new ArrayList<>();
    }
    
    public Template(Template template) {
        this.description = template.description;
        ArrayList<Event> tempEvents = new ArrayList<>();
        for(int i = 0; i < template.getEventCount(); i++) {
            tempEvents.add(template.getEventByIndex(i).newInstance(template.getEventByIndex(i)));
        }
        this.events = tempEvents;
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
    public ArrayList<Event> getEvents() {
        return events;
    }
    public String getDescription() {
        return description;
    }
}
