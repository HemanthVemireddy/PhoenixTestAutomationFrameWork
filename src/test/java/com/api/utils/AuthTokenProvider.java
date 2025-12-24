package com.api.utils;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static com.api.utils.ConfigManager_NEW.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
public class AuthTokenProvider {
	
	private AuthTokenProvider()
	{
		
	}

	@Test
	public static String getToken(Role role) {
		// I want to make the request for login API and we want to extract token from response
		UserCredentials UserCredentials = null;
		if(role == Role.FD)
		{
			UserCredentials = new UserCredentials("iamfd","password");
		}
		else if(role == Role.SUP)
		{
			UserCredentials = new UserCredentials("iamsup","password");
		}
		else if(role == Role.EN)
		{
			UserCredentials = new UserCredentials("iameng","password");
		}
		else if(role == Role.QC)
		{
			UserCredentials = new UserCredentials("iamqc","password");
		}
		String jsonPath = given().
			baseUri(ConfigManager_NEW.getproperty("BASE_URI")).
			and().
			contentType(ContentType.JSON).
			and().
			accept(ContentType.JSON).
			and().
			body(UserCredentials).
		when().
			post("login").
		then().
			statusCode(200).
			and().
			log().all().
			time(lessThan(2000L)).
			extract().
			body().
			jsonPath().getString("data.token");
		
		
    return jsonPath;
	}

}
