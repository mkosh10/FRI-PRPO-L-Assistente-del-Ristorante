package si.uni.lj.prpo.projekt04.api.v1.sources;


import si.uni.lj.prpo.projekt04.DTOs.ReservationDTO;
import si.uni.lj.prpo.projekt04.Reservation;
import si.uni.lj.prpo.projekt04.beans.ReservationBean;

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

    @GET
    public Response getReservations(){
        System.out.println("bla");

        return Response
                .ok(reservationBean.getAllReservations())
                .header("X-Total-Count", 10)
                .build();
    }

    @GET
    @Path("{id}")
    public Response getReservation(@PathParam("id") Integer id){
//        try {
//            ReservationEntity reservation = reservationBean.getReservationWithId(id);
//            return Response.ok(reservation)
//                    .status(Response.Status.FOUND)
//                    .build();
//
//        } catch(Exception e){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
//        }
    }

    @POST
    public Response addNewReservation(ReservationDTO reservation){
        boolean status = reservationBean.addNewReservation(reservation);
        // TODO: Dodaj boljso izjemo ce mogoce fromDTO to Entity ne gre (boljse v Optional to doddaj)
        if(status){
            return Response.status(Response.Status.CREATED).build();
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
