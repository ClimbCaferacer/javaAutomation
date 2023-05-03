package com.reportportal.services.api.widget.get.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WidgetOptions{

	@JsonProperty("timeline")
	private String timeline;

	@JsonProperty("zoom")
	private boolean zoom;

	@JsonProperty("viewMode")
	private String viewMode;
}