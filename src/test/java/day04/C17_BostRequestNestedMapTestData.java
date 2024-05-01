package day04;

import base_urls.RestFullBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.RestfulTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class C17_BostRequestNestedMapTestData extends RestFullBaseUrl {
    /*
    Given
        1) https://restful-booker.herokuapp.com/booking
        2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 15,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-03-07",
                "checkout": "2024-09-25"
            },
            "additionalneeds": "Lunch"
           }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
                                            "bookingid": 2243,
                                            "booking": {
                                                "firstname": "John",
                                                "lastname": "Doe",
                                                "totalprice": 471,
                                                "depositpaid": true,
                                                "bookingdates": {
                                                    "checkin": "2023-03-07",
                                                    "checkout": "2024-09-25"
                                                },
                                                "additionalneeds": "Lunch"
                                            }
                                        }
 */

    @Test
    public void postNestedMapTest() {

        //set url
        spec.pathParam("first", "booking")
                .contentType("application/json");

        // set expected data
        // while dealing with nested str from inner map structure

        Map<String , Object> bookingDate = RestfulTestData.bookingDatesMapper("2023-03-07","2024-09-25");
        Map<String,Object> payload = RestfulTestData.bookingMapper("John","Doe" , 15 , true , bookingDate , "additionalneeds");

        //send request and
        Response response = given(spec).body(payload).post("{first}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .body("booking.firstname" ,equalTo(payload.get("firstname")));


        Map<String , Object> actualData = response.as(Map.class);

        Object checkin =(((Map)((Map)(actualData.get("booking"))).get("bookingdates")).get("checkin"));
        assertEquals(200,response.statusCode());

        assertEquals(payload.get("firstname"),((Map)(actualData.get("booking"))).get("firstname"));
        assertEquals(payload.get("lastname"), ((Map)(actualData.get("booking"))).get("lastname") );
        assertEquals(payload.get("totalprice"), ((Map)(actualData.get("booking"))).get("totalprice") );
        assertEquals(payload.get("depositpaid"), ((Map)(actualData.get("booking"))).get("depositpaid") );

        assertEquals(bookingDate.get("checkin"), checkin);
        assertEquals(bookingDate.get("checkin"), (((Map)((Map)(actualData.get("booking"))).get("bookingdates")).get("checkin")));

        assertEquals(bookingDate.get("checkout"), (((Map)((Map)(actualData.get("booking"))).get("bookingdates")).get("checkout")));
        assertEquals(payload.get("additionalneeds"), ((Map)(actualData.get("booking"))).get("additionalneeds") );

        JsonPath json = response.jsonPath();
        assertEquals(payload.get("lastname"), json.getString("booking.lastname") );
    }
}