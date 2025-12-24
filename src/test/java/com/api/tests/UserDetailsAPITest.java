package com.api.tests;

import org.testng.annotations.Test;

import com.api.constant.Role;

import static com.api.utils.AuthTokenProvider.*;

import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest 
{
	
	@Test
	public void UserDetailsAPITest()
	{
		Header authHeader = new Header("Authorization",getToken(Role.EN));
		given()
			.baseUri("http://64.227.160.186:9000/v1")
			.contentType(ContentType.JSON)
			.header(authHeader)
		.when()
			.get("userdetails")
		.then()
			.statusCode(200)
			.time(lessThan(1000L))
			.log().ifValidationFails()
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema\\UserDetailsResponseSchema.json"));
			
		
			
			
	}

}
