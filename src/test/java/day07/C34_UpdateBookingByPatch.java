package day07;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.util.Map;

import static day07.C31_createBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C34_UpdateBookingByPatch extends RestFullBaseUrl {

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
        user send patch request
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
    @Test(dependsOnMethods = "day07.C31_createBooking.test")
    public void UpdateByPatchTest(){

        //set url
        spec.pathParams("1" , "booking" , "2" , bookingid);

        //set expected data

        String payloydStr = """
                {
                    "firstname" : "Tom",
                    "lastname" : "Hanks"
                }""";

        //sent req and get res

        Map<String , Object> payload = convertJsonToJava(payloydStr , Map.class);


        Response response = given(spec).body(payload).when().patch("{1}/{2}");
        response.prettyPrint();


        //do assertion

        Map<String , Object> actualData =  convertJsonToJava(response.asString(), Map.class);

        response.then().statusCode(200);
        assertEquals(payload.get("firstname"),actualData.get("firstname"));
        assertEquals(payload.get("lasttname"),actualData.get("lasttname"));

    }
}
