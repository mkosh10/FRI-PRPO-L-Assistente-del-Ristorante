package si.uni.lj.prpo.projekt04.api.v1.sources;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Menu", description = "Operations related to managing the restaurant's menu, including adding, updating, and retrieving menu items")
public class MenuSource {
    @GET
    public Response getAllEmployees(){
        return Response
                .ok()
                .header("X-Total-Count", 10)
                .build();
    }
}
