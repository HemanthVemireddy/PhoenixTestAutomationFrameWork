package com.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager_NEW.*;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;
public class SpecUtils {

    // To take care of common request configuration
    public static RequestSpecification requestSpec() {

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getproperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        
        return requestSpecification;
        
    }
    
    /*
     * Token Based Method
     */
    public static RequestSpecification requestSpecWithAuth(Role role) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getproperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", AuthTokenProvider.getToken(role))
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        
        return requestSpecification;
        
    }
    
    public static RequestSpecification requestSpec(Object payload) {

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getproperty("BASE_URI"))
                .setBody(payload)
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        
        return requestSpecification;
        
    }
    
   /*
    * Validation using Hamcrest Matchers 
    * Validate Response Status and time
    */
    public static ResponseSpecification responseSpec_ok()
    {
    	 ResponseSpecification responseSpecification= new ResponseSpecBuilder()
    	                              .expectResponseTime(Matchers.lessThan(1000L))
    	                              .expectStatusCode(200)
    	                              .expectContentType(ContentType.JSON)
    	                              .log(LogDetail.ALL)
    	                              .build();
    	                              return responseSpecification;
    }
    
    
    /*
     * Validation using Hamcrest Matchers 
     * Validate Response Status 
     */
     public static ResponseSpecification responseSpec(int status)
     {
     	 ResponseSpecification responseSpecification= new ResponseSpecBuilder()
     	                              .expectResponseTime(Matchers.lessThan(1000L))
     	                              .expectStatusCode(status)
     	                              .expectContentType(ContentType.JSON)
     	                              .log(LogDetail.ALL)
     	                              .build();
     	                              return responseSpecification;
     }
}
