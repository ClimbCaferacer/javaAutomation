package com.reportportal.services.widget.get.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentParameters{

	@JsonProperty("widgetOptions")
	private WidgetOptions widgetOptions;

	@JsonProperty("contentFields")
	private List<String> contentFields;

	@JsonProperty("itemsCount")
	private int itemsCount;
}