package si.uni.lj.prpo.projekt04;


import javax.persistence.*;
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
    private Date reservationDate;
    private String specialRequests;
    private String tableAssigned;
    private Date arrivalTime;

    private String discountCode;
    private Date createdAt;



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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
