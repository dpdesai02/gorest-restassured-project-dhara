package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()

                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);

    }
    //----------------------------------------all dynemic data keep changing names and Id---------------------------------------//
//    1. Verify the if the total record is 20
    @Test
    public void verifyTotal(){
        response.body("size()",equalTo(20));
    }

    //     2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    //provided request are not foundVe so verify the if the name of id = 2272617 is equal to ”Harinakshi Joshi”
    @Test
    public void verifyName(){
        response.body("findAll{it.id==2272617}.name",hasItem("Harinakshi Joshi"));
    }
    //  3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    //  3. Check the single ‘Name’ in the Array list (Ganesh Guha)
    @Test
    public void verifySingleName(){

        response.body("[2].name",equalTo("Ganesh Guha"));

    }
    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. BodhanGuha, Karthik Dubashi IV)
    @Test
    public void verifyMultipleName(){

        response.body("name",hasItems("Chandrakin Deshpande","Deepankar Asan","Ganesh Guha","Bhaumik Varma","Gorakhnath Adiga","Esha Bharadwaj","Sen. Apsara Dubashi","Digambar Chaturvedi PhD","Aasa Kaniyar II", "Ganaka Prajapat DVM", "Smriti Pilla", "Bhaumik Bhattacharya Esq.","Esha Mukhopadhyay I", "Gov. Balaaditya Mehrotra","Himadri Naik","Harinakshi Joshi", "Pranay Reddy", "Bhilangana Mehra", "Kashyap Guha", "Chandraayan Chopra"));
//
    }

    //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void verifyEmailId(){
        response.body("findAll{it.id==2272635}.email",hasItem("deshpande_chandrakin@dibbert.test"));


    }
    //  6. Verify the status is “Active” of user name is “Shanti Bhat V”
    //  6. Verify the status is “Active” of user name is “Chandrakin Deshpande”
    @Test
    public void verifystatus(){
        response.body("findAll{it.name=='Chandrakin Deshpande'}.status",hasItem("active"));


    }
    // 7. Verify the Gender = male of user name is “Niro Prajapat”
    // 7. Verify the Gender = male of user name is “Deepankar Asan”
    @Test
    public void verifyGender(){
        response.body("findAll{it.name=='Deepankar Asan'}.gender",hasItem("male"));


    }

}
