package day04;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingDatePojo;
import pojos.BookingPojo;
import testdata.RestfulTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C19_GetRequestNestedMapPojo extends RestFullBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/243
    When
        I send GET Request to the url
    Then
        Status code should be 200
        Response body should be like that;
            {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
 */

    @Test
    public void test(){

        //set url
        spec.pathParams("1" , "booking" , "2" , 451);


        //set expected data

        BookingDatePojo bookingDatePojo = new BookingDatePojo("2018-01-01" , "2019-01-01");
        BookingPojo expectedData = new BookingPojo("John", "Smith" , 111 , true , bookingDatePojo ,"Breakfast");

        //send request
        Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();

        //do assertion

       BookingPojo actualData =  response.as(BookingPojo.class);

       assertEquals(200 , response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingDatePojo(),actualData.getBookingDatePojo());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
