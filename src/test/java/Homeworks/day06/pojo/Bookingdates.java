package Homeworks.day06.pojo;

public class Bookingdates{
	private String checkin;
	private String checkout;

	public Bookingdates() {
	}

	public Bookingdates(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public void setCheckin(String checkin){
		this.checkin = checkin;
	}

	public String getCheckin(){
		return checkin;
	}

	public void setCheckout(String checkout){
		this.checkout = checkout;
	}

	public String getCheckout(){
		return checkout;
	}

	@Override
 	public String toString(){
		return 
			"Bookingdates{" + 
			"checkin = '" + checkin + '\'' + 
			",checkout = '" + checkout + '\'' + 
			"}";
		}
}
