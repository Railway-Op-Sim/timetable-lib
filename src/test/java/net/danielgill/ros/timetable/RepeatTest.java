package net.danielgill.ros.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.danielgill.ros.timetable.data.DataTemplates;
import net.danielgill.ros.timetable.parse.ParseEvent;
import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.service.Repeat;
import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.service.ServiceInvalidException;

public class RepeatTest {
    @Test
    void testRepeats() throws ServiceInvalidException {
        Service s = new Service(new Reference("1A01"), "TEST", DataTemplates.C139_1.getData(), 20);
        s.addEvent(ParseEvent.parseEvent("12:00;Snt;2-3 2-4"));
        s.addEvent(ParseEvent.parseEvent("12:10;12:11;Station"));
        s.addEvent(ParseEvent.parseEvent("12:15;Fer;2-3"));
        s.setRepeat(30, 2, 5);
        assertEquals("1A01;TEST;20;64;13;7;64,12:00;Snt;2-3 2-4,12:10;12:11;Station,12:15;Fer;2-3,R;30;2;5", s.toTimetableString());
        s.setRepeat(new Repeat(0, 0, 0));
        assertEquals("1A01;TEST;20;64;13;7;64,12:00;Snt;2-3 2-4,12:10;12:11;Station,12:15;Fer;2-3", s.toTimetableString());
    }
}
