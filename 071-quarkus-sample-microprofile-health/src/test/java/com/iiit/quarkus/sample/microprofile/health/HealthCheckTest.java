package com.iiit.quarkus.sample.microprofile.health;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HealthCheckTest {

    @Test
    public void testHealthCheck() {
        given()
                .when()
                .get("/health/live")
                .then()
                .statusCode(200)
                .body("status", is("UP"))
                .body("checks.size()", is(2))
                .body("checks.name", everyItem(anyOf(
                        is("Simple health check"),
                        is("Health check with data"))))
                .body("checks.status", everyItem(is("UP")))
                .body("checks.data.foo[0]", is("fooValue"))
                .body("checks.data.bar[0]", is("barValue"));
    }
}
