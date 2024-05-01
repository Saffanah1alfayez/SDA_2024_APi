package day04;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class C21_DeleteRequest extends JsonPlaceHolderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send DELETE Request to the Url
    Then
        Status code is 200
        And Response body is { }
*/

    @Test
    public void test(){

        spec.pathParams("1", "todos" , "2" , 198);

        Response response = given(spec).when().delete("{1}/{2}");


        //do assertion

        String responseStr = response.asString();
        System.out.println("responseStr = " + responseStr);

        assertEquals(200,response.statusCode());
        assertTrue(responseStr.equals("{}"));

    }
}
