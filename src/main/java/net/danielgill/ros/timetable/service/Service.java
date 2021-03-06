package net.danielgill.ros.timetable.service;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.data.Data;
import net.danielgill.ros.timetable.data.DataTemplate;
import net.danielgill.ros.timetable.event.Event;
import net.danielgill.ros.timetable.event.EventInvalidException;
import net.danielgill.ros.timetable.event.ReferenceEvent;
import net.danielgill.ros.timetable.event.TimedEvent;
import net.danielgill.ros.timetable.parse.ParseEvent;
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
    private Repeat repeat;
    
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
        repeat = new Repeat(0, 0, 0);
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
        repeat = new Repeat(0, 0, 0);
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
        repeat = new Repeat(0, 0, 0);
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
        repeat = new Repeat(0, 0, 0);
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
     * Gets the data object accociated with the service.
     * @return Data object.
     */
    public Data getData() {
        return this.data;
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
     * Adds an event to the end of the current list of events.
     * @param event A string version of the event to be added to the service.
     * @see ParseEvent
     */
    public void addEvent(String event) {
        this.events.add(ParseEvent.parseEvent(event));
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
     * Sets the repeat for a service.
     * @param r The repeat object to be set as the repeats.
     */
    public void setRepeat(Repeat r) {
        this.repeat = r;
    }

    /**
     * Sets the repeats for a service.
     * @param interval The time in minutes between repeats.
     * @param increment The increment of the reference for repeats.
     * @param number The number of repeats of a service after the original.
     */
    public void setRepeat(int interval, int increment, int number) {
        this.repeat = new Repeat(interval, increment, number);
    }

    /**
     * Sets the repeats for a service.
     * @param interval The time object representing the between repeats.
     * @param increment The increment of the reference for repeats.
     * @param number The number of repeats of a service after the original.
     */
    public void setRepeat(Time interval, int increment, int number) {
        this.repeat = new Repeat(interval, increment, number);
    }
    
    /**
     * Returns the repeat object associated with the service.
     * @return The repeat object.
     */
    public Repeat getRepeat() {
        return this.repeat;
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

        String repeatString = repeat.toString();
        if(!repeatString.isEmpty()) {
            output += "," + repeatString;
        }
        
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

        String repeatString = repeat.toString();
        if(!repeatString.isEmpty()) {
            output += "\n" + repeatString;
        }
        
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

        if(startEvent.getType().equals("Sns") || startEvent.getType().equals("Sfs") || startEvent.getType().equals("Sns-fsh") || startEvent.getType().equals("Sns-sh") || startEvent.getType().equals("Snt-sh")) {
            
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
        if(endEvent.getType().equals("Frh") || endEvent.getType().equals("Fer") || endEvent.getType().equals("Fns") || endEvent.getType().equals("Fsp") || endEvent.getType().equals("Fjo") || endEvent.getType().equals("F-nshs") || endEvent.getType().equals("Fns-sh") || endEvent.getType().equals("Frh-sh")) {

        } else {
            throw new ServiceInvalidException("Missing a valid end event for service.", ref);
        }

        validateEvents();
    }

    private void validateEvents() throws ServiceInvalidException {
        TimedEvent first = (TimedEvent) getEventFromIndex(0);
        Time current = new Time(first.getTime());

        for(Event e : events) {
            if(e instanceof TimedEvent te && (!e.getType().equals("stop") && !e.getType().equals("Sns-sh") && !e.getType().equals("Fns-sh"))) {
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
     * Sets the reference for the service.
     * @param ref A String to be set as the new reference.
     */
    public void setRef(String ref) {
        this.ref = new Reference(ref);
    }

    /**
     * Sets the reference for the service.
     * @param ref A reference object to be set as the new reference.
     */
    public void setRef(Reference ref) {
        this.ref = ref;
    }

    /**
     * Sets the description for the service.
     * @param description A String description of the service.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the start speed for the service.
     * @param startSpeed The new start speed for the service as an int.
     */
    public void setStartSpeed(int startSpeed) {
        this.data = new Data(startSpeed, this.data);
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
     * Sets the list of events in the service.
     * @param events An ArrayList containing Event objects.
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
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
