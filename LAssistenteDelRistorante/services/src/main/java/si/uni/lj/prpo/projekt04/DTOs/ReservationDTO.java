package si.uni.lj.prpo.projekt04.DTOs;
import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ReservationDTO {
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

    public Integer getNumberOfGuests() {
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



}
