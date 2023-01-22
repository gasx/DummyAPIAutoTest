package org.example;


import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class DeleteTest {
    public String URL = "https://dummy.restapiexample.com";
    public int userId = 9014;
    @Test
    public void test() throws InterruptedException {
        RestAssured.baseURI = URL;

        given().
                when().
                delete("/api/v1/delete/"+userId).
                then().
                statusCode(200);

        Thread.sleep(30000);

        given().
                when().
                get("/api/v1/employee/"+userId).
                then().
                statusCode(404);
    }

    @Test
    public void canUpdateNotExistingParam() {
        RestAssured.baseURI = URL;
        double random = Math.random();
        int randomNum = (int) Math.round(random);
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        given().
                when().
                post("/api/v1/create?"+generatedString+"="+randomNum).
                then().
                statusCode(400);
    }

}
