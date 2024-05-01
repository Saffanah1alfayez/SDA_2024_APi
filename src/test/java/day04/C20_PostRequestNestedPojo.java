package day04;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatePojo;
import pojos.BookingPojo;
import pojos.BookingResponcePojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C20_PostRequestNestedPojo extends RestFullBaseUrl{

    /*
    Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
               "firstname": "Jane",
               "lastname": "Doe",
               "totalprice": 111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2018-01-01",
                   "checkout": "2019-01-01"
               },
               "additionalneeds": "Extra pillows please"
           }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
         "bookingid": 2243,
         "booking":{
         "firstname": "Jane",
         "lastname": "Doe",
          "totalprice": 111,
          "depositpaid": true,
          "bookingdates": {
              "checkin": "2018-01-01",
               "checkout": "2019-01-01"
              },
             "additionalneeds": "Extra pillows please"
                                                   }
                                            }

     */

    @Test
    public void test(){

        spec.pathParam("1","booking");

       BookingDatePojo bookingDates =  new BookingDatePojo("2018-01-01" , "2019-01-01");
       BookingPojo payload = new BookingPojo("Jane", "Doe", 111, true, bookingDates ,"Extra pillows please");

              Response response = given(spec).body(payload).post("{1}");
              response.prettyPrint();

             BookingResponcePojo actualData = response.as(BookingResponcePojo.class);

             assertEquals(200 , response.statusCode());
             assertEquals(payload.getFirstname(),actualData.getBooking().getFirstname());
             assertEquals(payload.getLastname(),actualData.getBooking().getLastname());
             assertEquals(payload.getTotalprice(),actualData.getBooking().getTotalprice());
             assertEquals(payload.getDepositpaid(),actualData.getBooking().getDepositpaid());
             assertEquals(bookingDates.getCheckin() , actualData.getBooking().getBookingDatePojo().getCheckin());
             assertEquals(bookingDates.getCheckout() , actualData.getBooking().getBookingDatePojo().getCheckout());
             assertEquals(payload.getAdditionalneeds() , actualData.getBooking().getAdditionalneeds());
    }

}
