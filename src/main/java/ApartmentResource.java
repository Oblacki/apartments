

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;
import javax.inject.Inject;
import org.json.*;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/apartments")
@Log
public class ApartmentResource {

    private Logger log = Logger.getLogger(ApartmentResource.class.getName());

    @Inject
    private RestProperties restProperties;

    @GET
    @Metered
    public Response getAllApartments() {
        //first check if service is "healthy"
        if (!restProperties.isHealthy())
        {
            log.info("/apartments is sick :V (config value rest-properties.healthy is probably set to false)");
            return null;
        }
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

    @POST
    @Path("/healthy") //sets the rest-properties.healthy value in the config.yaml file
    public Response setHealth(Boolean healthy) {
        restProperties.setHealthy(healthy);
        log.info("Setting health to " + healthy);
        return Response.ok().build();
    }

    @GET
    @Path("/info")
    public Response getAssignmentInfo(){
        JSONObject json = new JSONObject();

        String[] clani = {"md1851", "vb4016"};
        json.put("clani", clani);

        String opis_projekta = "Najin projekt implementira aplikacijo za oddajo nepremicnin.";
        json.put("opis_projekta", opis_projekta);

        String[] ms = {"http://169.51.18.147:31400/v1/apartments", "http://169.51.18.147:30846/v1/customers"};
        json.put("mikrostoritve", ms);

        String[] github = {"https://github.com/Oblacki/apartments", "https://github.com/Oblacki/customer"};
        json.put("github", github);

        String[] travis = {"https://travis-ci.org/Oblacki/apartments", "https://travis-ci.org/Oblacki/customer"};
        json.put("travis", travis);

        String[] docker = {"https://hub.docker.com/r/oblacki/apartments/", "https://hub.docker.com/r/oblacki/customers/"};
        json.put("docker", docker);

        return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
    }
}
