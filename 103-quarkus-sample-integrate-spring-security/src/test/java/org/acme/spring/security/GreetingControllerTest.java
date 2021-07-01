package org.acme.spring.security;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingControllerTest {

    @Test
    public void testHelloEndpointForbidden() {
        given().auth().preemptive().basic("stuart", "test")
                .when().get("/greeting")
                .then()
                .statusCode(403);
    }

    @Test
    public void testHelloEndpoint() {
        given().auth().preemptive().basic("scott", "jb0ss")
                .when().get("/greeting")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

}
