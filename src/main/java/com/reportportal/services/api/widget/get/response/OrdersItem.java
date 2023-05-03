package com.reportportal.services.api.widget.get.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrdersItem{

	@JsonProperty("sortingColumn")
	private String sortingColumn;

	@JsonProperty("isAsc")
	private boolean isAsc;
}