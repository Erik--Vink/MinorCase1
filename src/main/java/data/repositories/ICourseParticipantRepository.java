package data.repositories;

import data.domain.Course;
import data.domain.CourseParticipant;

import java.util.ArrayList;

/**
 * Created by Erik on 8-10-2016.
 */
public interface ICourseParticipantRepository {
    ArrayList<CourseParticipant> getAll();
    CourseParticipant getById(int id);
    ArrayList<CourseParticipant> getAllByParentId(int id);
    int create(CourseParticipant courseParticipant);
}
