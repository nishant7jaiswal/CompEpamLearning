package com.epam.tests;

import com.epam.module.Category;
import com.epam.module.Pet;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.tools.ant.taskdefs.optional.depend.Depend;
import org.testng.Assert;
import org.junit.*;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiCrudAction {

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
    public void createAPet(){

        Pet pet = new Pet(83,"Doggysloggy",new Category(1,"dog"),"pending");

        Response response = given().contentType(ContentType.JSON)
                            .body(pet)
                            .when().post("/pet");
        Assert.assertEquals(response.getStatusCode(),200);
        String petid = response.path("id").toString();
        System.out.println(petid);
    }

    @Test()
    public void retrivePet(){

        get("/pet/"+petid)
                .then().assertThat().statusCode(200);

    }

    @Test
    public void petUpdate(){
        Pet pet = new Pet(83,"Doggysloggy",new Category(1,"dog"),"available");

        Response response = given().contentType(ContentType.JSON)
                .body(pet)
                .when().put("/pet");
        Assert.assertEquals(response.getStatusCode(),200);
        String petid = response.path("id").toString();
        System.out.println(petid);

    }


    @Test
    public void petDelete(){

        delete("/pet/"+petid).then().statusCode(200);
    }

}
