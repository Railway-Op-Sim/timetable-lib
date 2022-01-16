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
        StringBuilder output = new StringBuilder("");
        output.append(startTime.toString());
        for(Service service : services) {
            output.append("\u0000").append(service.toTimetableString());
        }
        output.append("\u0000");
        return output.toString();
    }
}
