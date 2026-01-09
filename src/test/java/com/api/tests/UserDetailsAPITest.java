package com.api.tests;

import org.testng.annotations.Test;
import com.api.constant.Role;
import com.api.utils.SpecUtils;
import static org.hamcrest.Matchers.*;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest 
{
	
	@Test
	public void UserDetailsAPITest()
	{
		given()
			.spec(SpecUtils.requestSpecWithAuth(Role.EN))
		.when()
			.get("userdetails")
		.then()
			.spec(SpecUtils.responseSpec_ok())
			.log().ifValidationFails()
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));		
	}

}
