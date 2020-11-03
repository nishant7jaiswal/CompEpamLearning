package com.epam.tests;


import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class FirstTest {
    @Test
    public void apigettest(){

    given().baseUri("http://api.zippopotam.us/")
            .when().get("us/90210")
            .then().assertThat().statusCode(200)
            .body("country",equalTo("United States"))
            .body("places[0].state",equalTo("California")).log().all();

    }




}
