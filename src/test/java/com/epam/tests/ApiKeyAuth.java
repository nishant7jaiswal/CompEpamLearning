package com.epam.tests;

import com.epam.module.Category;
import com.epam.module.Pet;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiKeyAuth {

    @BeforeClass
    public static void setUp(){

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("X-Auth-Token","0293faadfa1242f9b320533de8b32e06")
                .setBaseUri("https://api.football-data.org/")
                .setBasePath("v2/")
                .build();
    }
    @Test
    public void apiKeyAuthFootball(){

    get("matches").then().body("filters.permission",equalTo("TIER_ONE"))
            .statusCode(200).log().all();

    }

    @Test
    public void apiKeyAuthAllMatches(){

        Response resp = get("matches");
        List<Map<String,?>> matchData =resp.path("matches");


    }
    @Test
    public void oauth1ApiTest(){

        given().auth()
                .oauth("a73bbb264c7432421b9abc05fae6bf9379b9fb49bbefdf84ab036487fd5b"
                        ,"467b9a301fedac887a46a23d3f29d76afdff30ac9bbf6c8c"
                        ,"10dbffca01ce6985868b4cee84e0444f5bcdda104b60a13038c1d74b72e6797f"
                        ,"4f5bcdda104b60a13038c1d74b72e668b4cee84e04404b60")
                .when().post("https://njtester.aha.io/oauth/token?msg=Testing")
                .then().statusCode(200);

    }


}
