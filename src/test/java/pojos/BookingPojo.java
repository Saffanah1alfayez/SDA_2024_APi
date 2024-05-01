package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPojo {

    private String firstname;
    private String lastname ;
    private Integer totalprice ;
    private Boolean depositpaid;
    private BookingDatePojo BookingDatePojo ;
    private String additionalneeds;

    public BookingPojo() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public pojos.BookingDatePojo getBookingDatePojo() {
        return BookingDatePojo;
    }

    public void setBookingDatePojo(pojos.BookingDatePojo bookingDatePojo) {
        BookingDatePojo = bookingDatePojo;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public BookingPojo(String firstname, String lastname, Integer totalprice, Boolean depositpaid, pojos.BookingDatePojo bookingDatePojo, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        BookingDatePojo = bookingDatePojo;
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", BookingDatePojo=" + BookingDatePojo +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }
}
