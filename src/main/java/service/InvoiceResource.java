package service;

import data.controllers.InvoiceController;
import data.domain.Invoice;
import data.repositories.CourseParticipantRepository;
import data.repositories.CourseRepository;
import data.repositories.SubscriptionRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

/**
 * Created by Erik on 12-10-2016.
 */
@Path("/invoices")
public class InvoiceResource {

    @Context
    UriInfo uriInfo;

    private InvoiceController invoiceController;

    public InvoiceResource(){
        this.invoiceController = new InvoiceController(new CourseParticipantRepository(), new CourseRepository(), new SubscriptionRepository());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Invoice> getAll(@QueryParam("year") int year, @QueryParam("weeknumber") int weeknumber){

        return this.invoiceController.getAllInvoices(year, weeknumber);
    }
}
