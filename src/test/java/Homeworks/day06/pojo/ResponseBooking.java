package Homeworks.day06.pojo;

public class ResponseBooking {
	private String firstname;
	private String additionalneeds;
	private Bookingdates bookingdates;
	private Integer totalprice;
	private Boolean depositpaid;
	private String lastname;

	public ResponseBooking() {
	}

	public ResponseBooking(String firstname, String additionalneeds, Bookingdates bookingdates, Integer totalprice, Boolean depositpaid, String lastname) {
		this.firstname = firstname;
		this.additionalneeds = additionalneeds;
		this.bookingdates = bookingdates;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.lastname = lastname;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setAdditionalneeds(String additionalneeds){
		this.additionalneeds = additionalneeds;
	}

	public String getAdditionalneeds(){
		return additionalneeds;
	}

	public void setBookingdates(Bookingdates bookingdates){
		this.bookingdates = bookingdates;
	}

	public Bookingdates getBookingdates(){
		return bookingdates;
	}

	public void setTotalprice(Integer totalprice){
		this.totalprice = totalprice;
	}

	public Integer getTotalprice(){
		return totalprice;
	}

	public void setDepositpaid(Boolean depositpaid){
		this.depositpaid = depositpaid;
	}

	public Boolean getDepositpaid(){
		return depositpaid;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"firstname = '" + firstname + '\'' + 
			",additionalneeds = '" + additionalneeds + '\'' + 
			",bookingdates = '" + bookingdates + '\'' + 
			",totalprice = '" + totalprice + '\'' + 
			",depositpaid = '" + depositpaid + '\'' + 
			",lastname = '" + lastname + '\'' + 
			"}";
		}
}
