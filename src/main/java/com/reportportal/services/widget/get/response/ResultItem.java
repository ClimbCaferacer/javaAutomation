package com.reportportal.services.widget.get.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultItem{

	@JsonProperty("number")
	private int number;

	@JsonProperty("values")
	private Values values;

	@JsonProperty("name")
	private String name;

	@JsonProperty("startTime")
	private long startTime;

	@JsonProperty("id")
	private int id;
}