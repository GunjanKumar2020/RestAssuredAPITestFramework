package com.qa.api.gorest.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{
	
	@Test
	public void getAllUsersTest()
	{
		Response response=restClient.get(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Test
	public void getAllUsersWithQueryParamsTest()
	{
		HashMap<String,String> queryParams=new HashMap<String,String>();
		queryParams.put("name", "naveen");
		queryParams.put("Status", "active");
		Response response=restClient.get(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, null, queryParams, AuthType.BEARER_TOKEN,ContentType.JSON);
		
		Assert.assertTrue(response.statusLine().contains("OK"));
	}
	
	@Test(enabled=false)
	public void getSingleUserTest()
	{
		String userID="7725337";
		Response response=restClient.get(GOREST_BASE_URL, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		
		Assert.assertTrue(response.statusLine().contains("OK"));
		Assert.assertEquals(response.jsonPath().getString("id"), userID);
	}
	
	

}
