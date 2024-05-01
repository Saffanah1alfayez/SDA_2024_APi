package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testdata.jsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C022_ObjectMapperMap extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
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

Note: Use map and POJO seperately
*/


@Test
    public void test() throws JsonProcessingException {

    // set url
    spec.pathParam("1", "todos");

    //set expected data
    //jsonPlaceHolderTestData.jsonPlaceHolderMapper( 55, "Tidy your room", false);


    String expectedDataStr = """
             {
                           "userId": 55,
                           "title": "Tidy your room",
                           "completed": false
                           }""";

    ObjectMapper objectMapper = new ObjectMapper();
   Map<String , Object> payLoad =  objectMapper.readValue(expectedDataStr , Map.class);

   // SEND request and get response
    Response response = given(spec).body(payLoad).when().post("{1}");
    response.prettyPrint();

    //do assertions
    Map<String , Object> actualData = objectMapper.readValue(response.asString() , Map.class);

    assertEquals(201,response.statusCode());
    assertEquals(payLoad.get("userId"),actualData.get("userId"));
    assertEquals(payLoad.get("title"),actualData.get("title"));
    assertEquals(payLoad.get("completed"),actualData.get("completed"));
}
}
