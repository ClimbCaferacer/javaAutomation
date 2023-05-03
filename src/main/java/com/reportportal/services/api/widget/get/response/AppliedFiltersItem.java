package com.reportportal.services.api.widget.get.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppliedFiltersItem{

	@JsonProperty("owner")
	private String owner;

	@JsonProperty("name")
	private String name;

	@JsonProperty("share")
	private boolean share;

	@JsonProperty("orders")
	private List<OrdersItem> orders;

	@JsonProperty("id")
	private int id;

	@JsonProperty("conditions")
	private List<ConditionsItem> conditions;

	@JsonProperty("type")
	private String type;
}