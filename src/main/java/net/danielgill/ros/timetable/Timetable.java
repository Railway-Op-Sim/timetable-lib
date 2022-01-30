package net.danielgill.ros.timetable;

import java.util.ArrayList;
import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.service.ServiceInvalidException;
import net.danielgill.ros.timetable.time.Time;

/**
 * A class to store the timetable which can then be printed.
 * @author Daniel Gill
 */
public class Timetable {
    private ArrayList<Service> services;
    private final Time startTime;
    
    /**
     * Create a new timetable with the start time.
     * @param startTime The time at which the timetable starts.
     */
    public Timetable(Time startTime) {
        services = new ArrayList<>();
        this.startTime = startTime;
    }
    
    /**
     * Adds a service to the timetable.
     * @param service Adds a completed service to the timetable.
     */
    public void addService(Service service) {
        services.add(service);
    }
    
    /**
     * Returns the timetable as a string to be added to a file or printed.
     * @return The string timetable.
     * @throws ServiceInvalidException Thrown if a service is invalid in the timetable that may cause other issues.
     */
    public String getTextTimetable() throws ServiceInvalidException {
        StringBuilder output = new StringBuilder("");
        output.append(startTime.toString());
        for(Service service : services) {
            output.append("\u0000").append(service.toTimetableString());
        }
        output.append("\u0000");
        return output.toString();
    }

    /**
     * Returns an ArrayList containing references to all services in the timetable.
     * @return An ArrayList containing services in a timetable.
     */
    public ArrayList<Service> getServices() {
        return services;
    }

    /**
     * Returns a service in a timetable given a reference to it.
     * @param ref The String reference for the service.
     * @return A service with the given reference.
     */
    public Service getServiceByRef(String ref) {
        for(Service s : services) {
            if(s.getRef().toString().equalsIgnoreCase(ref)) {
                return s;
            }
        }
        return null;
    }
}
