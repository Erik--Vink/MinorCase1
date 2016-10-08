package service;

import data.Cursus;
import repositories.CursusRepository;
import repositories.DatabaseConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;

@Path("/cursussen")
public class CursusResource {
    @Context
    UriInfo uriInfo;

    @GET @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Cursus> getAll(){
        CursusRepository repository =  new CursusRepository(new DatabaseConnection());
        return repository.getAll();
    }


    @GET @Path("/{code}") @Produces(MediaType.APPLICATION_JSON)
    public Cursus getOne(@PathParam("code") String code){
        CursusRepository repository =  new CursusRepository(new DatabaseConnection());
        return repository.getByCode(code);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Cursus cursus){

        CursusRepository repository =  new CursusRepository(new DatabaseConnection());
        int id = repository.create(cursus);

        UriBuilder builder = UriBuilder.fromUri(uriInfo.getAbsolutePath());
        builder.path(Integer.toString(id));
        return Response.created(builder.build()).build();
    }

}
