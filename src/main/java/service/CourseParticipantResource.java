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

//    @GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
//    public Course getOne(@PathParam("id") int id){
//        return this.courseController.getCourseById(id);
//    }
//
//    @POST
//    @Consumes("text/plain")
//    public Response create(String file){
//
//        try {
//            ArrayList<Course> courses = CourseReader.stringToCourses(file);
//            for(Course c : courses){
//                courseController.createCourse(c);
//            }
//        } catch (InvalidPropertiesFormatException e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
//        }
//
//        UriBuilder builder = UriBuilder.fromUri(uriInfo.getAbsolutePath());
////        builder.path(Integer.toString(id));
//        return Response.created(builder.build()).build();
//    }

}
