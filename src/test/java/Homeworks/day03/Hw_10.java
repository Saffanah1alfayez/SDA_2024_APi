package Homeworks.day03;

import base_urls.petStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class Hw_10 extends petStoreBaseUrl {

    /*
    Using the https://petstore.swagger.io/ document,
    write an automation test that finds the number of "pets" with
    the status "available" and asserts that there are more than 100.
     */

    @Test
    public void getPets(){


        //finds the number of "pets" with the status "available"

        spec.pathParams("1" , "pet"
                ,"2" , "findByStatus");

        Response response = given(spec)
                           .queryParam("status", "available").get("{1}/{2}");

        //response.prettyPrint();

        //asserts that there are more than 100.

        int numberOfPets = response.jsonPath().getList("").size();
        System.out.println("numberOfPets = " + numberOfPets);
        assertTrue(numberOfPets > 100 , "Number of available pet more than 100 ");
    }

}
