package net.danielgill.ros.service;

import java.util.ArrayList;
import net.danielgill.ros.service.event.Event;
import net.danielgill.ros.service.event.EventInvalidException;
import net.danielgill.ros.service.event.ReferenceEvent;
import net.danielgill.ros.service.event.TimedEvent;
import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.template.Template;
import net.danielgill.ros.service.time.Time;

/**
 * Represents a single service within a timetable.
 * @author Daniel Gill
 */
public class Service {
    private Reference ref;
    private String description;
    
    private int power = -1;
    private int mass = -1;
    private int maxSpeed = -1;
    private int maxBrake = -1;
    private int startSpeed = -1;
    
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
        this.power = power;
        this.mass = mass;
        this.maxSpeed = maxSpeed;
        this.maxBrake = maxBrake;
        this.startSpeed = startSpeed;
        
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
     * Sets the reference and description for a given service and creates new ArrayList.
     * @deprecated This method should not be used, create a new instance of Service instead.
     */
    @Deprecated(since = "v1.2.0-alpha")
    public void setDetails(Reference ref, String description) {
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
        this.startSpeed = startSpeed;
        this.maxSpeed = maxSpeed;
        this.mass = mass;
        this.maxBrake = maxBrake;
        this.power = power;
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
     * @throws ServiceInvalidException Throws this if the service is invalid, should be caught and not added to a .ttb file.
     */
    public String toTimetableString() throws ServiceInvalidException {
        validateService();

        String output = "";
        if(events.get(0).getType().equals("Sns")) {
            output += ref + ";" + description + ",";
        } else {
            output += ref + ";" + description + ";" + startSpeed + ";" + maxSpeed + ";" + mass + ";" + maxBrake + ";" + power + ",";
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
     * @throws ServiceInvalidException Throws this if the service is invalid, should be caught.
     */
    public String toFormattedString() throws ServiceInvalidException {
        validateService();
        
        String output = "";
        if(events.get(0).getType().equals("Sns")) {
            output += ref + ";" + description + "\n";
        } else {
            output += ref + ";" + description + ";" + startSpeed + ";" + maxSpeed + ";" + mass + ";" + maxBrake + ";" + power + "\n";
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
        
        for(Event event : events) {
            try {
                event.validateEvent();
            } catch (EventInvalidException e) {
                throw new ServiceInvalidException("Error in event " + e.getEventString(), ref);
            }
        }
        
        Event startEvent = getEventFromIndex(0);
        if(startEvent.getType().equals("Sns")) {
            
        } else {
            if(power <= 0 || mass <= 0 || maxSpeed <= 0 || maxBrake <= 0 || startSpeed == -1) {
                throw new ServiceInvalidException("One or more data values for service are missing or incorrect.", ref);
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
     * @return The power of the service.
     */
    public int getPower() {
        return power;
    }

    /**
     * @return The mass of the service.
     */
    public int getMass() {
        return mass;
    }

    /**
     * @return The max speed of the service.
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @return The max braking of the service.
     */
    public int getMaxBrake() {
        return maxBrake;
    }

    /**
     * @return The start speed of the service.
     */
    public int getStartSpeed() {
        return startSpeed;
    }

    /**
     * Returns an Event from the service's event array list from a given index.
     * @param index The index of the event array list.
     * @return The event located in the service's list of events.
     */
    public Event getEventFromIndex(int index) {
        return events.get(index);
    }
}
