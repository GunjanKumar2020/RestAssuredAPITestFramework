package com.qa.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtils {
	
	// This method is created for deserialization (POJO to JSON Conversion)
	
	private static ObjectMapper objectMapper=new ObjectMapper();
	public static  <T> T deserialization(Response response, Class<T> targetClass)
	{
		try
		{
		return objectMapper.readValue(response.getBody().asString(), targetClass);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Deserialization is Failed .." + targetClass.getName());
		}
	}

}
