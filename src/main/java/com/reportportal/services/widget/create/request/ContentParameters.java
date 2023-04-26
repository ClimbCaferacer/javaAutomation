package com.reportportal.services.widget.create.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ContentParameters{

	@JsonProperty("widgetOptions")
	private WidgetOptions widgetOptions;

	@JsonProperty("contentFields")
	private List<String> contentFields;

	@JsonProperty("itemsCount")
	private int itemsCount;
}