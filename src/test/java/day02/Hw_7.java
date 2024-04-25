package day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

public class Hw_7 {
     /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void test()
    {
        String url = " https://reqres.in/api/unknown/";
        Response response = given().when().get(url);

        response
                .then()
                .statusCode(200);

        JsonPath json = response.jsonPath();

        List<String> listOfPantone_Values = json.getList("data.pantone_value");
        System.out.println("list Of Pantone_Values = " + listOfPantone_Values);

        List<Integer> listId = json.getList("data.id");
        System.out.println("listId = " + listId);

        System.out.println("-----------------------------------------------------");

        int idGreaterThan3 = 0;

        for (int w:listId) {
            if(w>3){
                System.out.println(w);
                idGreaterThan3++;
            }
        }
         assertTrue(idGreaterThan3==3);

        System.out.println("-----------------------------------------------------");




       List<String> namesLessThan3 = json.getList("data.findAll { it.id < 3 }.name");
        System.out.println("namesLessThan3 = " + namesLessThan3);
        assertTrue(namesLessThan3.size() == 2);
        



    }
}
