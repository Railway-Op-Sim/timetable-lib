package net.danielgill.ros.timetable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.danielgill.ros.timetable.service.Service;
import net.danielgill.ros.timetable.time.Time;

public class TimetableCheckerTest {

    @Test
    public void testStartTime() {
        Timetable s = new Timetable(null);
        TimetableInvalidException t = assertThrows(TimetableInvalidException.class, () -> {s.validate();});
    }
}
