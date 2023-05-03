package com.reportportal.services.api.widget.get.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConditionsItem{

	@JsonProperty("condition")
	private String condition;

	@JsonProperty("filteringField")
	private String filteringField;

	@JsonProperty("value")
	private String value;
}