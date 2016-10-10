package data.helpers;

import data.domain.Course;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Erik on 10-10-2016.
 */
public class CourseReader {

    public static ArrayList<Course> stringToCourses(String input) throws InvalidPropertiesFormatException {

        ArrayList<Course> courses = new ArrayList<>();

        String[] coursePairs = input.split("\r\n\r\n");

        for(String coursepair: coursePairs){
            String[] keyValuePairs = coursepair.split("\r\n");
            LinkedHashMap<String, String> map = new LinkedHashMap<>();

            if(keyValuePairs.length < 4){
                throw new InvalidPropertiesFormatException("The file is missing fields");
            }

            if(keyValuePairs.length > 4){
                throw new InvalidPropertiesFormatException("The file is missing new lines");
            }

            for(String s : keyValuePairs){
                String[] keyValueParts = s.split(":");
                for (int i = 0; i < keyValueParts.length; i += 2) {
                    map.put(keyValueParts[i], keyValueParts[i + 1]);
                }
            }
            courses.add(parseMapToCourse(map));
        }

        return courses;
    }

    private static Course parseMapToCourse(LinkedHashMap<String, String> map) throws InvalidPropertiesFormatException {
        Course course = new Course();
        int currentPosition = 0;

        for(String key : map.keySet()){
            switch(key){
                case "Cursuscode":
                    checkOrderPosition(key, currentPosition);
                    course.setCode(map.get(key).trim());
                    break;
                case "Startdatum":
                    checkOrderPosition(key, currentPosition);
                    String date = map.get(key).trim();
                    String datePattern = "[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}";
                    Pattern r = Pattern.compile(datePattern);
                    Matcher dateMatcher = r.matcher(date);
                    if (dateMatcher.find( )) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        course.setStartDate(LocalDate.parse(date, formatter));
                    }
                    else{
                        throw new InvalidPropertiesFormatException("Invalid date format");
                    }
                    break;
                case "Titel":
                    checkOrderPosition(key, currentPosition);
                    course.setTitle(map.get(key).trim());
                    break;
                case "Duur":
                    checkOrderPosition(key, currentPosition);
                    String durationString = map.get(key).trim();
                    String durationPattern = "[0-9]{1,2} dagen";
                    Pattern dp = Pattern.compile(durationPattern);
                    Matcher durationMatcher = dp.matcher(durationString);
                    if (durationMatcher.find( )) {
                        int duration = Integer.valueOf(durationString.substring(0, durationString.indexOf(" ")));
                        course.setDuration(duration);
                    }
                    else{
                        throw new InvalidPropertiesFormatException("Invalid duration format");
                    }
                    break;
            }
            currentPosition++;
        }

        return course;
    }

    private static void checkOrderPosition(String key, int position) throws InvalidPropertiesFormatException {
        String errorMessage = "Invalid order format";
        switch (key){
            case "Titel":
                if(position != 0){
                    throw new InvalidPropertiesFormatException(errorMessage);
                }
                break;
            case "Cursuscode":
                if(position != 1){
                    throw new InvalidPropertiesFormatException(errorMessage);
                }
                break;
            case "Duur":
                if(position != 2){
                    throw new InvalidPropertiesFormatException(errorMessage);
                }
                break;
            case "Startdatum":
                if(position != 3){
                    throw new InvalidPropertiesFormatException(errorMessage);
                }
                break;
        }
    }

}
