package service;

import data.controllers.CourseController;
import data.domain.Course;
import data.repositories.CourseRepository;
import data.repositories.DatabaseConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;

@Path("/courses")
public class CourseResource {

    @Context
    UriInfo uriInfo;

    private CourseController courseController;

    public CourseResource(){
        this.courseController = new CourseController(new CourseRepository());
    }

    @GET @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Course> getAll(){
        return this.courseController.getAllCourses();
    }

    @GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
    public Course getOne(@PathParam("id") int id){
        Course course = this.courseController.getCourseById(id);
        return course;
    }

    @POST
    @Consumes("text/plain")
    public Response create(String file){

        String filedata = file;

//        int id = this.courseController.createCourse(course);
//
        UriBuilder builder = UriBuilder.fromUri(uriInfo.getAbsolutePath());
//        builder.path(Integer.toString(id));
        return Response.created(builder.build()).build();
    }

}
