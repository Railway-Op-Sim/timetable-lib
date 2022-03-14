package net.danielgill.ros.timetable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import net.danielgill.ros.timetable.data.DataTemplates;
import net.danielgill.ros.timetable.event.FerEvent;
import net.danielgill.ros.timetable.event.FnsEvent;
import net.danielgill.ros.timetable.event.FrhEvent;
import net.danielgill.ros.timetable.event.SnsEvent;
import net.danielgill.ros.timetable.event.SntEvent;
import net.danielgill.ros.timetable.location.Location;
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

        Timetable ttb5 = new Timetable(new Time("12:00"));
        s = new Service(new Reference("1A01"), "Test Service");
        s.addEvent(new SntEvent(new Time("11:00"), new StartLocation("2-2 2-3")));
        s.addEvent(new FerEvent(new Time("10:00"), new Location("4-3")));
        s.addDataTemplate(DataTemplates.C143_0_2, 50);
        ttb5.addService(s);
        t = assertThrows(TimetableInvalidException.class, () -> {ttb5.validate();});
        System.out.println(t.getMessage());

        Timetable ttb6 = new Timetable(new Time("10:00"));
        s = new Service(new Reference("1A01"), "Test Service");
        s.addEvent(new SnsEvent(new Time("10:00"), new Reference("2B22")));
        s.addEvent(new FerEvent(new Time("11:00"), new Location("4-3")));
        s.addDataTemplate(DataTemplates.C143_0_2, 50);
        ttb6.addService(s);
        t = assertThrows(TimetableInvalidException.class, () -> {ttb6.validate();});
        System.out.println(t.getMessage());

        Timetable ttb7 = new Timetable(new Time("10:00"));
        s = new Service(new Reference("1A01"), "Test Service");
        s.addEvent(new SntEvent(new Time("10:00"), new StartLocation("2-2 2-3")));
        s.addEvent(new FnsEvent(new Time("10:00"), new Reference("2V77")));
        s.addDataTemplate(DataTemplates.C143_0_2, 50);
        ttb7.addService(s);
        t = assertThrows(TimetableInvalidException.class, () -> {ttb7.validate();});
        System.out.println(t.getMessage());

        Timetable ttb8 = new Timetable(new Time("10:00"));
        s = new Service(new Reference("1A01"), "Test Service");
        s.addEvent(new SnsEvent(new Time("10:00"), new Reference("1A01")));
        s.addEvent(new FnsEvent(new Time("10:00"), new Reference("1A01")));
        s.addDataTemplate(DataTemplates.C143_0_2, 50);
        ttb8.addService(s);
        assertDoesNotThrow(() -> {ttb8.validate();});
    }
}
