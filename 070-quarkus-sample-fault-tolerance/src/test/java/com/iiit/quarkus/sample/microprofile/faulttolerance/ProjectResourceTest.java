package com.iiit.quarkus.sample.microprofile.faulttolerance;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ProjectResourceTest extends BaseTest {

    @Inject
    private ProjectResource projectResource;

    @Test
    public void testCoffeeList() {
        projectResource.resetCounter();
        projectResource.setFailRatio(0f);
        get("/projects")
                .then()
                .statusCode(200)
                .body("id", hasItems(1, 2, 3))
                .body("countryOfOrigin", hasItems("Colombia", "Bolivia", "Vietnam"));
        Assertions.assertEquals(1, projectResource.getCounter().longValue());

        projectResource.resetCounter();
        projectResource.setFailRatio(1f);
        get("/projects")
                .then()
                .statusCode(500);
        Assertions.assertEquals(5, projectResource.getCounter().longValue());
    }

    @Test
    public void testCoffeeDetail() {
    	projectResource.setFailRatio(0f);
        get("/projects/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("countryOfOrigin", is("Colombia"));

        projectResource.setFailRatio(1f);
        get("/project/1")
                .then()
                .statusCode(500);
    }
}
