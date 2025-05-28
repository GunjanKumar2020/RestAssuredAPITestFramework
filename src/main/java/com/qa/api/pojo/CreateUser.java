package com.qa.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL) // This annotcation will exlude the passing null value in request.
public class CreateUser {
	private Integer id; // During Deserialization, server is returing with id value.So it's created
	private String name;
	private String email;
	private String gender;
	private String status;

}
