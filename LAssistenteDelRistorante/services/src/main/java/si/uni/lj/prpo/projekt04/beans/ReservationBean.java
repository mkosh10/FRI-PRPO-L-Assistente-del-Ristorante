package si.uni.lj.prpo.projekt04.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.uni.lj.prpo.projekt04.Employee;
import si.uni.lj.prpo.projekt04.Reservation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;

@RequestScoped
public class ReservationBean {
    @PersistenceContext(unitName = "l-assistente-del-ristorante-jpa")
    private EntityManager em;


    private final Logger LOG = Logger.getLogger(ReservationBean.class.getName());

    public List<Reservation> getAllReservations(){
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.getAll", Reservation.class);
        return  query.getResultList();
    }

    public int getReservationListSize(){
        return getAllReservations().size();
    }

    public List<Reservation> getAllReservations(QueryParameters query) {
        return JPAUtils.queryEntities(this.em, Reservation.class, query);
    }

    public long getReservationListSize(QueryParameters query) {
        return JPAUtils.queryEntitiesCount(this.em, Reservation.class, query);
    }

    public Reservation getReservationWithId(Integer id){
        try {
            TypedQuery<Reservation> query = em.createNamedQuery("Reservation.getReservation", Reservation.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean createNewReservation(Reservation reservation){
        try {
            em.persist(reservation);
            return true;

        } catch(Exception e){
            LOG.info("Can't create reservation" + e );
            return false;
        }

    }

    @Transactional
    public boolean updateReservation(Reservation reservation){
        try {
            em.merge(reservation);
            return true;
        } catch(Exception e) {
            LOG.info("Can't update reservation" + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteReservation(int reservationId){
        Reservation reservation= getReservationWithId(reservationId);
        if(reservation != null){
            try {
                em.remove(reservation);
                return true;
            } catch(Exception e){
                LOG.info("Exception while trying to delete reservation with id " + reservationId  + e.getMessage());
                return false;
            }
        } else {
            LOG.info("Can't find reservation with id " + reservationId);
            return false;
        }

    }


}
