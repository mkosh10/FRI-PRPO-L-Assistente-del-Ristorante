package si.uni.lj.prpo.projekt04.beans;

import si.uni.lj.prpo.projekt04.DTOs.ReservationDTO;
import si.uni.lj.prpo.projekt04.Reservation;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.logging.Logger;

@RequestScoped
public class ReservationBean {
    @PersistenceContext(unitName = "l-assistente-del-ristorante-jpa")
    private EntityManager em;


    private final Logger LOG = Logger.getLogger(ReservationBean.class.getName());

    public static final List<Reservation> testList = new ArrayList<Reservation>();

    static {
        Reservation reservation1 = new Reservation();
        reservation1.setCustomerName("John Doe");
        reservation1.setCustomerContactInfo("john.doe@example.com");
        reservation1.setNumberOfGuests(4);
        reservation1.setReservationDate(new Date()); // Current date
        reservation1.setSpecialRequests("Window seat");
        reservation1.setTableAssigned("Table 12");
        reservation1.setArrivalTime(new Date(System.currentTimeMillis() + 3600 * 1000)); // 1 hour from now
        reservation1.setDiscountCode("DISCOUNT10");
        reservation1.setCreatedAt(new Date());


        Reservation reservation2 = new Reservation();
        reservation2.setCustomerName("Jane Smith");
        reservation2.setCustomerContactInfo("jane.smith@example.com");
        reservation2.setNumberOfGuests(2);
        reservation2.setReservationDate(new Date()); // Current date
        reservation2.setSpecialRequests("No peanuts due to allergy");
        reservation2.setTableAssigned("Table 5");
        reservation2.setArrivalTime(new Date(System.currentTimeMillis() + 7200 * 1000)); // 2 hours from now
        reservation2.setDiscountCode("WELCOME20");
        reservation2.setCreatedAt(new Date());

        testList.add(reservation1);
        testList.add(reservation2);
    }

    Optional<Reservation> toReservationEntity(ReservationDTO reservationDto){

        String customerName = reservationDto.getCustomerName();
        String customerContactInfo = reservationDto.getCustomerContactInfo();
        Integer numberOfGuests = reservationDto.getNumberOfGuests();
        Date reservationDate = reservationDto.getReservationDate();
        String specialRequests = reservationDto.getSpecialRequests();
        String tableAssigned = reservationDto.getTableAssigned();
        Date arrivalTime = reservationDto.getArrivalTime();
        String discountCode = reservationDto.getDiscountCode();
        Date createdAt = reservationDto.getCreatedAt();

        if(customerName == null || customerContactInfo == null || numberOfGuests == null ||
        reservationDate == null || tableAssigned == null || arrivalTime ==null || createdAt == null){
            //TODO: Dodaj exceptionn
            LOG.info("Not every parameter is present in ReservationDto");
            return Optional.empty();
        }

        Reservation resEntity = new Reservation();
        //TODO: izbrisi po dodajanu baze
//        resEntity.setId(100);
        resEntity.setCustomerName(customerName);
        resEntity.setCustomerContactInfo(customerContactInfo);
        resEntity.setNumberOfGuests(numberOfGuests);
        resEntity.setReservationDate(reservationDate);
        resEntity.setArrivalTime(arrivalTime);
        resEntity.setTableAssigned(tableAssigned);
        resEntity.setCreatedAt(createdAt);
        if(specialRequests != null){
            resEntity.setSpecialRequests(specialRequests);
        }
        if(discountCode != null){
            resEntity.setDiscountCode(discountCode);
        }

        return Optional.of(resEntity);
    }

    public List<Reservation> getAllReservations(){
        TypedQuery<Reservation> query =em.createNamedQuery("Reservation.getAll", Reservation.class);
        return  query.getResultList();
    }

    public Reservation getReservationWithId(Integer id){

        for(Reservation res : testList){
//            if(Objects.equals(res.getId(), id)){
                return res;
//            }
        }
        return null;
    }

    public boolean addNewReservation(ReservationDTO reservation){
        Optional<Reservation> reservationEntity = toReservationEntity(reservation);
        if(reservationEntity.isPresent()){
            LOG.info("ReservationEntity is present");
            Reservation resEntity = reservationEntity.get();
//            resEntity.setId(testList.size() + 1);
            testList.add(resEntity);
            return true;
        } else  {
            return false;
        }
    }

    public boolean deleteReservation(int reservationId){
        Reservation reservation= getReservationWithId(reservationId);

        if(reservation != null){
            testList.remove(reservation);
            return true;
        } else {
            LOG.info("Reservation not present");
            return false;
        }
    }


}
