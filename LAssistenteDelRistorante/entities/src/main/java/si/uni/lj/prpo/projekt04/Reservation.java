package si.uni.lj.prpo.projekt04;


import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@NamedQueries(value =
        {
                @NamedQuery(name = "Reservation.getAll", query = "SELECT r FROM Reservation r"),
                @NamedQuery(name = "Reservation.getReservation", query = "SELECT r FROM Reservation r WHERE r.id = :id")
        })
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customerName;
    private String customerContactInfo;
    private Integer numberOfGuests;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate reservationDate;
    private String specialRequests;
    private String tableAssigned;

    @JsonbDateFormat("yyyy-MM-dd' 'HH:mm")
    private LocalDateTime arrivalTime;
    private String discountCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContactInfo() {
        return customerContactInfo;
    }

    public void setCustomerContactInfo(String customerContactInfo) {
        this.customerContactInfo = customerContactInfo;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }


    public String getTableAssigned() {
        return tableAssigned;
    }

    public void setTableAssigned(String tableAssigned) {
        this.tableAssigned = tableAssigned;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString(){
        String str = "ReservationId "+ id +
                "CustomerName: " + customerName +
                "CustomerContactInfo: " + customerContactInfo +
                "NumberOfGuests: " + numberOfGuests +
                "ReservationDate: " + reservationDate +
                "SpecialRequests: " + tableAssigned +
                "ArrivalTime: " + arrivalTime +
                "DiscountCode: " + discountCode +
                "CreatedAt: " + createdAt;
        return str;
    }



}
