package com.qa.api.gorest.tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.CreateUser;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{

	@Test
	public void deleteUserTest()
	{
		
		// 1. Create User
		
		CreateUser user=new CreateUser();
		user.setName("Kumar");
		user.setEmail(StringUtil.getRandomEmailid());
		user.setGender("male");
		user.setStatus("active");
		
		Response responsePost=restClient.post(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePost.jsonPath().getString("name"), "Kumar");
		Assert.assertEquals(responsePost.jsonPath().getString("gender"), "male");
		String userID=responsePost.jsonPath().getString("id");
		System.out.println("Created UserID is : " +userID);
		
		// 2. Get Created User
		
		Response responseGet=restClient.get(GOREST_BASE_URL, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), "Kumar");
		Assert.assertEquals(responseGet.jsonPath().getString("gender"), "male");
		Assert.assertEquals(responseGet.jsonPath().getString("status"), "active");
		Assert.assertEquals(responseGet.jsonPath().getString("id"), userID);
		
		// 3. Delete Created User
		
		Response responseDelete=restClient.delete(GOREST_BASE_URL, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		// 4. Get Deleted User Details
		
		Response responseGetDeletedUser=restClient.get(GOREST_BASE_URL, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGetDeletedUser.body().jsonPath().getString("message"),"Resource not found");
	}
}
