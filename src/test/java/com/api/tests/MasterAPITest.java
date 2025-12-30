package com.api.tests;
import org.testng.annotations.Test;
import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import static com.api.utils.ConfigManager_NEW.*;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class MasterAPITest 
{
	@Test
	public static void masterAPITest()
	{
		given().
		baseUri(getproperty("BASE_URI")).
		contentType("").
		headers("Authorization", AuthTokenProvider.getToken(Role.FD)).
	    contentType("")   // default content-type: application/x-www-form-urlencoded
	    .log().all()
	.when()
	    .post("master")
	.then()
	    .log().all()
	    .statusCode(200)
	    .time(lessThan(3000L))
	    .body("message", equalTo("Success"))
	    .body("data", notNullValue())
	    .body("data", hasKey("mst_oem"))
	    .body("data", hasKey("mst_model"))
	    .body("$", hasKey("message")) // in Array contains multiple list values .
	    .body("$", hasKey("data")) //$ means bigger or whole JSON
	    .body("data.mst_oem.size()", equalTo(2))           // check JSON array size
	    .body("data.mst_model.size()", greaterThan(0))
	    .body("data.mst_oem.id", everyItem(notNullValue()))
	    .body("data.mst_oem.name", everyItem(notNullValue()))
	    .body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json" ));

	}
	
	
	@Test
	public static void InvalidTokenMasterAPI()
	{
		given().
		baseUri(getproperty("BASE_URI")).
		contentType("").
		headers("Authorization", "").
	    contentType("")   // default content-type: application/x-www-form-urlencoded
	    .log().all()
	.when()
	    .post("master")
	.then()
	    .log().all()
	    .statusCode(401);
	}

}
