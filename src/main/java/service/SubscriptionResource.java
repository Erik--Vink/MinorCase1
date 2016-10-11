package service;

import data.controllers.SubscriptionController;
import data.domain.Subscription;
import data.repositories.CourseParticipantRepository;
import data.repositories.CourseRepository;
import data.repositories.SubscriptionRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;

@Path("/subscriptions")
public class SubscriptionResource {

    @Context
    UriInfo uriInfo;

    private SubscriptionController subscriptionController;

    public SubscriptionResource(){
        this.subscriptionController = new SubscriptionController(new SubscriptionRepository(), new CourseRepository(), new CourseParticipantRepository());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Subscription subscription){

        try {
            this.subscriptionController.createSubscription(subscription);
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        UriBuilder builder = UriBuilder.fromUri(uriInfo.getAbsolutePath());
        return Response.created(builder.build()).build();
    }

}
