package day03;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static testdata.jsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class C13_PutRequest extends JsonPlaceHolderBaseUrl {

    /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
             "userId": 21,
             "title": "Read Books",
             "completed": false
           }
    When
        I send a PUT request to the URL
    Then
       the status code should be 200
       And the response body should be like:
       {
          "completed": false,
          "title": "Read Books",
          "userId": 21,
          "id": 198
       }

     */

    @Test
    public void putRequestTest() {

        spec.pathParams("first" , "todos"  ,
        "second" , 198);


        Map<String , Object> payLoad = jsonPlaceHolderMapper(21 , "Read Books" , false);


        Response response = given(spec)
                            .body(payLoad)
                            .when()
                            .put("{first}/{second}"); //update

        response.then().statusCode(200);

        response.prettyPrint();
        payLoad.put("id" , 198);

        Map<String , Object> actualData =  response.as(Map.class);
        assertEquals(200,response.statusCode());
        AssertJUnit.assertEquals(payLoad.get("userId"),actualData.get("userId"));
        AssertJUnit.assertEquals(payLoad.get("title"),actualData.get("title"));
        AssertJUnit.assertEquals(payLoad.get("completed"),actualData.get("completed"));
        AssertJUnit.assertEquals(payLoad.get("id"),actualData.get("id"));
    }

}
