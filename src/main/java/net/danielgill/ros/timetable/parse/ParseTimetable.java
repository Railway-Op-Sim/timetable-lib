package net.danielgill.ros.timetable.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.danielgill.ros.timetable.Timetable;
import net.danielgill.ros.timetable.time.Time;

/**
 * A class that can parse a timetable given a file input.
 * @author Daniel Gill
 */
public class ParseTimetable {
    private ParseTimetable() {
        throw new UnsupportedOperationException();
    }

    /**
     * Parses a timetable in the .ttb format and returns the timetable object.
     * @param file The file to be parsed.
     * @return The timetable object containing the parsed timetable.
     * @throws FileNotFoundException Thrown if the file cannot be accessed.
     * @see Timetable
     */
    public static Timetable parseTimetable(File file) throws FileNotFoundException {
        Scanner read = new Scanner(file);
        read.useDelimiter("\u0000");
        List<String> lines = new ArrayList<>();
        while(read.hasNext()) {
            lines.add(read.next());
        }
        read.close();

        Timetable t;
        boolean startTimeFound = false;

        t = new Timetable(null);
        while(!startTimeFound) {
            String newLine = lines.remove(0);
            newLine = newLine.replace(" ", "");
            Pattern p = Pattern.compile("[0-9][0-9]:[0-9][0-9]");
            Matcher m = p.matcher(newLine);
            if(m.find() && !isComment(newLine)) {
                t = new Timetable(new Time(newLine.substring(m.start(), m.end())));
                startTimeFound = true;
            } else {

            }
        }

        for(String line : lines) {
            if(isComment(line)) {
                continue;
            }
            t.addService(ParseService.parseService(line));
        }

        return t;
    }

    private static boolean isComment(String line) {
        line = line.replace(" ", "");
        return line.startsWith("*") || line.startsWith("-") || line.startsWith("+");
    }
}
