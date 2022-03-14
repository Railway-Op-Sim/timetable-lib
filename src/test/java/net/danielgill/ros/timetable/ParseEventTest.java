package net.danielgill.ros.timetable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.danielgill.ros.timetable.event.CdtEvent;
import net.danielgill.ros.timetable.event.FrhEvent;
import net.danielgill.ros.timetable.event.SfsEvent;
import net.danielgill.ros.timetable.event.SnsEvent;
import net.danielgill.ros.timetable.event.StopEvent;
import net.danielgill.ros.timetable.location.NamedLocation;
import net.danielgill.ros.timetable.parse.ParseEvent;
import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

public class ParseEventTest {
    @Test
    void testParseEvent() {
        Assertions.assertEquals(new SnsEvent(new Time("00:00"), new Reference("1D51")).toString(), ParseEvent.getEventFromString("00:00;Sns;1D51").toString());
        Assertions.assertEquals(new StopEvent(new NamedLocation("Test"), new Time("12:00")).toString(), ParseEvent.getEventFromString("12:00;Test").toString());
        Assertions.assertEquals(new SfsEvent(new Time("01:00"), new Reference("1D51")).toString(), ParseEvent.getEventFromString("01:00;Sfs;1D51").toString());
        Assertions.assertEquals(new CdtEvent(new Time("12:00")).toString(), ParseEvent.getEventFromString("12:00;cdt").toString());
        Assertions.assertEquals(new FrhEvent().toString(), ParseEvent.getEventFromString("Frh").toString());
    }
}
