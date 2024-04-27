package Homeworks.day03;

import base_urls.petStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Hw_9  extends petStoreBaseUrl {

/*
Write an automation test that will create a 'user'
using the "https://petstore.swagger.io/" document
*/

    @Test
    public void createUserTest(){

       spec.pathParam("first","user");

        Map<String,Object> payload = new HashMap<>();
        payload.put("id",0);
        payload.put("username","thejam");
        payload.put("firstName","jam");
        payload.put("lastName","pink");
        payload.put("email","jam1pink@outlock.com");
        payload.put("password","1234567");
        payload.put("phone","0555555555555");
        payload.put("userStatus",0);

     System.out.println(payload);

        Response response = given(spec).body(payload).post("{first}");

        response.prettyPrint();


        // Assert the response status code is 200
         JsonPath json = response.jsonPath();
         assertEquals(200 , response.statusCode());


        Map<String,Object> actualData = response.as(Map.class); //deselization

        assertEquals(payload.get("id"),actualData.get("id"));
        assertEquals(payload.get("username"),actualData.get("username"));
        assertEquals(payload.get("firstName"),actualData.get("firstName"));

        //assertEquals(response.jsonPath().getMap(""), payload);

    }
}
