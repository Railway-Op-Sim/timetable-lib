package net.danielgill.ros.timetable.template;

import java.util.ArrayList;

import net.danielgill.ros.timetable.event.*;

/**
 * A template stores a list of events that can be added to a service.
 * @author Daniel Gill
 */
public class Template {
    private final String description;
    private ArrayList<Event> events;

    /**
     * Creates a new Template with a blank events ArrayList and a description.
     * @param description The description for the template, not related to service descriptions.
     */
    public Template(String description) {
        this.description = description;
        
        events = new ArrayList<>();
    }

    /**
     * Creates a new Template from another using a deep-copy method in each event.
     * @param template The template to be copied.
     */
    public Template(Template template) {
        this.description = template.description;
        ArrayList<Event> tempEvents = new ArrayList<>();
        for(int i = 0; i < template.getEventCount(); i++) {
            tempEvents.add(template.getEventByIndex(i).newInstance(template.getEventByIndex(i)));
        }
        this.events = tempEvents;
    }

    /**
     * Adds an event to the template.
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Returns an event in the template from a given index.
     * @param index The index of the event in the template.
     * @return The event instance.
     */
    public Event getEventByIndex(int index) {
        return this.events.get(index);
    }

    /**
     * Returns the number of events in the template.
     * @return An integer representing the number of events in the template.
     */
    public int getEventCount() {
        return events.size();
    }

    /**
     * Returns the ArrayList containing the events of the template.
     * @return The ArrayList containing the events from the template.
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Returns the description of the template.
     * @return A String representing the template description.
     */
    public String getDescription() {
        return description;
    }
}
