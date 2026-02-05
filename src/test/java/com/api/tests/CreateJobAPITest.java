package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest 
{
	
	@Test
	public void CreateJobAPITest()
	{
		Customer customer = new Customer("", "", "", "", "", "");
		CustomerAddress customadd = new CustomerAddress("", "", "", "", "", "", "", "");
		CustomerProduct customprod = new CustomerProduct("", "", "", "", "", 0, 0);
		Problems problem = new Problems(0, null);
		List<Problems> problemarray = new ArrayList<>();
		problemarray.add(problem);
		
		CreateJobPayload paylod =new CreateJobPayload(null, null, null, null, customer, customadd, customprod, problemarray);
		
		
		given()
		.spec(requestSpecification)
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpecification)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(""))
		.body("message",Matchers.equalTo(""))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
	}

}
