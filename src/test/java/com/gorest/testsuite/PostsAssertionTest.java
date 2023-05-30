package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()

                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);

    }
    // 1. Verify the if the total record is 25
    @Test
    public void verifyTotal(){
        response.body("size()",equalTo(25));
    }

    //2.Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
//source not available
    @Test
    public void verifyTitle(){
        response.body("findAll{it.id== 39305}.title",hasItem( "Cruentus absorbeo id auctus suus cunabula sint umerus."));
    }
    //  3. Check the single user_id in the Array list (5522)
    //source not available
    @Test
    public void verifyUserId(){
        response.body("[1].user_id",equalTo(2272684));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681),)
    // Check the multiple ids in the ArrayList (39297, 39296,39295),)
    @Test
    public void verifyMultipleId(){
        response.body("id",hasItems(39297, 39296,39295));
    }

    //5. Verify the body of userid = 2678 is equal to
    //5. Verify the body of userid = 2272684 is equal to
    @Test
    public void verifyBody() {
        response.body("find{it.user_id==2272684}.body", equalTo("Adhuc crebro odit. Tres tredecim cubo. Adfectus universe crustulum. Thorax altus varius. Defigo utor succurro. Denique enim aliqua. Similique torqueo cogo. Succurro triginta thymum. Delectatio desolo atrox. Damno expedita accendo. Nemo cursim tenetur. Utor tamen qui. Ambitus quos baiulus. Sollicito vicissitudo cras. Voluptas numquam sperno. Cultellus auris curriculum. Cogito sodalitas quia. Adaugeo blandior amplus. Tolero libero capio."));
    }
}

