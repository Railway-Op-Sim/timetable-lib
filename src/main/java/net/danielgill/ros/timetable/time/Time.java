package net.danielgill.ros.timetable.time;

/**
 * A class to store the time in events and other classes.
 * @author Daniel Gill
 */
public class Time {
    private int hours;
    private int minutes;

    /**
     * Creates a new Time instance from a String.
     * @param timeColon A string of the time, with a colon in the middle, e.g. "12:32".
     */
    public Time(String timeColon) {
        String[] split = timeColon.split(":");
        hours = Integer.parseInt(split[0]);
        minutes = Integer.parseInt(split[1]);
    }

    /**
     * Creates a new Time instance from a given number of minutes.
     * @param minutes An integer representing the number of minutes.
     */
    public Time(int minutes) {
        if(minutes >= 1440) {
            minutes %= 1440;
        } 
        hours = (int) minutes / 60;
        this.minutes = (int) minutes % 60;
    }

    /**
     * Creates a new Time instance from the minutes and hours.
     * @param minutes An integer representing the number of minutes.
     * @param hours An integer representing the number of hours.
     */
    public Time(int minutes, int hours) {
        if(minutes >= 60) {
            this.hours = ((int) minutes/60) + hours;
            this.minutes = ((int) minutes % 60) - ((int) minutes/60);
        } else {
            this.hours = hours;
            this.minutes = minutes;
        }
    }

    /**
     * Creates a new Time instance from a different time instance.
     * @param time A given instance of Time to be used.
     */
    public Time(Time time) {
        this.hours = time.hours;
        this.minutes = time.minutes;
    }

    /**
     * Returns the time as a string, containing the colon.
     * @return A String for the time, including the colon.
     */
    @Override
    public String toString() {
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }

    /**
     * Returns the number of minutes for a time instance.
     * @return An integer for the number of minutes after 00:00.
     */
    public int getMinutes() {
        return this.minutes + (this.hours * 60);
    }

    /**
     * Adds a number of minutes to a time instance, then returns itself.
     * @param amount An integer of minutes to be added.
     * @return The instance of Time with minutes added.
     */
    public Time addMinutes(int amount) {
        int oldMinutes = this.minutes + (this.hours * 60);
        oldMinutes += amount;
        if(oldMinutes >= 1440) {
            oldMinutes %= 1440;
        } 
        int newHours = (int) oldMinutes / 60;
        int newMinutes = (int) oldMinutes % 60;
        if(minutes >= 60) {
            this.hours = ((int) newMinutes/60) + newHours;
            this.minutes = ((int) newMinutes % 60) - ((int) newMinutes/60);
        } else {
            this.hours = newHours;
            this.minutes = newMinutes;
        }
        return this;
    }

    /**
     * Subtracts a number of minutes to a time instance, then returns itself.
     * @param amount An integer of minutes to be subtracted.
     * @return An instance of Time with minutes subtracted.
     */
    public Time minusMinutes(int amount) {
        int oldMinutes = this.minutes + (this.hours * 60);
        oldMinutes -= amount;
        if(oldMinutes < 0) {
            oldMinutes += (1440 * (Math.abs((int) oldMinutes / 1440) + 1));
        }
        int newHours = (int) oldMinutes / 60;
        int newMinutes = (int) oldMinutes % 60;
        if(newMinutes >= 60 || newMinutes < 0) {
            this.hours = ((int) newMinutes/60) + newHours;
            this.minutes = ((int) newMinutes % 60) - ((int) newMinutes/60);
        } else {
            this.hours = newHours;
            this.minutes = newMinutes;
        }
        return this;
    }
    
    /**
     * Adds a number of minutes to the time instance, then returns a new instance without updating.
     * @param amount An integer of minutes to be added.
     * @return The instance of Time with minutes added.
     */
    public Time getNewAddMinutes(int amount) {
        int oldMinutes = this.minutes + (this.hours * 60);
        oldMinutes += amount;
        if(oldMinutes >= 1440) {
            oldMinutes %= 1440;
        } 
        int newHours = (int) oldMinutes / 60;
        int newMinutes = (int) oldMinutes % 60;
        Time t;
        if(minutes >= 60) {
            t = new Time(((int) newMinutes % 60) - ((int) newMinutes/60), ((int) newMinutes/60) + newHours);
        } else {
            t = new Time(newMinutes, newHours);
        }
        return t;
    }
    
    /**
     * Subtracts a number of minutes to the time instance, then returns a new instance without updating.
     * @param amount An integer of minutes to be subtracted.
     * @return An instance of Time with minutes subtracted.
     */
    public Time getNewMinusMintues(int amount) {
        int oldMinutes = this.minutes + (this.hours * 60);
        oldMinutes -= amount;
        if(oldMinutes < 0) {
            oldMinutes += (1440 * (Math.abs((int) oldMinutes / 1440) + 1));
        }
        int newHours = (int) oldMinutes / 60;
        int newMinutes = (int) oldMinutes % 60;
        Time t;
        if(newMinutes >= 60 || newMinutes < 0) {
            t = new Time(((int) newMinutes % 60) - ((int) newMinutes/60), ((int) newMinutes/60) + newHours);
        } else {
            t = new Time(newMinutes, newHours);
        }
        return t;
    }
    
    public boolean earlierThan(int compareMins) {
        int totalMins = this.minutes + (this.hours * 60);
        return totalMins < compareMins;
    }
    public boolean earlierThan(Time compareTime) {
        int totalMins = this.minutes + (this.hours * 60);
        return totalMins < compareTime.getMinutes();
    }
    public boolean laterThan(int compareMins) {
        int totalMins = this.minutes + (this.hours * 60);
        return totalMins > compareMins;
    }
    public boolean laterThan(Time compareTime) {
        int totalMins = this.minutes + (this.hours * 60);
        return totalMins > compareTime.getMinutes();
    }

    public boolean equalTime(Time compareTime) {
        return this.getMinutes() == compareTime.getMinutes();
    }
}
