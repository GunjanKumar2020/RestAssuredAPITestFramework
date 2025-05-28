package com.qa.api.contacts.tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Credentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CredentialsTest extends BaseTest{
	
	private String tokenID;
	
	@BeforeMethod
	public void getToken()
	{
		// eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2E2MzE1YmQxMjAyYzAwMTNjNjRkNjQiLCJpYXQiOjE3NDc0NTU4MTh9.tTG0F-orpbTEkT4kfdd3t_etvuZ0VKVJP6pSI_Qwx2Y
		
		Credentials cred=Credentials.builder()
		         .email("sim8166@gmail.com")
		         .password("Test@12345")
		         .build();
		Response response=restClient.post(CONTACTS_BASE_URL, CONTACTS_LOGIN_ENDPOINT, cred, null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		tokenID=response.jsonPath().getString("token");
		ConfigManager.set("bearertoken", tokenID);
		System.out.println("Generated Token Id is : " + tokenID);
	
	}
	
	@Test
	public void getContacts()
	{
		Response responseGET=restClient.get(CONTACTS_BASE_URL, CONTACTS_CONTACTS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGET.statusCode(), 200);
		
	}

}
