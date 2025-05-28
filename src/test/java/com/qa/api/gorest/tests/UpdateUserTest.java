package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.CreateUser;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest{
	
	
	
	
	@Test
	public void updateUser()
	{
		// Passing Body data by creating POJO class using builder pattern
		
		CreateUser user=CreateUser.builder()
		
		.name("Neha")
		.email(StringUtil.getRandomEmailid())
		.status("inactive")
		.gender("female")
		.build();
		
		// 1. Create a new User
		
		Response responsePost=restClient.post(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePost.jsonPath().getString("name"), "Neha");
		Assert.assertEquals(responsePost.jsonPath().getString("gender"), "female");
		String userID=responsePost.jsonPath().getString("id");
		
		System.out.println("Create User Id is : " + userID);
		
		// 2. Fatching userId after creating a new record
		
		Response responseGET=restClient.get(GOREST_BASE_URL, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGET.jsonPath().getString("name"), "Neha");
		Assert.assertEquals(responseGET.jsonPath().getString("gender"), "female");
		Assert.assertEquals(responseGET.jsonPath().getString("id"),userID );
		
		// 3. Updating the create user details
		
		user.setName("Nandita");
		user.setStatus("active");
		Response responsePUT=restClient.put(GOREST_BASE_URL, GOREST_USERS_ENDPOINT+"/"+userID, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(responsePUT.jsonPath().getString("name"), "Nandita");
		Assert.assertEquals(responsePUT.jsonPath().getString("status"), "active");
		Assert.assertEquals(responsePUT.jsonPath().getString("status"),"active" );
		
	}

	
		
	}


