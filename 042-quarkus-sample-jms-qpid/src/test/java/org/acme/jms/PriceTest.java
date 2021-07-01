package org.acme.jms;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.quarkus.artemis.test.ArtemisTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
@QuarkusTestResource(ArtemisTestResource.class)
public class PriceTest {

    @Test
    public void testLastPrice() throws Exception {
        assertTrue(Wait.waitFor(() -> {
            return RestAssured.given().when().get("/prices/last").getStatusCode() == 200;
        }, 10000, 25), "Price didnt became available in allotted time");

        RestAssured.given()
                .when().get("/prices/last")
                .then()
                .statusCode(200)
                .body(matchesPattern("\\d+"));
    }
}
