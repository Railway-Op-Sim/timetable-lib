package net.danielgill.ros.timetable;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.event.Event;
import net.danielgill.ros.timetable.event.ReferenceEvent;
import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.service.ServiceInvalidException;

public class TimetableChecker {
    public Timetable ttb;

    public TimetableChecker(Timetable ttb) {
        this.ttb = ttb;
    }

    public void checkTimetable() throws TimetableInvalidException {
        if(ttb.getServices() == null || ttb.getServices().size() == 0 || ttb.getServices().isEmpty()) {
            throw new TimetableInvalidException("Timetable does not have any services.");
        }
        if(ttb.getStartTime() == null) {
            throw new TimetableInvalidException("Timetable does not have a start time.");
        }

        checkServices();
    }

    private void checkServices() throws TimetableInvalidException {
        List<String> fromList = new ArrayList<>();
        List<String> toList = new ArrayList<>();

        List<Service> services = ttb.getServices();
        for(Service s : services) {
            try {
                s.validateService();
            } catch (ServiceInvalidException e) {
                throw new TimetableInvalidException("Error in service: " + e.toString());
            }

            fromList.add(getReferenceFromEvent(s.getEventFromIndex(0)));
            toList.add(getReferenceFromEvent(s.getEventFromIndex(s.getEvents().size() - 1)));
        }
    }

    private String getReferenceFromEvent(Event e) {
        if(e instanceof ReferenceEvent) {
            ReferenceEvent ref = (ReferenceEvent) e;
            return ref.getRef().toString();
        } else {
            return null;
        }
    }
}
