package day06;

import base_urls.DummyResBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class C28_RevisionExample03 extends DummyResBaseUrl {

/*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
 */

    @Test
    public void test(){
       //set url
       spec.pathParam("1","employees") ;

        //sent req and get res

        Response response = given(spec).when().get("{1}");
        response.prettyPrint();

        //do assertion

//        Status code is 200
        response.then().statusCode(200)
                .body("data.id",hasSize(24))
                .body("data.employee_name",hasItems("Tiger Nixon" , "Garrett Winters"));
//
//        There are 24 employees
       JsonPath json = response.jsonPath();
       int numOfId = json.getList("data.id").size();
       assertEquals(24 , numOfId);


//        "Tiger Nixon" and "Garrett Winters" are among the employees
        List<String > names = json.getList("data.employee_name");
        assertTrue(names.contains("Tiger Nixon"));
        assertTrue(names.contains("Garrett Winters"));

//        The greatest age is 66
        List<Integer> ageList =  json.getList("data.employee_age");
        Integer maxAge = 0; // can we make it "int"
        for(Integer age: ageList){
            if(age>maxAge){
                maxAge=age;
            }
        }
        Integer expectedAge = 66;
        assertEquals(expectedAge , maxAge);


     // The name of the lowest age is "Tatyana Fitzpatrick"
        int minAge = 200;
        for (Integer age:ageList){
            if(age<minAge){
                minAge=age;
            }
        }

        Object youngestEmployee = json.getList("data.findAll{it.employee_age=="+minAge+"}.employee_name").get(0);
        assertEquals("Tatyana Fitzpatrick" , youngestEmployee);

   //Total salary of all employees is 6,644,770 AS TBQ36489 sq

        List<Integer> salaryList = json.getList("data.employee_salary");
        int totalSalary = 0;
        for(Integer salary: salaryList) {

            //totalSalary = totalSalary + salary;
            totalSalary+=salary;
        }

        assertEquals(6644770,totalSalary);

    }


}
