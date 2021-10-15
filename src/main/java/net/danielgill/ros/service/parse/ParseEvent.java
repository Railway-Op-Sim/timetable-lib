package net.danielgill.ros.service.parse;

import net.danielgill.ros.service.event.*;
import net.danielgill.ros.service.location.Location;
import net.danielgill.ros.service.location.NamedLocation;
import net.danielgill.ros.service.location.StartLocation;
import net.danielgill.ros.service.reference.Reference;
import net.danielgill.ros.service.time.Time;

public class ParseEvent {
	public ParseEvent() {

	}

	public Event getEventFromString(String eventString) {
		//TODO: Add more events here when they are added the event package.
		String[] eventSplit = eventString.split(";");
		if(eventSplit.length == 2) {
			if(eventSplit[1].equalsIgnoreCase("cdt")) {
				return new CdtEvent(new Time(eventSplit[0]));
			} else {
				return new StopEvent(new Time(eventSplit[0]), new NamedLocation(eventSplit[1]));
			}
		} else if(eventSplit.length == 3) {
			if(eventSplit[1].equalsIgnoreCase("Fer")) {
				return new FerEvent(new Time(eventSplit[0]), new Location(eventSplit[2]));
			} else if(eventSplit[1].equalsIgnoreCase("Fns")) {
				return new FnsEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
			} else if(eventSplit[1].equalsIgnoreCase("pas")) {
				return new PassEvent(new Time(eventSplit[0]), new NamedLocation(eventSplit[2]));
			} else if(eventSplit[1].equalsIgnoreCase("Sns")) {
				return new SnsEvent(new Time(eventSplit[0]), new Reference(eventSplit[2]));
			} else if(eventSplit[1].equalsIgnoreCase("Snt")) {
				return new SntEvent(new Time(eventSplit[0]), new StartLocation(eventSplit[2]));
			} else {
				return new StopEvent(new Time(eventSplit[0]), new Time(eventSplit[1]), new NamedLocation(eventSplit[2]));
			}
		}
		return null;
	}
}
