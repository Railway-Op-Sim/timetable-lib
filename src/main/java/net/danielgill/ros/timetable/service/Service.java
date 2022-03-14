package net.danielgill.ros.timetable.service;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.data.Data;
import net.danielgill.ros.timetable.data.DataTemplate;
import net.danielgill.ros.timetable.event.Event;
import net.danielgill.ros.timetable.event.EventInvalidException;
import net.danielgill.ros.timetable.event.ReferenceEvent;
import net.danielgill.ros.timetable.event.TimedEvent;
import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.template.Template;
import net.danielgill.ros.timetable.time.Time;

/**
 * Represents a single service within a timetable.
 * @author Daniel Gill
 */
public class Service {
    private Reference ref;
    private String description;
    private Data data;
    
    private ArrayList<Event> events;

    /**
     * Creates a service from a reference, description and the service data.
     * @param ref The reference for a given service.
     * @param description The description for a given service.
     * @param startSpeed The service's start speed.
     * @param maxSpeed The service's max speed.
     * @param mass The service's mass.
     * @param maxBrake The service's max braking force.
     * @param power The service's power.
     */
    public Service(Reference ref, String description, int startSpeed, int maxSpeed, int mass, int maxBrake, int power) {
        this.ref = ref;
        this.description = description;
        this.data = new Data(startSpeed, maxSpeed, mass, maxBrake, power);
        events = new ArrayList<>();
    }
    
    /**
     * Creates a service from a reference, description and the service data.
     * @param ref The reference for a given service.
     * @param description The description for a given service.
     * @param data The service data.
     */
    public Service(Reference ref, String description, Data data) {
        this.ref = ref;
        this.description = description;
        this.data = data;
        events = new ArrayList<>();
    }
    
    /**
     * Creates a service from a reference, description, the service data and start speed.
     * @param ref The reference for a given service.
     * @param description The description for a given service.
     * @param data The service data.
     * @param startSpeed The start speed of the service.
     */
    public Service(Reference ref, String description, Data data, int startSpeed) {
        this.ref = ref;
        this.description = description;
        this.data = new Data(startSpeed, data);
        events = new ArrayList<>();
    }

    /**
     * Creates a service from a reference and description.
     * @param ref The reference for a given service.
     * @param description The description for a given service.
     */
    public Service(Reference ref, String description) {
        this.ref = ref;
        this.description = description;
        events = new ArrayList<>();
    }

    /**
     * Sets the data for a given service.
     * @param startSpeed The service's start speed.
     * @param maxSpeed The service's max speed.
     * @param mass The service's mass.
     * @param maxBrake The service's max braking force.
     * @param power The service's power.
     */
    public void setData(int startSpeed, int maxSpeed, int mass, int maxBrake, int power) {
        this.data = new Data(startSpeed, maxSpeed, mass, maxBrake, power);
    }
    
    /**
     * Sets the data for a given service.
     * @param data The new data for a given service.
     */
    public void setData(Data data) {
        this.data = data;
    }
    
    /**
     * Adds a data template and starting speed to a service.
     * @param dt The data template to be added.
     * @param startSpeed The start speed of the service.
     */
    public void addDataTemplate(DataTemplate dt, int startSpeed) {
        this.data = new Data(startSpeed, dt.getData());
    }

