package day04;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.RestfulTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C18_GetRequestNestedMap extends RestFullBaseUrl {

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
        spec.pathParams("1" , "booking" , "2" , 455);

        //set expected data
        Map<String , Object>  bookingDates = RestfulTestData.bookingDatesMapper("2018-01-01" , "2019-01-01");
        Map<String , Object> expectedData = RestfulTestData.bookingMapper("Josh","Allen",111 ,true , bookingDates , "super bowls");

        //send request
        Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();

        //do assertion
        Map<String , Object> actualdata = response.as(Map.class);

        assertEquals(200 , response.statusCode());
        assertEquals(expectedData.get("firstname"),actualdata.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualdata.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualdata.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualdata.get("depositpaid"));
        assertEquals(bookingDates.get("checkin"),((Map)actualdata.get("bookingdates")) .get("checkin"));
        assertEquals(bookingDates.get("checkout"),((Map)actualdata.get("bookingdates")) .get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualdata.get("additionalneeds"));
    }
}