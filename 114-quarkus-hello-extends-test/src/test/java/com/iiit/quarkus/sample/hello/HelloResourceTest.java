package com.iiit.quarkus.sample.hello;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import java.util.UUID;

@QuarkusTest
public class HelloResourceTest {

	@Test
	public void testHelloEndpoint() {
		given().when().get("/hello").then().statusCode(200).body(is("hello world"));
	}

	@Test
	public void testGreetingEndpoint() {
		String uuid = UUID.randomUUID().toString();
		given().pathParam("name", uuid).when().get("/hello/{name}").then()
				.statusCode(200).body(is("hello " + uuid));
	}

}