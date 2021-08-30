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
        this.events.add(event);
    }
    
    public void addTemplate(Template template, Time startTime) {
        for(int i = 0; i < template.getEventCount(); i++) {
            Event tempEvent = template.getEventByIndex(i);
            System.out.println("Event " + tempEvent.toString() + " found.");
            if(tempEvent instanceof TimedEvent) {
                ((TimedEvent) tempEvent).incrementTime(startTime.getMinutes());
            }
            this.events.add(tempEvent);
        }
    }
    
    @Override
    public String toString() {
        String output = "";
        
        if(events.isEmpty()) {
            System.err.println("No events exist in service " + getRef());
        } else {
            if(events.get(0).getType().equals("Sns")) {
                output += ref + ";" + description + "\n";
            } else {
                output += ref + ";" + description + ";" + startSpeed + ";" + maxSpeed + ";" + mass + ";" + maxBrake + ";" + maxSpeed + "\n";
            }
        
            for(int i = 0; i < events.size(); i++) {
                output += events.get(i).toString() + "\n";
            }
        }
        
        return output;
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
