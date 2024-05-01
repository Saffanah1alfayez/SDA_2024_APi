package day05;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class C26_task extends JsonPlaceHolderBaseUrl {
    /*
   Given
       https://jsonplaceholder.typicode.com/todos
   When
  I send a GET request to the Url
And
    Accept type is "application/json"
Then
    HTTP Status Code should be 200
And
       There must be a todo like:
           {
               "userId": 1,
               "id": 4,
               "title": "et porro tempora",
               "completed": true
           }
    */

    @Test
    public void test() {

        // set url
        spec.pathParam("1" , "todos");

        //set exp data
        String expTodo = """
                {
                               "userId": 1,
                               "id": 4,
                               "title": "et porro tempora",
                               "completed": true
                           }""";


        Map<String , Object> exTodomap = convertJsonToJava(expTodo , Map.class);

        Response response = given(spec).get("{1}");

        //do assertion

        //response.then().statusCode(200);

        JsonPath json = response.jsonPath();

//        List<Integer> todo = json.getList("findAll{it.id==4}");
//        System.out.println("id = " + todo);
//
//        Map<String , Object> todoMap =  (Map)todo;

        Object userIs = json.getList("findAll{it.id==4.userId").get(0);
        Object title = json.getList("findAll{it.id==4").get(0);
        Object id = json.getList("findAll{it.id==4").get(0);
        Object completed = json.getList("findAll{it.id==4").get(0);

    }
}
