package day05;

import base_urls.RestFullBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;
import pojos.BookingResponcePojo;

import java.awt.print.Book;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C25_NestedPostObjectMapper extends RestFullBaseUrl {

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
       And response body should be like
       {
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
    public void test() throws JsonProcessingException {
    // set url

    spec.pathParams("1", "booking");

    // set esp data
    String exp = """
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
    //BookingPojo payLoud = objectMapper.readValue(exp, BookingPojo.class);
    BookingPojo payLoud = convertJsonToJava(exp , BookingPojo.class); // using

    //sed req and get res

    Response response = given(spec).body(payLoud).post("{1}");
    response.prettyPrint();

    //do assertion

    BookingResponcePojo actualData =convertJsonToJava(response.asString(), BookingResponcePojo.class);

    assertEquals( 200 , response.statusCode());
    assertEquals(payLoud.getFirstname(),actualData.getBooking().getFirstname());
    assertEquals(payLoud.getLastname(),actualData.getBooking().getLastname());
    assertEquals(payLoud.getTotalprice(),actualData.getBooking().getTotalprice());
    assertEquals(payLoud.getDepositpaid(),actualData.getBooking().getDepositpaid());
    assertEquals(payLoud.getBookingDatePojo().getCheckin(),actualData.getBooking().getBookingDatePojo().getCheckin());
    assertEquals(payLoud.getBookingDatePojo().getCheckout(),actualData.getBooking().getBookingDatePojo().getCheckout());
    assertEquals(payLoud.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());


































}









}
