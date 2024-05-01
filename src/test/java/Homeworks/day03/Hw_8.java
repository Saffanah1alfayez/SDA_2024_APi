package Homeworks.day03;


import base_urls.Hw_8baseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Hw_8 extends Hw_8baseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void test(){

        //Set the Url
        String url = "https://reqres.in/api/users";

        //Set the expected data(Payload) --> Prepare it as Map

        Map<String,Object> payload = new HashMap<>();

        payload.put("name" , "morpheus");
        payload.put("job" , "leader");

        //Send the request and get the response
        Response response = given().contentType(ContentType.JSON).body(payload).when().post(url);
        response.prettyPrint();

        //Do assertion

        JsonPath json = response.jsonPath();
        assertEquals(201 , response.statusCode());
       // assertEquals(payload.get("userId") , json.getInt("userId"));

        Map<String, Object> actualdata = response.as(Map.class);

        assertEquals(payload.get("name"), actualdata.get("name"));
        assertEquals(payload.get("job"), actualdata.get("job"));

       // assertEquals(payload.get("createdAt"), actualdata.get("createdAt"));

    }
}
