package net.danielgill.ros.service;

import java.util.ArrayList;
import net.danielgill.ros.service.event.Event;
import net.danielgill.ros.service.event.TimedEvent;
import net.danielgill.ros.service.template.Template;
import net.danielgill.ros.service.time.Time;

public class Service {
    private String ref;
    private String description;
    
    private int power = -1;
    private int mass = -1;
    private int maxSpeed = -1;
    private int maxBrake = -1;
    private int startSpeed = -1;
    
    private ArrayList<Event> events;

    public Service(String ref, String description, int startSpeed, int maxSpeed, int mass, int maxBrake, int power) {
        this.ref = ref;
        this.description = description;
        this.power = power;
        this.mass = mass;
        this.maxSpeed = maxSpeed;
        this.maxBrake = maxBrake;
        this.startSpeed = startSpeed;
        
        events = new ArrayList<>();
    }
    
    public Service(String ref, String description) {
        this.ref = ref;
        this.description = description;
        events = new ArrayList<>();
    }
    
    public void setDetails(String ref, String description) {
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
    
    @Override
    public String toString() {
        //if(!isValid()) {
        //    return "Service " + ref + " is not valid and therefore cannot be printed.";
        //}
        
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
    
    public String toFormattedString() {
        //if(!isValid()) {
        //    return "Service " + ref + " is not valid and therefore cannot be processed.";
        //}
        
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
    
    public boolean isValid() {
        String output = "[VALIDATION ERROR] ";
        
        if(ref.isBlank()) {
            System.out.println(output + "Reference does not exist for a service.");
            return false;
        }
        if(description.isBlank()) {
            System.out.println(output + "Description is not valid for service " + ref + ".");
            return false;
        }
        if(events.isEmpty()) {
            System.out.println(output + "No events exist for service " + ref + ".");
            return false;
        }
        
        //TODO: Check each event with its own isValid() method.
        
        //TODO: Use new method to sort list of events.
        
        Event startEvent = getEventFromIndex(0);
        if(startEvent.getType().equals("Sns")) {
            
        } else {
            if(power <= 0 || mass <= 0 || maxSpeed <= 0 || maxBrake <= 0 || startSpeed == -1) {
                System.out.println(output + "Data values for service " + ref + " are missing or incorrect.");
                return false;
            }
        }
        
        return true;
    }

    public String getRef() {
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
    
    public ArrayList<Event> getNonStartFinishEvents() {
        ArrayList<Event> tempEvents = events;
        tempEvents.remove(0);
        tempEvents.remove(tempEvents.size() - 1);
        return tempEvents;
    }
    
}
