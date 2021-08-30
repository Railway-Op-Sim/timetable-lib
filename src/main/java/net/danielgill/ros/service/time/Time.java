package net.danielgill.ros.service.time;

public class Time {
    private int hours;
    private int minutes;
    
    public Time(String timeColon) {
        String[] split = timeColon.split(":");
        hours = Integer.parseInt(split[0]);
        minutes = Integer.parseInt(split[1]);
    }
    public Time(int minutes) {
        hours = (int) minutes / 60;
        this.minutes = (int) minutes % 60;
    }
    public Time(int minutes, int hours) {
        if(minutes >= 60) {
            this.hours = ((int) minutes/60) + hours;
            this.minutes = ((int) minutes % 60) - ((int) minutes/60);
        } else {
            this.hours = hours;
            this.minutes = minutes;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }
    
    public int getMinutes() {
        return this.minutes + (this.hours * 60);
    }
    
    public void addMinutes(int amount) {
        int oldMinutes = this.minutes + (this.hours * 60);
        oldMinutes += amount;
        int newHours = (int) oldMinutes / 60;
        int newMinutes = (int) oldMinutes % 60;
        if(minutes >= 60) {
            this.hours = ((int) newMinutes/60) + newHours;
            this.minutes = ((int) newMinutes % 60) - ((int) newMinutes/60);
        } else {
            this.hours = newHours;
            this.minutes = newMinutes;
        }
    }
    public void minusMinutes(int amount) {
        int oldMinutes = this.minutes + (this.hours * 60);
        oldMinutes -= amount;
        int newHours = (int) oldMinutes / 60;
        int newMinutes = (int) oldMinutes % 60;
        if(newMinutes >= 60 || newMinutes < 0) {
            this.hours = ((int) newMinutes/60) + newHours;
            this.minutes = ((int) newMinutes % 60) - ((int) newMinutes/60);
        } else {
            this.hours = newHours;
            this.minutes = newMinutes;
        }
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
