package day07;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatePojo;
import pojos.BookingPojo;

import static day07.C31_createBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C32_getBooking  extends RestFullBaseUrl {

    /*

  Given
        url: "https://restful-booker.herokuapp.com/booking/1
    When
        user send GET request
    Then
        verify status code is 200
    And
        response is like :
        {
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}
     */

    @Test (dependsOnMethods = {"day07.C31_createBooking.test"})
    public void test (){

        //set url
        spec.pathParams("1", "booking" ,
                "2" , bookingid);

        //send req and get res
        Response response = given(spec).get("{1}/{2}");
        response.prettyPrint();

        // Set Expected Data
        BookingDatePojo bookingDates = new BookingDatePojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");


        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getBookingDatePojo().getCheckin(),actualData.getBookingDatePojo().getCheckin());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingDatePojo().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingDatePojo().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
