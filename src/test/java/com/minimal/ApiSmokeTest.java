package com.minimal;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ApiSmokeTest {
    @Test
    void getPost_returns200AndValidFields() {
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
