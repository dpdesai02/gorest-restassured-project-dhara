package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

  static   String name ="DPPatel"+ TestUtils.getRandomValue();
  static  String  email = TestUtils.getRandomValue() +" @gmail.com";

  static String gender ="female";

  static String status ="active";

  static int userId;

    @Test
    public void createUser() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        Response response=given()
                .header("Content-Type","application.json")
                .header("Authorization","Bearer e059a6f01d60b97a4ff8f5d47e3fd0d7663d0832b8d1c3c18ed526648dd1df3d")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post("https://gorest.co.in/public/v2/users");
        response.then().log().all().statusCode(201);

    }
    @Test
    public void getUser() {
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization","Bearer e059a6f01d60b97a4ff8f5d47e3fd0d7663d0832b8d1c3c18ed526648dd1df3d")
                .when()
                .get("https://gorest.co.in/public/v2/users/2284037");
        response.then().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Angel");
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        Response response=given()
                .header("Content-Type","application.json")
                .header("Authorization","Bearer e059a6f01d60b97a4ff8f5d47e3fd0d7663d0832b8d1c3c18ed526648dd1df3d")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put("https://gorest.co.in/public/v2/users/2284037");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void verifyUserDeleteSuccessfully() {
        String token ="e059a6f01d60b97a4ff8f5d47e3fd0d7663d0832b8d1c3c18ed526648dd1df3d";
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("https://gorest.co.in/public/v2/users/2284037");
        response.then().log().all().statusCode(204);


    }
}
