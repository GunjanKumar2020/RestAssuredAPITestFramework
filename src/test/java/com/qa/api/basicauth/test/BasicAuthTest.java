package com.qa.api.basicauth.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest extends BaseTest{
	
	@Test
	public void basicAuthValidationTest()
	{
		Response responseGET=restClient.get(BASIC_AUTH_URL, BASIC_AUTH_ENDPOINT, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		Assert.assertEquals(responseGET.statusCode(), 200);
		Assert.assertTrue(responseGET.body().asString().contains("Congratulations! You must have the proper credentials."));
	}

}
