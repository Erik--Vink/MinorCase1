package data.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by Erik on 12-10-2016.
 */
@Builder
@Data
public class Invoice {
    private CourseParticipant courseParticipant;
    private ArrayList<Course> courses;

    public Invoice(){
        this.courses = new ArrayList<>();
    }

    public Invoice(CourseParticipant courseParticipant, ArrayList<Course> courses){
        this.courseParticipant = courseParticipant;
        this.courses = courses;
    }
}
