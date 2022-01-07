package net.danielgill.ros.timetable.data;

public class DataTemplate {
    private Data data;
    private String keyword;
    
    public DataTemplate(String keyword, int maxSpeed, int mass, int maxBrake, int power) {
        this.data = new Data(maxSpeed, mass, maxBrake, power);
        this.keyword = keyword;
    }
    
    public Data getData(){
        return data;
    }
    
    public boolean keywordEqual(String keywordTest) {
        return this.keyword.equalsIgnoreCase(keywordTest);
    }
}
