package net.danielgill.ros.timetable.parse;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.service.Service;

/**
 * A class which can parse a service given a String input.
 * @author Daniel Gill
 */
public class ParseService {
    private static List<String> lines;

    /**
     * Parses a service from the .ttb format string and returns the service object.
     * @param serviceString The service string to be parsed.
     * @return The parsed service object.
     * @see Service
     */
    public static Service parseService(String serviceString) {
        lines = new ArrayList<>();
        for(String split : serviceString.split(",")) {
            lines.add(split);
        }
        Service s;
        
        String init = lines.remove(0);
        String[] initSplit = init.split(";");
        if(initSplit.length == 2) {
            s = new Service(new Reference(initSplit[0]), initSplit[1]);
        } else if(initSplit.length == 7) {
            s = new Service(new Reference(initSplit[0]), initSplit[1], Integer.parseInt(initSplit[2]), Integer.parseInt(initSplit[3]), Integer.parseInt(initSplit[4]), Integer.parseInt(initSplit[5]), Integer.parseInt(initSplit[6]));
        } else {
            s = new Service(new Reference(initSplit[0]), "Test");
        }

        for(String line : lines) {
            s.addEvent(ParseEvent.parseEvent(line));
        }

        return s;
    }
}
