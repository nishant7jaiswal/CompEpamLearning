package com.epam.tests;

import com.epam.module.Category;
import com.epam.module.Pet;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;
import static org.hamcrest.CoreMatchers.equalTo;


import static io.restassured.RestAssured.*;

public class ApiCrudActionRefractor {

    private String petid ="83";

    @BeforeClass
    public static void setUp(){

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("appication/json")
                .setBaseUri("https://petstore.swagger.io/")
                .setBasePath("v2")
                .build();
    }

    @Test
    public void allInOne(){

        Pet pet = new Pet(101,"Doggytest",new Category(1,"dog"),"pending");
        //Create request
        String petID = given().contentType(ContentType.JSON).body(pet).when().post("/pet").path("id").toString();
        //Get request
        get("/pet/"+petID).then().statusCode(200).body("status",equalTo("pending"));
        //Update request
        pet.setStatus("available");
        given().contentType(ContentType.JSON).body(pet).when().put("/pet").then().statusCode(200);
        get("/pet/"+petID).then().statusCode(200).body("status",equalTo("available"));
        //Delete request
        delete("/pet/"+petID).then().statusCode(200);
        //Verify the pet is deleted
        get("/pet/"+petID).then().statusCode(404);

    }



}
