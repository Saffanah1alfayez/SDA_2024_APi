package day01;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class C01_RequestAndResponse {

    public static void main(String[] args) {

        /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET Request to the url
    Then
        Print Status Code (should be 200)
    And
        Print Content Type (should be JSON)
    And
        Print Status Line (should be HTTP/1.1 200 OK)
    And
        Print Connection and Date headers on console
    And
        Print all headers on console
*/
        String url = "https://restful-booker.herokuapp.com/booking";
        Response response = given().get(url); // all data i need is in response
        //Response response1 = RestAssured.get(url); // all data i need is in response

        //how to print response
        response.prettyPrint();

        //System.out.println("response = " + response); //it print referneece of the response

        // how to print status code

        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        //how to print  status line

        String statusLine = response.statusLine();
        System.out.println("statusLine = " + statusLine);

        //how to print contact type

        String contactType = response.contentType();
        System.out.println("contactType = " + contactType);


        //how can i get value from headers
        String conn = response.header("Connection");
        System.out.println("conn = " + conn);


        String date = response.header("Date");
        System.out.println("date = " + date);

        // how to get all headers

        Headers headers = response.headers();
        System.out.println("headers = " + headers);

        //how to get response time
        System.out.println("response time() = " + response.time());

    }
}
