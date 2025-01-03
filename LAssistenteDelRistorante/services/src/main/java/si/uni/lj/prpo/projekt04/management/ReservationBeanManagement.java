package si.uni.lj.prpo.projekt04.management;

import si.uni.lj.prpo.projekt04.DTOs.ReservationDTO;
import si.uni.lj.prpo.projekt04.Reservation;
import si.uni.lj.prpo.projekt04.beans.ReservationBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class ReservationBeanManagement {

    private final Logger LOG = Logger.getLogger(ReservationBeanManagement.class.getName());
    @Inject
    private ReservationBean reservationBean;


    //TODO: Dodaj exceptionn sekade kade shto e null
    Optional<Reservation> toReservationEntity(ReservationDTO reservationDto){

        String customerName = reservationDto.getCustomerName();
        String customerContactInfo = reservationDto.getCustomerContactInfo();
        Integer numberOfGuests = reservationDto.getNumberOfGuests();
        LocalDate reservationDate = reservationDto.getReservationDate();
        String specialRequests = reservationDto.getSpecialRequests();
        String tableAssigned = reservationDto.getTableAssigned();
        LocalDateTime arrivalTime = reservationDto.getArrivalTime();
        String discountCode = reservationDto.getDiscountCode();

        if(customerName == null || customerContactInfo == null || numberOfGuests == null ||
                reservationDate == null || tableAssigned == null || arrivalTime ==null){
            LOG.info("Not every parameter is present in ReservationDto");
            return Optional.empty();
        }

        Reservation resEntity = new Reservation();
        resEntity.setCustomerName(customerName);
        resEntity.setCustomerContactInfo(customerContactInfo);
        resEntity.setNumberOfGuests(numberOfGuests);
        resEntity.setReservationDate(reservationDate);
        resEntity.setArrivalTime(arrivalTime);
        resEntity.setTableAssigned(tableAssigned);
        if(specialRequests != null){
            resEntity.setSpecialRequests(specialRequests);
        }
        if(discountCode != null){
            resEntity.setDiscountCode(discountCode);
        }

        return Optional.of(resEntity);
    }


    public boolean createNewReservation(ReservationDTO reservationDto){
        Optional<Reservation> reservaton = toReservationEntity(reservationDto);
        if(reservaton.isPresent()){
            return reservationBean.createNewReservation(reservaton.get());
        } else {
            return false;
        }
    }

    public boolean updateReservation(Integer id, ReservationDTO reservationDTO){
        Reservation currentReservation = reservationBean.getReservationWithId(id);
        if(currentReservation != null){
            if (reservationDTO.getCustomerName() != null) {
                currentReservation.setCustomerName(reservationDTO.getCustomerName());
            }
            if (reservationDTO.getCustomerContactInfo() != null) {
                currentReservation.setCustomerContactInfo(reservationDTO.getCustomerContactInfo());
            }
            if (reservationDTO.getNumberOfGuests() != null) {
                currentReservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
            }
            if (reservationDTO.getReservationDate() != null) {
                currentReservation.setReservationDate(reservationDTO.getReservationDate());
            }
            if (reservationDTO.getTableAssigned() != null) {
                currentReservation.setTableAssigned(reservationDTO.getTableAssigned());
            }
            if (reservationDTO.getArrivalTime() != null) {
                currentReservation.setArrivalTime(reservationDTO.getArrivalTime());
            }
            if (reservationDTO.getSpecialRequests() != null) {
                currentReservation.setSpecialRequests(reservationDTO.getSpecialRequests());
            }
            if (reservationDTO.getDiscountCode() != null) {
                currentReservation.setDiscountCode(reservationDTO.getDiscountCode());
            }

            currentReservation.setUpdatedAt(LocalDateTime.now());

            return reservationBean.updateReservation(currentReservation);
        }

        return false;


    }


}
