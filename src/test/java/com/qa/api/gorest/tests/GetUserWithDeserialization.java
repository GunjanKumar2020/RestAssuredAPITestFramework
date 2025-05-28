package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.CreateUser;
import com.qa.api.utils.JsonUtils;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserWithDeserialization extends BaseTest{
	
	private String tokenID;
	
	@BeforeClass
	public void setUpToken()
	{
		tokenID="e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6";
		ConfigManager.set("bearertoken", tokenID);
	}
	
	
	@Test
	public void CreateAUserTest()
	{
	     	CreateUser user=new CreateUser(null,"Naveen", StringUtil.getRandomEmailid(), "male", "active");
			Response response=restClient.post(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			Assert.assertEquals(response.jsonPath().getString("name"), "Naveen");
			Assert.assertEquals(response.jsonPath().getString("gender"), "male");
			Assert.assertNotNull(response.jsonPath().getString("id"));
			
			String userID=response.jsonPath().getString("id");
			
			// GET a created User
			
			Response responseGET=restClient.get(GOREST_BASE_URL, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
			
			Assert.assertTrue(responseGET.statusLine().contains("OK"));
			Assert.assertEquals(responseGET.jsonPath().getString("id"), userID);
			
			CreateUser userResponse=JsonUtils.deserialization(responseGET, CreateUser.class);
			Assert.assertEquals(userResponse.getName(), "Naveen");
			Assert.assertEquals(userResponse.getStatus(), "active");
	}

}
