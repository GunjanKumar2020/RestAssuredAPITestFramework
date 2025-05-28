package com.qa.api.products.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.products;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsTest extends BaseTest{
	
	// This method is created to excuted deserialization test cases.
	@Test
	public void getProductsTest()
	{
		//ChainTestListener.log("Get All Products test");
		Response responseGET=restClient.get(PRODUCTS_BASE_URL, PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(responseGET.statusCode(), 200);
		products[] products=JsonUtils.deserialization(responseGET, products[].class);
		
		for (products product: products)
		{
			System.out.println("Product ID is : " + product.getId());
			System.out.println("Product title is : " + product.getTitle());
			System.out.println("Product price is : " + product.getPrice());
			System.out.println("Product description is : " + product.getDescription());
			System.out.println("Product category is : " + product.getCategory());
			System.out.println("Product image url is : " + product.getImage());
			System.out.println("Product rating is : " + product.getRating().getRate());
			System.out.println("Product ID is : " + product.getRating().getCount());
			
		}
	}

}
