package day03;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.jsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C12_PostRequestTestData extends JsonPlaceHolderBaseUrl {

    /*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
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
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/

    @Test
    public void postRequestTest() {

        //Set the Url
        spec.pathParam("first" , "todos");


        //Set the expected data(Payload) --> Prepare it as Map

//        2)  {
//                    "userId": 55,
//                    "title": "Tidy your room",
//                    "completed": false
//        }


        Map<String , Object> payLoad = jsonPlaceHolderTestData
                .jsonPlaceHolderMapper(55, "Tidy your room" , false);
        System.out.println("payLoad.get(\"userId\") = " + payLoad.get("userId"));


        //Send the request and get the response

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        //Do assertion

        JsonPath json = response.jsonPath();
        assertEquals(201 , response.statusCode());
        assertEquals(payLoad.get("userId") , json.getInt("userId"));

        Map<String,Object> actualData = response.as(Map.class); //deselization

        assertEquals(201,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));



    }

}
