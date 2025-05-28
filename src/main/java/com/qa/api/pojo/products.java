package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class products {

	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private ratings rating;
	
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ratings
	{
		private double rate;
		private int count;
		
	}
	
}
