package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import com.qa.api.client.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	protected RestClient restClient;
	
	// ***************  BASE URLs*************************
	protected final static String GOREST_BASE_URL="https://gorest.co.in";
	protected final static String CONTACTS_BASE_URL="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String REQRES_BASE_URL="https://reqres.in";
	protected final static String BASIC_AUTH_URL="https://the-internet.herokuapp.com";
	protected final static String PRODUCTS_BASE_URL="https://fakestoreapi.com";
	
	
	
	// *************** ENDPOINTs *******************
	protected final static String GOREST_USERS_ENDPOINT="/public/v2/users";
	protected final static String CONTACTS_LOGIN_ENDPOINT="/users/login";
	protected final static String CONTACTS_CONTACTS_ENDPOINT="/contacts";
	protected final static String REQRES_ENDPOINT="/api/users";
	protected final static String BASIC_AUTH_ENDPOINT="/basic_auth";
	protected final static String PRODUCTS_ENDPOINT="/products";
	
	@BeforeTest
     public void setUp()
    {
	      restClient=new RestClient();
     }
	
	@BeforeSuite
	public void setUpAllureReport()
	{
		RestAssured.filters(new AllureRestAssured());;
	}
	

}
