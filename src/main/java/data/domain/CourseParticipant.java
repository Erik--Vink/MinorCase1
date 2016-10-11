package data.domain;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Erik on 10-10-2016.
 */
@Data
public abstract class CourseParticipant {
    protected LinkedList<CourseParticipant> courseParticipantList;
    protected CourseParticipant parent;

    protected int id;
    protected String email;
    protected String forename;
    protected String surname;
    protected String zipcode;
    protected String place;
    protected String street;
    protected String houseNumber;
    protected String accountNumber;
    protected CourseParticipantType type;
    protected int parentId;

    public abstract boolean add(CourseParticipant courseParticipant);
    public abstract ListIterator createListIterator();

    public void setParent(CourseParticipant courseParticipant){
        this.parent = courseParticipant;
    }

    public CourseParticipant getParent(){
        return this.parent;
    }

}
