package day03;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static testdata.jsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class C14_PatchReqest extends JsonPlaceHolderBaseUrl {
    
     /*
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2) {
                 "title": "Read Books"
               }
        When
            I send PATCH Request to the Url
        Then
           Status code is 200
           And response body is like  {
                                            "userId": 10,
                                            "id": 198,
                                            "title": "Read Books",
                                            "completed": true
                                        }
    */
    
    @Test
    public void patchTest() {
        
        //set url
        
        spec.pathParams("first", "todos" , "second" , 198);
        
        //set expected data
        
        Map<String , Object> payLoad = jsonPlaceHolderMapper(null , "Read Books" , null );
        System.out.println("payLoad = " + payLoad);

        Map<String , Object> expectedData = jsonPlaceHolderMapper(10 , "Read Books" , true );
         expectedData.put("id" , 198);
        System.out.println("expectedData = " + expectedData);

        // send request and get responce

        Response response = given(spec).body(payLoad).when().patch("{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(Map.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
    }
}
