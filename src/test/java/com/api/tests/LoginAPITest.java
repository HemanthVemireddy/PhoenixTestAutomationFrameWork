package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager_NEW.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
	public void loginAPITest()
	{
		UserCredentials user = new UserCredentials("iamfd","password");
		
		given()
			.baseUri(getproperty("BASE_URI"))
		.and()
			.contentType(ContentType.JSON)
		.and()
			.accept(ContentType.JSON)
		.and()
			.body(user)
		.and()
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
		
		.when()
			.post("login")
		.then()
			.statusCode(200)
			.time(lessThan(3000L))
			.log().all()
		    .body("message", equalTo("Success"))
		    .and()
		    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema\\LoginResponseSchema.json"));
		
	}

}
