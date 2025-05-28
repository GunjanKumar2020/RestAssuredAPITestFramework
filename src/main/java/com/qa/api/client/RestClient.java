package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;

import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIException;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.expect;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestClient {
	
	
   private ResponseSpecification responseSpec200=expect().statusCode(200);
   private ResponseSpecification responseSpec201=expect().statusCode(201);
   private ResponseSpecification responseSpec204=expect().statusCode(204);
   private ResponseSpecification responseSpec400=expect().statusCode(400);
   private ResponseSpecification responseSpec404=expect().statusCode(404);
   private ResponseSpecification responseSpec200or201=expect().statusCode(anyOf(equalTo(200),equalTo(201)));
   private ResponseSpecification responseSpec200or404=expect().statusCode(anyOf(equalTo(200), equalTo(404)));
   
   // GET,PUT & PATCH = Status Code is 200 OK
   // DELETE - Status Code is 204 No Content
   // After Delete, GET Call - Status Code is 404 Not Found
   // and Body message is : "message": "Resource not found"
	
	private RequestSpecification setUpRequest(String baseurl, AuthType authType, ContentType contentType)
	{
		RequestSpecification request=RestAssured.given().log().all()
		       .baseUri(baseurl)
		       .contentType(contentType)
		       .accept(contentType);
		
		switch(authType)
		{
		case BEARER_TOKEN:
			request.header("Authorization","Bearer "+ConfigManager.get("bearertoken"));
			break;
		case OAUTH2:
			request.header("Authorization","Bearer " + "OauthToken");
			break;
		case BASIC_AUTH:
			request.header("Authorization","Basic "+ generateBasicAuth());
			break;
		case API_KEY:
			request.header("x-api-key","Api Key");
			break;
		case NO_AUTH:
			System.out.println("Auth is not required");
			break;
			default:
				System.out.println("This auth is not supported.. Please pass valid auth type");
				throw new APIException("Invalid API Auth Type");
			}
		return request;
		
	}
	
	
	private void applyParams(RequestSpecification request, HashMap<String, String> pathParam, HashMap<String,String> queryParam)
	{
		if (queryParam !=null)
		{
			request.queryParams(queryParam);
		}
		
		if (pathParam !=null)
		{
			request.pathParams(pathParam);
		}
	}
	
	private String generateBasicAuth()
	{
		String basicAuth = ConfigManager.get("basic_auth_username") + ":" + ConfigManager.get("basic_auth_password");
		
		// Basic Auth with admin : admin values in java script endoded with btoa method and return as encoded values as
		// 'YWRtaW5hZG1pbg=='
		// But in Java used in RestAssured we don't have btoa method to convert in encoded values.
		// hence we use Base64.getEncoder().encodeToString and pass the variable which stores the values of admin:admin and then  get the byte values
				
		return Base64.getEncoder().encodeToString(basicAuth.getBytes());
	}
	// CRUD Operations methods are written below :
	
	// 1. GET Call method
	
	/**
	 * This method is used to call GET api
	 * @param baseurl
	 * @param endPoint
	 * @param pathParam
	 * @param queryParam
	 * @param authType
	 * @param contentType
	 * @return It returns the GET api response
	 */
	public Response get(String baseurl,String endPoint, 
			HashMap<String,String> pathParam, HashMap<String,String> queryParam,
			AuthType authType,ContentType contentType)
	{
		 RequestSpecification request=setUpRequest(baseurl, authType, contentType);
		 applyParams(request,pathParam,queryParam);
		 Response response= request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		 response.prettyPrint();
		 return response;
	}
	
	// 2. POST Call Method 
	
	public <T> Response post(String baseurl, String endPoint, T body,
			HashMap<String, String> pathParam , HashMap<String,String> queryParam,
			AuthType authType,ContentType contentType)
	{
		RequestSpecification request=setUpRequest(baseurl,authType,contentType);
		applyParams(request,pathParam, queryParam);
		Response response=request.body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
		
		}
	
	
	public  Response post(String baseurl, String endPoint, File file,
			HashMap<String, String> pathParam , HashMap<String,String> queryParam,
			AuthType authType,ContentType contentType)
	{
		RequestSpecification request=setUpRequest(baseurl,authType,contentType);
		applyParams(request,pathParam, queryParam);
		Response response=request.body(file).post(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
		
		}
	
	// 3. PUT Call : 
	
	public <T> Response put(String baseurl, String endPoint, T body,
			HashMap<String, String> pathParam , HashMap<String,String> queryParam,
			AuthType authType,ContentType contentType)
	{
		RequestSpecification request=setUpRequest(baseurl,authType,contentType);
		applyParams(request,pathParam, queryParam);
		Response response=request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
		
		}
	
	// 4. PATCH Call :
	
	public <T> Response patch(String baseurl, String endPoint, T body,
			HashMap<String, String> pathParam , HashMap<String,String> queryParam,
			AuthType authType,ContentType contentType)
	{
		RequestSpecification request=setUpRequest(baseurl,authType,contentType);
		applyParams(request,pathParam, queryParam);
		Response response=request.body(body).patch(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
		
		}
	
	// 5. DELETE Call :
	
	public Response delete(String baseurl, String endPoint,
			HashMap<String, String> pathParam , HashMap<String,String> queryParam,
			AuthType authType,ContentType contentType)
	{
		RequestSpecification request=setUpRequest(baseurl,authType,contentType);
		applyParams(request,pathParam, queryParam);
		Response response=request.delete(endPoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
		
		}
	
	
	// 6. GET Call - To verify the deleted records
	
	

}
