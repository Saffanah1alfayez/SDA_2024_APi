package day07;

import base_urls.RestFullBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static day07.C31_createBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C35_DeleteBooking extends RestFullBaseUrl {

    /*
Given
    url: "https://restful-booker.herokuapp.com/booking/:id
When
    user send DELETe request
Then
    verify status code is 201
And
    response is like : Created


 */


    @Test(dependsOnMethods = "day07.C31_createBooking.test")
    public void test(){

        //set url
        spec.pathParams("1","booking" , "2" , bookingid);

        //set expected data

        String ecpStr = "Created";

        //send req and get res

        Response response = given(spec).when().delete("{1}/{2}");
        response.prettyPrint();


        //do assertion

        assertEquals(201 , response.statusCode());

        assertEquals(ecpStr,response.asString());

    }
}
