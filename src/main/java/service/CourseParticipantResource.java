package service;

import data.controllers.CourseController;
import data.controllers.CourseParticipantController;
import data.domain.Course;
import data.domain.CourseParticipant;
import data.helpers.CourseReader;
import data.repositories.CourseParticipantRepository;
import data.repositories.CourseRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

@Path("/courseparticipants")
public class CourseParticipantResource {

    @Context
    UriInfo uriInfo;

    private CourseParticipantController courseParticipantController;

    public CourseParticipantResource(){
        this.courseParticipantController = new CourseParticipantController(new CourseParticipantRepository());
    }

    @GET @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<CourseParticipant> getAll(){
        return this.courseParticipantController.getAllCourseParticipants();
    }

}
