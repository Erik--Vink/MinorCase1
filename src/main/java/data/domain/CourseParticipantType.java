package data.domain;

/**
 * Created by Erik on 11-10-2016.
 */
public enum CourseParticipantType {
    SINGLE, COMPANY;

    public String toString() {
        switch(this) {
            case SINGLE: return "SINGLE";
            case COMPANY: return "COMPANY";
        }
        return null;
    }

    public static CourseParticipantType fromString(String string) {
        if(string.equals("SINGLE")){return SINGLE;}
        else if(string.equals("COMPANY")){return COMPANY;}
        else return null;
    }
}
