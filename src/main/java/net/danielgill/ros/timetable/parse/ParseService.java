package net.danielgill.ros.timetable.parse;

import java.util.ArrayList;
import java.util.List;

import net.danielgill.ros.timetable.reference.Reference;
import net.danielgill.ros.timetable.service.Service;

public class ParseService {
    private static List<String> lines;

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
            s.addEvent(ParseEvent.getEventFromString(line));
        }

        return s;
    }
}
