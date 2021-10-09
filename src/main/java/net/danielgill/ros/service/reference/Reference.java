package net.danielgill.ros.service.reference;

public class Reference {
    private String ref;
    
    public Reference(String ref) {
        this.ref = ref;
    }
    
    public void incrementRef(int increment) {
        ref = ref.substring(0, 2) + String.format("%02d", (Integer.parseInt(ref.substring(2, 4)) + increment));
    }
    
    @Override
    public String toString() {
        return ref;
    }
    
    public String getRef() {
        return ref;
    }
}
