package net.danielgill.ros.timetable.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.danielgill.ros.timetable.Timetable;
import net.danielgill.ros.timetable.time.Time;

public class ParseTimetable {
    private static List<String> lines;

    public static Timetable parseTimetable(File file) throws FileNotFoundException {
        Scanner read = new Scanner(file);
        read.useDelimiter("\u0000");
        lines = new ArrayList<>();
        while(read.hasNext()) {
            lines.add(read.next());
        }
        read.close();

        Timetable t = new Timetable(new Time(lines.remove(0)));

        for(String line : lines) {
            if(isComment(line)) {
                continue;
            }
            t.addService(ParseService.parseService(line));
        }

        return t;
    }

    private static boolean isComment(String line) {
        line.replace(" ", "");
        if(line.startsWith("*")) {
            return true;
        } else {
            return false;
        }
    }
}
