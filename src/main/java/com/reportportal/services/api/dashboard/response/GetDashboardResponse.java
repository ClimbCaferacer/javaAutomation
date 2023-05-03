package com.reportportal.services.api.dashboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetDashboardResponse {

	@JsonProperty("owner")
	private String owner;

	@JsonProperty("name")
	private String name;

	@JsonProperty("share")
	private boolean share;

	@JsonProperty("id")
	private int id;

	@JsonProperty("widgets")
	private List<WidgetsItem> widgets;
}