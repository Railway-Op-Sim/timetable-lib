package net.danielgill.ros.timetable.reference;

/**
 * A class which stores the reference to a service.
 * @author Daniel Gill
 */
public class Reference {
    private String ref;

    /**
     * Creates a reference from a given reference string.
     * @param ref A string containing the reference.
     */
    public Reference(String ref) {
        this.ref = ref;
    }

    /**
     * Creates a reference copy from another instance.
     * @param ref The reference instance to be copied.
     */
    public Reference(Reference ref) {
        this.ref = ref.getRef();
    }

    /**
     * Increments the reference by a given integer value.
     * @param increment The increment to be added to the reference.
     */
    public void incrementRef(int increment) {
        ref = ref.substring(0, 2) + String.format("%02d", (Integer.parseInt(ref.substring(2, 4)) + increment) % 100);
    }

    /**
     * Returns the reference as a String.
     * @return The String containing the reference.
     */
    @Override
    public String toString() {
        return ref;
    }

    /**
     * Returns the reference as a String.
     * @return The String containing the reference.
     */
    public String getRef() {
        return ref;
    }
}
