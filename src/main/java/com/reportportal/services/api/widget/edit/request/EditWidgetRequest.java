package com.reportportal.services.api.widget.edit.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EditWidgetRequest {
	private String owner;
	private List<String> filterIds;
	private String name;
	private String description;
	private Boolean share;
	private ContentParameters contentParameters;
	private List<FiltersItem> filters;
	private String widgetType;
}