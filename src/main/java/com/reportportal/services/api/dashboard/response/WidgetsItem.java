package com.reportportal.services.api.dashboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WidgetsItem{

	@JsonProperty("widgetName")
	private String widgetName;

	@JsonProperty("widgetOptions")
	private WidgetOptions widgetOptions;

	@JsonProperty("widgetId")
	private int widgetId;

	@JsonProperty("widgetSize")
	private WidgetSize widgetSize;

	@JsonProperty("widgetPosition")
	private WidgetPosition widgetPosition;

	@JsonProperty("share")
	private boolean share;

	@JsonProperty("widgetType")
	private String widgetType;
}