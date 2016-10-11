package data.controllers;

import data.domain.Course;
import data.domain.CourseParticipant;
import data.repositories.ICourseParticipantRepository;
import data.repositories.ICourseRepository;

import java.util.ArrayList;

/**
 * Created by Erik on 10-10-2016.
 */
public class CourseParticipantController {

    private ICourseParticipantRepository courseParticipantRepository;

    public CourseParticipantController(ICourseParticipantRepository courseParticipantRepository){
        this.courseParticipantRepository = courseParticipantRepository;
    }

    public ArrayList<CourseParticipant> getAllCourseParticipants(){
        return this.courseParticipantRepository.getAll();
    }
}
