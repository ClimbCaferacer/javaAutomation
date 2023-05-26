package com.reportportal.services.api.widget.edit.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WidgetOptions{
	private String minPassingRate;
	private List<String> attributeKeys;
	private Boolean latest;
}