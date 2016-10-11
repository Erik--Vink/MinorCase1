package data.controllers;

import data.domain.Course;
import data.repositories.ICourseRepository;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Erik on 10-10-2016.
 */
public class CourseController {

    private ICourseRepository courseRepository;

    public CourseController(ICourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public ArrayList<Course> getAllCourses(){
        return this.courseRepository.getAll();
    }

    public Course getCourseById(int id){
        return this.courseRepository.getById(id);
    }

    public int createCourse(Course course) throws IllegalArgumentException{
        if(this.courseHasDateConflicts(course)){
            throw new IllegalArgumentException("The file contains courses that already exist on the given dates.");
        }
        return this.courseRepository.create(course);
    }

    private boolean courseHasDateConflicts(Course course){

        ArrayList<Course> courses = courseRepository.getAll();
        return courses.stream()
                .anyMatch(c -> (c.getStartDate().plusDays(1).isAfter(course.getStartDate()) && c.getStartDate().minusDays(1).isBefore(course.getStartDate().plusDays(course.getDuration()))) ||
                        (c.getStartDate().minusDays(1).isBefore(course.getStartDate()) && c.getStartDate().plusDays(c.getDuration() + 1).isAfter(course.getStartDate())));

    }
}
