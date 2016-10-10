package data.repositories;

import data.domain.Course;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Erik on 8-10-2016.
 */
public interface ICourseRepository {
    ArrayList<Course> getAll();
    Course getById(int id);
    int create(Course course);
}
