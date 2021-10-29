package net.danielgill.ros.service.data;

public class Data {
    private final int power;
    private final int mass;
    private final int maxSpeed;
    private final int maxBrake;
    private final int startSpeed;
    
    public Data(int startSpeed, int maxSpeed, int mass, int maxBrake, int power) {
        this.power = power;
        this.mass = mass;
        this.maxSpeed = maxSpeed;
        this.maxBrake = maxBrake;
        this.startSpeed = startSpeed;
    }
    
    public Data(int maxSpeed, int mass, int maxBrake, int power) {
        this.power = power;
        this.mass = mass;
        this.maxSpeed = maxSpeed;
        this.maxBrake = maxBrake;
        this.startSpeed = -1;
    }
    
    public int getPower() {
        return power;
    }

    public int getMass() {
        return mass;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxBrake() {
        return maxBrake;
    }

    public int getStartSpeed() {
        return startSpeed;
    }
}
