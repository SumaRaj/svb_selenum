package com.svb.qa;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiBasicsTest {
    @Test
    void getPost_shouldReturn200AndValidateBody() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
            .log().uri()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", greaterThan(0))
            .body("title", not(isEmptyOrNullString()));
    }
}
