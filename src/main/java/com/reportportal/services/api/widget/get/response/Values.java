package com.reportportal.services.api.widget.get.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Values{

	@JsonProperty("statistics$executions$passed")
	private String statisticsExecutionsPassed;

	@JsonProperty("statistics$executions$failed")
	private String statisticsExecutionsFailed;

	@JsonProperty("statistics$executions$skipped")
	private String statisticsExecutionsSkipped;
}