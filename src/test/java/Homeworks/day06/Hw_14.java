package Homeworks.day06;

import Homeworks.day06.pojo.Bookingdates;
import Homeworks.day06.pojo.ResponseBooking;
import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Hw_14 extends RestFullBaseUrl {

    /*
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
     */

    @Test
    public void test(){

        //set url
        spec.pathParam("1" , "booking");

        // set expected data

        Bookingdates bookingdates = new Bookingdates("2018-01-01" , "2019-01-01");
        ResponseBooking payload = new ResponseBooking("Jim","Breakfast",bookingdates,111,true ,"Brown");

        //sent req and get res

        Response response = given(spec).body(payload).post("{1}");
        response.prettyPrint();

        //do assertion

        ResponseBooking actualData = response.as(ResponseBooking.class);
        response.then().statusCode(200);

        assertEquals(payload.getFirstname(),actualData.getFirstname());
        assertEquals(payload.getLastname(),actualData.getLastname());
        assertEquals(payload.getTotalprice(),actualData.getTotalprice());
        assertEquals(payload.getTotalprice(),actualData.getTotalprice());
        assertEquals(payload.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(payload.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(payload.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(payload.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
