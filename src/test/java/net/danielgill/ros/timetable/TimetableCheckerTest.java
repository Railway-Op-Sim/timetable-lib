package net.danielgill.ros.timetable;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import net.danielgill.ros.timetable.data.DataTemplate;
import net.danielgill.ros.timetable.data.DataTemplates;
import net.danielgill.ros.timetable.event.FrhEvent;
import net.danielgill.ros.timetable.event.SntEvent;
import net.danielgill.ros.timetable.event.StopEvent;
import net.danielgill.ros.timetable.location.NamedLocation;
import net.danielgill.ros.timetable.location.StartLocation;
import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.service.ServiceInvalidException;
import net.danielgill.ros.timetable.time.Time;


public class TimetableCheckerTest {

    @Test
    public void testTimetable() {
        Timetable ttb = new Timetable(null);
        TimetableInvalidException t = assertThrows(TimetableInvalidException.class, () -> {ttb.validate();});
        System.out.println(t.getMessage());

        ttb.addService(new Service(new Reference("1A01"), ""));
        t = assertThrows(TimetableInvalidException.class, () -> {ttb.validate();});
        System.out.println(t.getMessage());
    }

    @Test
    public void testServiceBasic() {
        Timetable ttb2 = new Timetable(new Time("00:00"));
        ttb2.addService(new Service(new Reference("1A01"), ""));
        TimetableInvalidException t = assertThrows(TimetableInvalidException.class, () -> {ttb2.validate();});
        System.out.println(t.getMessage());

        Timetable ttb3 = new Timetable(new Time("00:00"));
        ttb3.addService(new Service(new Reference(""), "Test Service"));
        t = assertThrows(TimetableInvalidException.class, () -> {ttb3.validate();});
        System.out.println(t.getMessage());

        Timetable ttb4 = new Timetable(new Time("00:00"));
        ttb4.addService(new Service(new Reference("1A01"), "Test Service"));
        t = assertThrows(TimetableInvalidException.class, () -> {ttb4.validate();});
        System.out.println(t.getMessage());
    }

    @Test
    public void testServiceEvents() throws ServiceInvalidException {
        Timetable ttb1 = new Timetable(new Time("12:00"));
        Service s = new Service(new Reference("1A01"), "Test Service");
        ttb1.addService(s);
        TimetableInvalidException t = assertThrows(TimetableInvalidException.class, () -> {ttb1.validate();});
        System.out.println(t.getMessage());

        Timetable ttb2 = new Timetable(new Time("12:00"));
        s = new Service(new Reference("1A01"), "Test Service");
        s.addEvent(new SntEvent(new Time("12:00"), new StartLocation("2-3 2-2")));
        ttb2.addService(s);
        t = assertThrows(TimetableInvalidException.class, () -> {ttb2.validate();});
        System.out.println(t.getMessage());

        Timetable ttb3 = new Timetable(new Time("12:00"));
        s = new Service(new Reference("1A01"), "Test Service");
        s.addEvent(new SntEvent(new Time("12:00"), new StartLocation("2-3 2-2")));
        s.addEvent(new FrhEvent());
        ttb3.addService(s);
        t = assertThrows(TimetableInvalidException.class, () -> {ttb3.validate();});
        System.out.println(t.getMessage());

        Timetable ttb4 = new Timetable(new Time("12:00"));
        s = new Service(new Reference("1A01"), "Test Service");
        s.addEvent(new SntEvent(new Time("11:00"), new StartLocation("2-2 2-3")));
        s.addEvent(new FrhEvent());
        s.addDataTemplate(DataTemplates.C143_0_2, 50);
        ttb4.addService(s);
        t = assertThrows(TimetableInvalidException.class, () -> {ttb4.validate();});
        System.out.println(t.getMessage());
    }
}
