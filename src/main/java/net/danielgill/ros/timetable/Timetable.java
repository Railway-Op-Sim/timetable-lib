package net.danielgill.ros.timetable;

import java.util.ArrayList;
import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.service.ServiceInvalidException;
import net.danielgill.ros.timetable.time.Time;

public class Timetable {
    private ArrayList<Service> services;
    private final Time startTime;
    
    public Timetable(Time startTime) {
        services = new ArrayList<>();
        this.startTime = startTime;
    }
    
    public void addService(Service service) {
        services.add(service);
    }
    
    public String getTextTimetable() throws ServiceInvalidException {
        String output = "";
        output += startTime.toString();
        for(Service service : services) {
            output += "\u0000" + service.toTimetableString();
        }
        output += "\u0000";
        return output;
    }
}
