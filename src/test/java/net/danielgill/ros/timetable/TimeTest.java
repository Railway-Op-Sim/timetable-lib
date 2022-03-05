package net.danielgill.ros.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.danielgill.ros.timetable.time.Time;

public class TimeTest {
    @Test
    void testTimeParse() {
        assertEquals("00:10", new Time("00:10").toString());
        assertEquals("00:10", new Time("24:10").toString());
        assertEquals("00:10", new Time(10).toString());
        assertEquals("01:25", new Time(25, 1).toString());
    }

    @Test
    void testTimeMathematics() {
        Time t1 = new Time("00:10");
        Time t2 = new Time("00:05");
        assertEquals("00:15", t1.addMinutes(t2.getMinutes()).toString());
        assertEquals("00:05", t2.toString());
        assertEquals("00:10", t1.minusMinutes(t2.getMinutes()).toString());
    }

    @Test
    void testTimeLogic() {
        Time t1 = new Time("00:10");
        Time t2 = new Time("00:05");
        assertFalse(t1.equalTime(t2));
        assertTrue(t1.laterThan(t2));
        assertTrue(t1.laterThan(5));
        assertFalse(t1.earlierThan(t2));
        assertFalse(t1.earlierThan(5));
    }

}
