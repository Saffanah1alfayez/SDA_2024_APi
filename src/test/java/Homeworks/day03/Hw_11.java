package Homeworks.day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Hw_11 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12
    Note: Print using JsonPath: response.jsonPath().prettyPrint();
*/

    @Test
    public void test(){

        //set url
        String url = "https://automationexercise.com/api/productsList";

        //send get response
        Response response = given().when().get(url);


        JsonPath json = response.jsonPath();
        //json.prettyPrint();

        System.out.println("--------------------------------------------------------------------------------");

        //Assert that the number of "Women" user type is 12

        List<String> numberOfWomen = json.getList("products.findAll{it.category.usertype.usertype=='Women'}");
        System.out.println("numberOfWomen = " + numberOfWomen);

        assertEquals(numberOfWomen.size(),12);
    }
}
