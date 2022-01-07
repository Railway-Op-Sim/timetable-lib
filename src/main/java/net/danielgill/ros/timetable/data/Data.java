package net.danielgill.ros.timetable.data;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.service.ServiceInvalidException;

/**
 * Stores data for a single service, including the start speed.
 * @author Daniel Gill
 */
public class Data {
    private final int power;
    private final int mass;
    private final int maxSpeed;
    private final int maxBrake;
    private final int startSpeed;
    
    /**
     * Creates a new data including the start speed.
     * @param startSpeed
     * @param maxSpeed
     * @param mass
     * @param maxBrake
     * @param power 
     */
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
    
    /**
     * Creates another instance of data from an existing data instance and a start speed.
     * @param startSpeed
     * @param data 
     */
    public Data(int startSpeed, Data data) {
        this(startSpeed, data.maxSpeed, data.mass, data.maxBrake, data.power);
    }
    
    /**
     * Returns a string version of the data.
     * @return Data in string form.
     */
    @Override
    public String toString() {
        return startSpeed + ";" + maxSpeed + ";" + mass + ";" + maxBrake + ";" + power;
    }
    
    /**
     * Validates data, and throws an exception if data values are incorrect.
     * @param ref The reference of the service, used for throwing the exception.
     * @throws ServiceInvalidException Thrown if data values are incorrect.
     */
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
