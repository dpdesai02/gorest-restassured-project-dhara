package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }
    //1. Extract the title
    @Test
    public void test01() {
        List<String> title= response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  extract the title :" + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the total number of record
    @Test
    public void test02() {
        Integer size= response.extract().path("size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  Extract the total number of record :" +size);
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the body of 15th record
    @Test
    public void test03() {
        String record= response.extract().path("body[14]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  Extract the body of 15th record :" +record);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the user_id of all the records
    @Test
    public void test04() {
        List<Integer> records= response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Extract the user_id of all the records:" +records);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the title of all the records
    @Test
    public void test05() {
        List<String> listOfTitle= response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Extract the title of all the records:" +listOfTitle);
        System.out.println("------------------End of Test---------------------------");
    }
    // 6. Extract the title of all records whose user_id = 5456
    @Test
    public void test06() {
        String title = response.extract().path("find{it.user_id==2223279}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Extract the title of all records whose user_id :" +title);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Extract the body of all records whose id = 39295
    @Test
    public void test07() {
        List<HashMap<String,?>> value= response.extract().path("findAll{it.id==39295}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the Values of id = '39295'  :" +value);
        System.out.println("------------------End of Test---------------------------");
    }
}