    /**
     * Adds an event at the end of the current list of events.
     * @param event The event object to be added.
     * @see Event
     */
    public void addEvent(Event event) {
        try {
            this.events.add(event);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Adds a template's events to the service events, with times determined by the start time.
     * @param template The template object to be added to the events.
     * @param startTime The time which will be added to the instance of the template.
     * @see Template
     * @see TimedEvent
     */
    public void addTemplate(Template template, Time startTime) {
        Template temptemplate = new Template(template);
        for(int i = 0; i < temptemplate.getEventCount(); i++) {
            Event tempEvent = temptemplate.getEventByIndex(i);
            if(tempEvent instanceof TimedEvent) {
                ((TimedEvent) tempEvent).incrementTime(startTime.getMinutes());
            }
            this.events.add(tempEvent);
        }
    }

    /**
     * Adds a template's events to the service events, with times and references determined by start time and increment.
     * @param template The template object to be added to the events.
     * @param startTime The time which will be added to the instance of the template.
     * @param increment The increment which will be added to the instance of all ReferenceEvents.
     * @see Template
     * @see TimedEvent
     * @see ReferenceEvent
     */
    public void addTemplate(Template template, Time startTime, int increment) {
        Template temptemplate = new Template(template);
        for(int i = 0; i < temptemplate.getEventCount(); i++) {
            Event tempEvent = temptemplate.getEventByIndex(i);
            if(tempEvent instanceof TimedEvent) {
                ((TimedEvent) tempEvent).incrementTime(startTime.getMinutes());
            }
            if(tempEvent instanceof ReferenceEvent) {
                ((ReferenceEvent) tempEvent).incrementRef(increment);
            }
            this.events.add(tempEvent);
        }
    }


    /**
     * Provides a string output of the service for a .ttb file format.
     * @return A String containing the service ready to be inserted into a .ttb file.
     * @throws ServiceInvalidException Throws this if a service is invalid, should be caught and not added to a .ttb file.
     */
    public String toTimetableString() throws ServiceInvalidException {
        validateService();

        String output = "";
        if(events.get(0).getType().equals("Sns") || events.get(0).getType().equals("Sfs")) {
            output += ref + ";" + description + ",";
        } else {
            output += ref + ";" + description + ";" + data.toString() + ",";
        }

        int current = 0;
        for(int i = 0; i < events.size() - 1; i++) {
            output += events.get(i).toString() + ",";
            current = i + 1;
        }
        output += events.get(current).toString();
        
        return output;
    }

    /**
     * Provides a string output of the service in a readable format.
     * @return A string containing the service in a readable format.
     * @throws ServiceInvalidException Throws this if a service is invalid, should be caught.
     */
    public String toFormattedString() throws ServiceInvalidException {
        validateService();
        
        String output = "";
        if(events.get(0).getType().equals("Sns") || events.get(0).getType().equals("Sfs")) {
            output += ref + ";" + description + "\n";
        } else {
            output += ref + ";" + description + ";" + data.toString() + "\n";
        }

        int current = 0;
        for(int i = 0; i < events.size() - 1; i++) {
            output += events.get(i).toString() + "\n";
            current = i + 1;
        }
        output += events.get(current).toString();
        
        return output;
    }

    /**
     * Validates the service, and throws a ServiceInvalidException if it is invalid.
     * @throws ServiceInvalidException Throws this if the service is invalid.
     */
    public void validateService() throws ServiceInvalidException {
        if(ref.getRef().isBlank()) {
            throw new ServiceInvalidException("Reference does not exist for a service.");
        }
        if(description.isBlank()) {
            throw new ServiceInvalidException("Description is blank for service.", ref);
        }
        if(events.isEmpty()) {
            throw new ServiceInvalidException("No events exist for service.", ref);
        }
        if(events.size() <= 1) {
            throw new ServiceInvalidException("Too few events for service, minimum 2.", ref);
        }
        
        for(Event event : events) {
            try {
                event.validateEvent();
            } catch (EventInvalidException e) {
                throw new ServiceInvalidException("Error in event " + e.getEventString(), ref);
            }
        }
        
        Event startEvent = getEventFromIndex(0);

        if(startEvent.getType().equals("Sns") || startEvent.getType().equals("Sfs")) {
            
        } else if(startEvent.getType().equals("Snt")) {
            if (data == null) {
                throw new ServiceInvalidException("Missing data values for service.", ref);
            } else {
                data.validate(ref);
            }
        } else {
            throw new ServiceInvalidException("Missing a valid start event for service.", ref);
        }

        Event endEvent = getEventFromIndex(getEvents().size() - 1);
        if(endEvent.getType().equals("Frh") || endEvent.getType().equals("Fer") || endEvent.getType().equals("Fns") || endEvent.getType().equals("Fsp")) {

        } else {
            throw new ServiceInvalidException("Missing a valid end event for service.", ref);
        }

        validateEvents();
    }

    private void validateEvents() throws ServiceInvalidException {
        TimedEvent first = (TimedEvent) getEventFromIndex(0);
        Time current = new Time(first.getTime());

        for(Event e : events) {
            if(e instanceof TimedEvent te) {
                if(te.getTime().earlierThan(current)) {
                    throw new ServiceInvalidException("Event time is before previous event.", ref);
                } else {
                    current = te.getTime();
                }
            }
        }
    }

    /**
     * Returns a reference for the service.
     * @return The reference for the service.
     */
    public Reference getRef() {
        return ref;
    }

    /**
     * Returns the service description.
     * @return The description for the service.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns an Event from the service's event array list from a given index.
     * @param index The index of the event array list.
     * @return The event located in the service's list of events.
     */
    public Event getEventFromIndex(int index) {
        return events.get(index);
    }

    /**
     * Returns an List containing all the events for a service.
     * @return List containing all events for a service.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets the event at the given index.
     * @param index The Integer index to be set.
     * @param evt The new event.
     */
    public void setEventAtIndex(int index, Event evt) {
        events.set(index, evt);
    }

    /**
     * Returns the first event in a service that matches a given type.
     * @param type The type of the service as a string.
     * @return The first event.
     */
    public Event getFirstEventByType(String type) {
        for(Event e : events) {
            if(e.getType().equalsIgnoreCase(type)) {
                return e;
            }
        }
        return null;
    }
}
