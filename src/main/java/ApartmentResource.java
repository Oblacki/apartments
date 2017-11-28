

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;
import javax.inject.Inject;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/apartments")
@Log
public class ApartmentResource {

    private Logger log = Logger.getLogger(ApartmentResource.class.getName());

//    @Inject
//    private RestProperties restProperties;
//
//    @POST
//    @Path("/healthy")
//    public Response setHealth(Boolean healthy) {
//        restProperties.setHealthy(healthy);
//        log.info("Setting health to " + healthy);
//        return Response.ok().build();
//    }

    @GET
    @Metered
    public Response getAllApartments() {
        List<Apartment> apartments = Database.getApartments();
        log.info("Getting all apartments...");
        return Response.ok(apartments).build();
    }

    @GET
    @Path("/{apartmentId}")
    public Response getApartment(@PathParam("apartmentId") String apartmentId) {
        Apartment apartment = Database.getApartment(apartmentId);
        return apartment != null
                ? Response.ok(apartment).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getApartmentByCustomer(@PathParam("customerId") String customerId) {
        List<Apartment> apartments = Database.getApartmentByCustomerId(customerId);
        return apartments != null
                ? Response.ok(apartments).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewApartment(Apartment apartment) {
        Database.addApartment(apartment);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{apartmentId}")
    public Response deleteApartment(@PathParam("apartmentId") String apartmentId) {
        Database.deleteApartment(apartmentId);
        return Response.noContent().build();
    }
}
