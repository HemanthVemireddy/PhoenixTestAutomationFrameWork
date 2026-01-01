package com.api.tests;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;

import static com.api.utils.ConfigManager_NEW.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class CountAPITest
{
	@Test
	public static void verifyCountAPIResponse()
	{
		given().
			baseUri(getproperty("BASE_URI")).
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			headers("Authorization", AuthTokenProvider.getToken(Role.FD)).
			log().uri().
			log().method().
			log().headers().
		when().
		    get("/dashboard/count").
		then().
	        statusCode(200).
	        time(lessThan(2000L)).
	        log().all().
	        body("message", equalTo("Success")).
	        body("data", notNullValue()).
	        body("data.size()", equalTo(3)).
		    body("data.count", everyItem(greaterThanOrEqualTo(0))).
		    body("data.label", everyItem(not(blankOrNullString()))).
		    body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json")).
		    body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
	        //jsonPath().getString()
	}

}
