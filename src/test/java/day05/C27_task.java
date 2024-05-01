package day05;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class C27_task {

    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Abhaidev Kaur", "Fr. Deenabandhu Adiga", "Akshita Singh DC" are among the users -> Data may change
        And
            The female users are less than or equals to male users -> Data may change
*/


    @Test
    public void test() {

        Response response = given().when().get("https://gorest.co.in/public/v1/users");
       // response.prettyPrint();

        response
                .then()
                .body("meta.pagination.limit" , equalTo(10))
                .body("meta.pagination.links.current" , equalTo("https://gorest.co.in/public/v1/users?page=1"))
                .body("data.name",hasSize(10))
                .body("data.status" , hasItem("active"))
                .body("data.name",hasItems("Devagya Joshi" , "Karthik Gandhi" , "Lakshminath Rana"));

        JsonPath json =response.jsonPath();

        List<String> fgender = json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println("fgender = " + fgender);

        List<String> mgender = json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("mgender = " + mgender);

        assertTrue(fgender.size() >= mgender.size());


        //assertFalse(fgender.size() >= mgender.size());

    }

}
