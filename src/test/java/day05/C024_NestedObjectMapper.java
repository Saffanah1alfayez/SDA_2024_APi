package day05;

import base_urls.RestFullBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C024_NestedObjectMapper extends RestFullBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/466
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
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
*/
    @Test
    public void test() throws JsonProcessingException {

        //set url

        spec.pathParams("1","booking" , "2", 15);

        // set expected data

        String expectedSrt = """
                {
                                "firstname": "Jane",
                                "lastname": "Doe",
                                "totalprice": 111,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                                },
                                "additionalneeds": "Extra pillows please"
                            }""";

        ObjectMapper objectMapper = new ObjectMapper();
        BookingPojo expectedData = objectMapper.readValue(expectedSrt , BookingPojo.class);

        // send req and get res

        Response response = given(spec).get("{1}/{2}"); // selerization
        response.prettyPrint();

        // do assrtion

        BookingPojo actualdata = objectMapper.readValue(response.asString(),BookingPojo.class); // deselerztion

        assertEquals(expectedData.getFirstname(),actualdata.getFirstname());
        assertEquals(expectedData.getLastname(),actualdata.getLastname());
        assertEquals(expectedData.getTotalprice(),actualdata.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualdata.getDepositpaid());
        assertEquals(expectedData.getBookingDatePojo().getCheckin(),actualdata.getBookingDatePojo().getCheckin());
        assertEquals(expectedData.getBookingDatePojo().getCheckout(),actualdata.getBookingDatePojo().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualdata.getAdditionalneeds());

    }
}
