package day07;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;

import static day07.C31_createBooking.bookingid;
import static day07.C31_createBooking.payload;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C33_UpdateBooking extends RestFullBaseUrl {

     /*
    Given
        url: "https://restful-booker.herokuapp.com/booking
    And
        body:     {
                    "firstname" : "James",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Lunch"
                }

    When
        user send put request
    Then
        verify status code is 200
    And
        response is like :
                            {
                        "firstname" : "James",
                        "lastname" : "Brown",
                        "totalprice" : 111,
                        "depositpaid" : true,
                        "bookingdates" : {
                            "checkin" : "2018-01-01",
                            "checkout" : "2019-01-01"
                        },
                        "additionalneeds" : "Lunch"
                    }


     */

    @Test(dependsOnMethods = {"day07.C31_createBooking.test"} )
    public void test(){

        //set url
        spec.pathParams("1" , "booking" , "2" , bookingid);

        //set expected data
        //System.out.println("payload.getFirstname() = " + payload.getFirstname());

        payload.setFirstname("James");
        payload.setAdditionalneeds("Lunch");

        //sent req and get res

        Response response = given(spec).body(payload).when().put("{1}/{2}");
        response.prettyPrint();

        response.then().statusCode(200);

        BookingPojo actualData = response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payload.getFirstname(),actualData.getFirstname());
        assertEquals(payload.getLastname(),actualData.getLastname());
        assertEquals(payload.getTotalprice(),actualData.getTotalprice());
        assertEquals(payload.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(payload.getBookingDatePojo().getCheckin(),actualData.getBookingDatePojo().getCheckin());
        assertEquals(payload.getBookingDatePojo().getCheckout(),actualData.getBookingDatePojo().getCheckout());
        assertEquals(payload.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}
