package net.danielgill.ros.timetable.parse;

import net.danielgill.ros.timetable.event.*;
import net.danielgill.ros.timetable.location.Location;
import net.danielgill.ros.timetable.location.NamedLocation;
import net.danielgill.ros.timetable.location.StartLocation;
import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.time.Time;

/**
 * A class which can create an event based on a String input.
 *
 * @author Daniel Gill
 */
public class ParseEvent {

    public ParseEvent() {
        //Not sure what to put here.
    }

    /**
     * Creates an instance of the correct Event class based on a given string.
     *
     * @param eventString The string in the .ttb format for a single event.
     * @return The event instance that represents the given string.
     */
    public static Event getEventFromString(String eventString) {
        String[] eventSplit = eventString.split(";");
        if(eventSplit.length == 1) {
            return new FrhEvent();
        } else if (eventSplit.length == 2) {
            if (eventSplit[1].equalsIgnoreCase("cdt")) {
                return new CdtEvent(new Time(eventSplit[0]));
            } else {
                return new StopEvent(new Time(eventSplit[0]), new NamedLocation(eventSplit[1]));
            }
        } else if (eventSplit.length == 3) {
            if (eventSplit[1].equalsIgnoreCase("Fer")) {
                return new FerEvent(new Time(eventSplit[0]), new Location(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("Fns")) {
                return new FnsEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("pas")) {
                return new PassEvent(new Time(eventSplit[0]), new NamedLocation(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("Sns")) {
                return new SnsEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("Snt")) {
                return new SntEvent(new Time(eventSplit[0]), new StartLocation(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("Sfs")) {
                return new SfsEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("jbo")) {
                return new JboEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("fsp")) {
                return new FspEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("rsp")) {
                return new RspEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else {
                return new StopEvent(new Time(eventSplit[0]), new Time(eventSplit[1]), new NamedLocation(eventSplit[2]));
            }
        }
        return null;
    }
}
