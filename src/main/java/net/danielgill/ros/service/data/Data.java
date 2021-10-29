package net.danielgill.ros.service.data;

import net.danielgill.ros.service.ServiceInvalidException;
import net.danielgill.ros.service.reference.Reference;

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
    
    protected Data(int maxSpeed, int mass, int maxBrake, int power) {
        this.power = power;
        this.mass = mass;
        this.maxSpeed = maxSpeed;
        this.maxBrake = maxBrake;
        this.startSpeed = -1;
    }
    
    public Data(int startSpeed, Data data) {
        this(startSpeed, data.maxSpeed, data.mass, data.maxBrake, data.power);
    }
    
    public String toString() {
        return startSpeed + ";" + maxSpeed + ";" + mass + ";" + maxBrake + ";" + power;
    }
    
    public void validate(Reference ref) throws ServiceInvalidException {
        if(power <= 0 || mass <= 0 || maxSpeed <= 0 || maxBrake <= 0 || startSpeed == -1) {
            throw new ServiceInvalidException("One or more data values for service are missing or incorrect.", ref);
        }
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
