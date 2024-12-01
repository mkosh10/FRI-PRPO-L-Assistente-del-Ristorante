package si.uni.lj.prpo.projekt04.api.v1.sources;


import si.uni.lj.prpo.projekt04.DTOs.ReservationDTO;
import si.uni.lj.prpo.projekt04.beans.ReservationBean;
import si.uni.lj.prpo.projekt04.management.ReservationBeanManagement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationsSource {

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private ReservationBeanManagement reservationBeanManagement;

    @GET
    public Response getReservations(){
        return Response
                .ok(reservationBean.getAllReservations())
                .header("X-Total-Count", 10)
                .build();
    }

    @GET
    @Path("{id}")
    public Response getReservation(@PathParam("id") Integer id){

            var reservation = reservationBean.getReservationWithId(id);
            if(reservation != null){

                return Response.ok(reservation)
                        .status(Response.Status.FOUND)
                        .build();

            } else {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .build();
            }
    }

    @POST
    public Response addNewReservation(ReservationDTO reservation){
        boolean status = reservationBeanManagement.createNewReservation(reservation);
        if(status){
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateReservation(@PathParam("id") Integer id,  ReservationDTO reservationDTO){
        boolean success = reservationBeanManagement.updateReservation(id, reservationDTO);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE()
    @Path("{id}")
    public Response deleteReservation(@PathParam("id") Integer id){
        boolean success = reservationBean.deleteReservation(id);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
 }
