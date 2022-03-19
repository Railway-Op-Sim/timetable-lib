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
    private ParseEvent() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an instance of the correct Event class based on a given string.
     *
     * @param eventString The string in the .ttb format for a single event.
     * @return The event instance that represents the given string.
     */
    public static Event parseEvent(String eventString) {
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
            } else if (eventSplit[1].equalsIgnoreCase("Frh-sh")) {
                return new FrhshEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("Sns-fsh")) {
                return new SnsfshEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else if (eventSplit[1].equalsIgnoreCase("F-nshs")) {
                return new FnshsEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
            } else {
                return new StopEvent(new Time(eventSplit[0]), new Time(eventSplit[1]), new NamedLocation(eventSplit[2]));
            }
        } else if (eventSplit.length == 4) {
            if(eventSplit[1].equalsIgnoreCase("Snt-sh")) {
                return new SntshEvent(new Time(eventSplit[0]), new StartLocation(eventSplit[2]), new Reference(eventSplit[3]));
            } else if(eventSplit[1].equalsIgnoreCase("Sns-sh")) {
                return new SnsshEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]), new Reference(eventSplit[3]));
            } else if(eventSplit[1].equalsIgnoreCase("Fns-sh")) {
                return new FnsshEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]), new Reference(eventSplit[3]));
            }
        }
        return null;
    }
}
