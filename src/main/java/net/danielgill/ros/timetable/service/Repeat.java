package net.danielgill.ros.timetable.service;

import net.danielgill.ros.timetable.time.Time;

/**
 * Represents a repeat within a service.
 * @author Daniel Gill
 */
public class Repeat {
    private int interval;
    private int increment;
    private int number;

    /**
     * Creates a new repeat.
     * @param interval The time in minutes between repeats.
     * @param increment The increment of the reference for repeats.
     * @param number The number of repeats of a service after the original.
     */
    public Repeat(int interval, int increment, int number) {
        this.increment = increment;
        this.interval = interval;
        this.number = number;
    }

    /**
     * Creates a new repeat.
     * @param interval The time object representing the between repeats.
     * @param increment The increment of the reference for repeats.
     * @param number The number of repeats of a service after the original.
     */
    public Repeat(Time interval, int increment, int number) {
        this.increment = increment;
        this.interval = interval.getMinutes();
        this.number = number;
    }

    /**
     * Gets the string representation of a repeat.
     * @return The string representation of a repeat or the empty string in the case of no repeats.
     */
    @Override
    public String toString() {
        if(number >= 1) {
            return ("R;" + interval + ";" + increment + ";" + number);
        } else {
            return "";
        }
    }
}
