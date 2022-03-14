package net.danielgill.ros.timetable;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.event.Event;
import net.danielgill.ros.timetable.event.ReferenceEvent;
import net.danielgill.ros.timetable.event.TimedEvent;
import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.service.ServiceInvalidException;
import net.danielgill.ros.timetable.time.Time;

/**
 * A class to check the validity of a timetable.
 * @author Daniel Gill
 */
public class TimetableChecker {
    /**
     * Checks whether a given timetable is not valid, throws a TimetableInvalidException if not.
     * @param ttb The timetable object to be checked.
     * @throws TimetableInvalidException Thrown if the timetable is invalid.
     */
    public static void checkTimetable(Timetable ttb) throws TimetableInvalidException {
        if(ttb.getServices() == null || ttb.getServices().size() == 0 || ttb.getServices().isEmpty()) {
            throw new TimetableInvalidException("Timetable does not have any services.");
        }
        if(ttb.getStartTime() == null) {
            throw new TimetableInvalidException("Timetable does not have a start time.");
        }

        checkServices(ttb);
    }

    private static void checkServices(Timetable ttb) throws TimetableInvalidException {
        List<String> fromList = new ArrayList<>();
        List<String> toList = new ArrayList<>();

        List<Service> services = ttb.getServices();
        for(Service s : services) {
            try {
                s.validateService();
            } catch (ServiceInvalidException e) {
                throw new TimetableInvalidException("Error in service " + e.toString());
            }

            String ref = s.getRef().toString();

            String fromRef = getReferenceFromEvent(s.getEventFromIndex(0));
            if(fromRef != null) {
                fromList.add(fromRef);
            } 
            String toRef = getReferenceFromEvent(s.getEventFromIndex(s.getEvents().size() - 1));
            if(toRef != null) {
                toList.add(toRef);
            }

            Time startTime = ttb.getStartTime();
            Event e = s.getEventFromIndex(0);
            if(e instanceof TimedEvent) {
                TimedEvent te = (TimedEvent) e;
                if(te.getTime().earlierThan(startTime)) {
                    throw new TimetableInvalidException("Error in service [" + ref + "]: Service starts before timetable start time.");
                }
            }
        }
        for(Service s : services) {
            String ref = s.getRef().toString();

            String fromRef = getReferenceFromEvent(s.getEventFromIndex(0));
            if(!toList.contains(ref) && fromRef != null) {
                throw new TimetableInvalidException("Error in service [" + ref + "]: No service " + fromRef + " exists to form from.");
            }

            String toRef = getReferenceFromEvent(s.getEventFromIndex(s.getEvents().size() - 1));
            if(!fromList.contains(ref) && toRef != null) {
                throw new TimetableInvalidException("Error in service [" + ref + "]: No service " + toRef + " exists to form to.");
            }
        }
    }

    private static String getReferenceFromEvent(Event e) {
        if(e instanceof ReferenceEvent) {
            ReferenceEvent ref = (ReferenceEvent) e;
            return ref.getRef().toString();
        } else {
            return null;
        }
    }
}
