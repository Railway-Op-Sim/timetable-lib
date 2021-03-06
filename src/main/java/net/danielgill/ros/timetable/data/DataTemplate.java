package net.danielgill.ros.timetable.data;

/**
 * A template for containing service data, identified with a keyword.
 * @author Daniel Gill
 */
public class DataTemplate {
    private Data data;
    private String keyword;
    private String label;
    
    /**
     * Create a new data template from individual service data and a keyword.
     * @param keyword The keyword for the data template
     * @param maxSpeed
     * @param mass
     * @param maxBrake
     * @param power 
     */
    public DataTemplate(String keyword, int maxSpeed, int mass, int maxBrake, int power) {
        this.data = new Data(maxSpeed, mass, maxBrake, power);
        this.keyword = keyword;
        this.label = "";
    }
    
    /**
     * Create a new data template from individual service data, a keyword and label.
     * @param keyword
     * @param maxSpeed
     * @param mass
     * @param maxBrake
     * @param power
     * @param label
     */
    public DataTemplate(String keyword, int maxSpeed, int mass, int maxBrake, int power, String label) {
        this.data = new Data(maxSpeed, mass, maxBrake, power);
        this.keyword = keyword;
        this.label = label;
    }
    
    /**
     * Gets the data included in a single data template.
     * @return The service data
     */
    public Data getData(){
        return data;
    }

    /**
     * Gets the keyword for a given data template.
     * @return The keyword
     */
    public String getKeyword() {
        return keyword;
    }
    
    /**
     * Checks if the keyword is equal to a given string.
     * @param keywordTest The string to be checked against the keyword.
     * @return A boolean for whether they are equal.
     */
    public boolean keywordEqual(String keywordTest) {
        return this.keyword.equalsIgnoreCase(keywordTest);
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
