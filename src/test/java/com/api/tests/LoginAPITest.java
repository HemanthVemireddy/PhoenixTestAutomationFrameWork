package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest()
	{
		UserCredentials user = new UserCredentials("iamfd","password");
		
		given()
			.spec(SpecUtils.requestSpec(user))
		
		.when()
			.post("login")
		.then()
			.spec(SpecUtils.responseSpec_ok())
		    .body("message", equalTo("Success"))
		    .and()
		    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
	}

}
