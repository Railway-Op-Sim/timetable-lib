package net.danielgill.ros.timetable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import net.danielgill.ros.timetable.event.StopEvent;
import net.danielgill.ros.timetable.parse.ParseTimetable;
import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.service.ServiceInvalidException;

public class ParseTimetableTest {
    @Test
    public void parseJSON2TTBTimetable() throws FileNotFoundException, ServiceInvalidException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("Croydon.ttb").getFile());
        Timetable t = ParseTimetable.parseTimetable(file);

        assertDoesNotThrow(() -> {t.validate();});
        assertEquals("06:00", t.getStartTime().toString());
        Service s = t.getServiceByRef("2O26");
        StopEvent e = (StopEvent) s.getFirstEventByType("stop");
        assertEquals("Selhurst", e.getLoc().toString());
    }
}
