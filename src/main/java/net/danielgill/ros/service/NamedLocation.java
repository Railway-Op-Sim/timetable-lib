package net.danielgill.ros.service;

public class NamedLocation {
    private String name;
    
    public NamedLocation(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
