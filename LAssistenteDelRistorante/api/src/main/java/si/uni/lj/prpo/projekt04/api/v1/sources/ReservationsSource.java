package si.uni.lj.prpo.projekt04.api.v1.sources;


import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.uni.lj.prpo.projekt04.DTOs.ReservationDTO;
import si.uni.lj.prpo.projekt04.MenuItem;
import si.uni.lj.prpo.projekt04.Reservation;
import si.uni.lj.prpo.projekt04.beans.ReservationBean;
import si.uni.lj.prpo.projekt04.management.ReservationBeanManagement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, PUT, POST, DELETE")
@Tag(name = "Reservations", description = "Operations related to managing reservations")

public class ReservationsSource {

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private ReservationBeanManagement reservationBeanManagement;

    @Context
    private UriInfo uriInfo;

    @GET
    @Operation(summary = "Get all reservations", description = "Returns all reservations")
    @APIResponses({
            @APIResponse(description = "List of reservations retrieved successfully.", responseCode = "200", content = @Content(schema = @Schema(implementation = ReservationDTO.class)))
    })
    public Response getReservations(){

        final var query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Reservation> responseList = null;
        long listSize = 0;
        if(query == null){
            responseList = reservationBean.getAllReservations();
            listSize = reservationBean.getReservationListSize();
        } else {
            responseList = reservationBean.getAllReservations(query);
            listSize = reservationBean.getReservationListSize(query);
        }
        return Response
                .ok(responseList)
                .header("X-Total-Count", listSize)
                .build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get reservation by id", description = "Returns one specific reservation")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = ReservationDTO.class))),
            @APIResponse(description = "Reservation Not found", responseCode = "404")
    })
    public Response getReservation(@PathParam("id") Integer id){

            var reservation = reservationBean.getReservationWithId(id);
            if(reservation != null){

                return Response.ok(reservation)
                        .status(Response.Status.OK)
                        .build();

            } else {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
            }
    }

    @POST
    @Operation(summary = "Add new reservation", description = "Creates a new reservation with the provided details, including customer information, date, time, and number of guests")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    public Response addNewReservation(
            @RequestBody(
                    description = "Reservation details to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ReservationDTO.class))
            )
            ReservationDTO reservation){
        boolean status = reservationBeanManagement.createNewReservation(reservation);
        if(status){
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Update reservation", description = "Updates the reservation with the specified ID. Allows modifying details such as customer name, date, time, number of guests, arrival time etc")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response updateReservation(@PathParam("id") Integer id,
                                      @RequestBody(description = "Reservation details to update", content = @Content(schema = @Schema(implementation = ReservationDTO.class)))
                                      ReservationDTO reservationDTO){
        boolean success = reservationBeanManagement.updateReservation(id, reservationDTO);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE()
    @Path("{id}")
    @Operation(summary = "Delete reservation", description = "Deletes the reservation with the specified ID. Once deleted, the reservation cannot be recovered")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response deleteReservation(@PathParam("id") Integer id){
        boolean success = reservationBean.deleteReservation(id);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
 }
