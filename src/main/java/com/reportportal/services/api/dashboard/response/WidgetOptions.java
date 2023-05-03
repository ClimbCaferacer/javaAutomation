package com.reportportal.services.api.dashboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class WidgetOptions{

	@JsonProperty("launchNameFilter")
	private String launchNameFilter;

	@JsonProperty("includeMethods")
	private boolean includeMethods;

	@JsonProperty("minPassingRate")
	private String minPassingRate;

	@JsonProperty("attributeKeys")
	private List<String> attributeKeys;

	@JsonProperty("latest")
	private boolean latest;

	@JsonProperty("viewMode")
	private String viewMode;

	@JsonProperty("timeline")
	private String timeline;

	@JsonProperty("zoom")
	private boolean zoom;
}