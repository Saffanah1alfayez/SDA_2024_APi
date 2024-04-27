package day03;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C15_PostRequestPojo extends JsonPlaceHolderBaseUrl {

    /*
     Given
        https://jsonplaceholder.typicode.com/todos
        {
        "userId": 55,
        "title": "Tidy your room",
        "completed": false
        }
    When
        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title":"Tidy your room" ,
                                "completed": false,
                                "id": 201
                                }
*/

    @Test
    public void PostRequestPojoTest(){

        //set url

        spec.pathParam("first" , "todos");

        //set expected data
        JsonPlaceHolderPojo payload =  new JsonPlaceHolderPojo(55, "Tidy your room" , false);
        System.out.println("payload.getTitle() = " + payload.getTitle());
        System.out.println(payload);

        // send request and ger response

        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        // do assertion
        JsonPlaceHolderPojo actualData =  response.as(JsonPlaceHolderPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(payload.getUserId() , actualData.getUserId());
        assertEquals(payload.getTitle() , actualData.getTitle());
        assertEquals(payload.getCompleted() , actualData.getCompleted());
    }























}
