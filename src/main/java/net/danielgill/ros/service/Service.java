package net.danielgill.ros.service;

import java.util.ArrayList;
import net.danielgill.ros.service.event.Event;
import net.danielgill.ros.service.event.EventInvalidException;
import net.danielgill.ros.service.event.ReferenceEvent;
import net.danielgill.ros.service.event.TimedEvent;
import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.template.Template;
import net.danielgill.ros.service.time.Time;

public class Service {
    private Reference ref;
    private String description;
    
    private int power = -1;
    private int mass = -1;
    private int maxSpeed = -1;
    private int maxBrake = -1;
    private int startSpeed = -1;
    
    private ArrayList<Event> events;

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
    
    public Service(Reference ref, String description) {
        this.ref = ref;
        this.description = description;
        events = new ArrayList<>();
    }
    
    @Deprecated(since = "v1.2.0-beta")
    public void setDetails(Reference ref, String description) {
        this.ref = ref;
        this.description = description;
        events = new ArrayList<>();
    }
    
    public void setData(int startSpeed, int maxSpeed, int mass, int maxBrake, int power) {
        this.startSpeed = startSpeed;
        this.maxSpeed = maxSpeed;
        this.mass = mass;
        this.maxBrake = maxBrake;
        this.power = power;
    }
    
    public void addEvent(Event event) {
        try {
            this.events.add(event);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
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

    public Reference getRef() {
        return ref;
    }

    public String getDescription() {
        return description;
    }

    public int getPower() {
        return power;
    }

    public int getMass() {
        return mass;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxBrake() {
        return maxBrake;
    }

    public int getStartSpeed() {
        return startSpeed;
    }
    
    public Event getEventFromIndex(int index) {
        return events.get(index);
    }
}
