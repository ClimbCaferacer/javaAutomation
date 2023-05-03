package com.reportportal.services.api.widget.create.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CreateWidgetRequest{

	@JsonProperty("filterIds")
	private List<String> filterIds;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("share")
	private boolean share;

	@JsonProperty("contentParameters")
	private ContentParameters contentParameters;

	@JsonProperty("widgetType")
	private String widgetType;

	@JsonProperty("filters")
	private List<Filters> filters;
}