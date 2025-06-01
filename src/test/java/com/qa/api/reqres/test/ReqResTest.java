package com.qa.api.reqres.test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;

import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTest extends BaseTest {
	
	@Test(enabled=false)
	public void getUserDetails()
	{
		HashMap<String, String> queryParam=new HashMap<String,String>();
		queryParam.put("page", "2");
		Response responseGET=restClient.get(REQRES_BASE_URL, REQRES_ENDPOINT, null, queryParam, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(responseGET.statusCode(), 200);
	}

}
