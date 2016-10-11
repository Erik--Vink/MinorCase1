package service;

import data.controllers.CourseController;
import data.domain.Course;
import data.helpers.CourseReader;
import data.repositories.CourseRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

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
        return this.courseController.getCourseById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Course course){

        int id = courseController.createCourse(course);

        UriBuilder builder = UriBuilder.fromUri(uriInfo.getAbsolutePath());
        builder.path(Integer.toString(id));
        return Response.created(builder.build()).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response create(String file){

        try {
            ArrayList<Course> courses = CourseReader.stringToCourses(file);
            for(Course c : courses){
                try {
                    courseController.createCourse(c);
                }
                catch (IllegalArgumentException e){
                    return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
                }
            }
        } catch (InvalidPropertiesFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        UriBuilder builder = UriBuilder.fromUri(uriInfo.getAbsolutePath());
//        builder.path(Integer.toString(id));
        return Response.created(builder.build()).build();
    }

}
