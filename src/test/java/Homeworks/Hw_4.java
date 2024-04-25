package day01;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.internal.print.ResponsePrinter.print;
import static org.hamcrest.Matchers.*;

public class Hw_4 {

    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */
    @Test
    public void test() {

        String url = "https://reqres.in/api/users/23";
        Response response = given().get(url);
        response.prettyPrint();

        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server" , "cloudflare")
                .body("[0]" ,is(nullValue()));


                //.body("{}");
                //.body("" ,isEmpty());
                //.body(emptyOrNullString());
    }
}
