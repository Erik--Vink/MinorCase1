package data.controllers;

import data.domain.Course;
import data.repositories.ICourseRepository;

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

    public int createCourse(Course course){
        return this.courseRepository.create(course);
    }
}
