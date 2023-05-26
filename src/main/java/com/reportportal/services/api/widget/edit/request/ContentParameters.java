package com.reportportal.services.api.widget.edit.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContentParameters{
	private WidgetOptions widgetOptions;
	private List<Object> contentFields;
	private Integer itemsCount;
}