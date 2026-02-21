package com.api.tests;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest 
{
	
	@Test
	public void CreateJobAPITest()
	{
		Customer customer = new Customer("Hudson", "Kumar", "8984494844", "", "h@gmail.com", "");
		CustomerAddress customadd = new CustomerAddress("1-131", "Duvvur", "YSR", "516175", "", "Andhra Pradesh", "India", "7647466443763");
		CustomerProduct customprod = new CustomerProduct("2025-04-06T18:30:00.000Z", "19554842580198", "19554842580198", "19554842580198", "19554842580198", 1, 1);
		Problems problem = new Problems(1, "Battery Issue");
		List<Problems> problemarray = new ArrayList<>();
		problemarray.add(problem);
		
		CreateJobPayload payload = new CreateJobPayload( "LOC001", "PLAT001", "WAR001", "OEM001", customer, customadd, customprod,problemarray);

		
		
		given()
		.spec(SpecUtils.requestSpec(payload))
		.when()
		.post("/job/create")
		.then()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPI.json"))
		.body("message",Matchers.equalTo(""))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
	}

}
