package com.qa.api.gorest.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.CreateUser;
import com.qa.api.utils.ExcelReaderUtil;
import com.qa.api.utils.StringUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest{
	
	@DataProvider
	public Object[][] getUserData()
	{
		return new Object[][]
				{
			{"Gunjan","male","active"},
			{"Neha","female","inactive"},
			{"Vidya","female","inactive"},
			{"Rajesh","male","active"}
		};
	}
	
	@DataProvider
	public Object[][] getUserExcelData()
	{
		return ExcelReaderUtil.readDataFromExcelFile(AppConstants.USER_EXEL_SHEET_NAME);
	}
	
	@Test(dataProvider="getUserData")
	//@Test(dataProvider="getUserExcelData")
	public void createUserTest(String name,String gender,String status)
	{
		
		CreateUser user=new CreateUser(null,name,StringUtil.getRandomEmailid(), gender, status);
		ChainTestListener.log("Created user :" + user);
		Response response=restClient.post(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), name);
		Assert.assertEquals(response.jsonPath().getString("gender"), gender);
		Assert.assertNotNull(response.jsonPath().getString("id"));
		
	}
	
	@Test
	public void createUserWithStringTest()
	{
		String emailID=StringUtil.getRandomEmailid();
		
				
		String userJson="{\r\n"
				+ "    \"name\": \"Gunjan\",\r\n"
				+ "    \"gender\": \"Male\",\r\n"
				+ "    \"email\": \""+emailID+"\",\n"
				+ "    \"status\": \"active\"\r\n"
				+ "}";
				
		Response response=restClient.post(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, userJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Gunjan");
		//Assert.assertEquals(response.jsonPath().getString("gender"), "Male");
		//Assert.assertNotNull(response.jsonPath().getString("id"));
		
	}
	
	@Test(enabled=false)
	public void createUserWithJsonFileTest()
	{
		File userFile=new File("./src/test/resource/jsons/user.json");
				
		Response response=restClient.post(GOREST_BASE_URL, GOREST_USERS_ENDPOINT, userFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Gunjan");
		//Assert.assertEquals(response.jsonPath().getString("gender"), "Male");
		
		
	}


}
