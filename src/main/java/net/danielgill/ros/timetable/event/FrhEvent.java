package net.danielgill.ros.timetable.event;

public class FrhEvent extends Event {

    public FrhEvent() {
        super("Frh");
    }

    @Override
    public String toString() {
        return "Frh";
    }

    @Override
    public Event newInstance(Event event) {
        return new FrhEvent();
    }

    @Override
    public void validateEvent() throws EventInvalidException {
    }

    @Override
    public String getDescription() {
        return "Finish and Remain Here";
    }

    @Override
    public String getContextualDescription() {
        return "Finishes and remains here";
    }
    
}
