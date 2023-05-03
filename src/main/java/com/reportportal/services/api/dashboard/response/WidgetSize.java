package com.reportportal.services.api.dashboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WidgetSize{

	@JsonProperty("width")
	private int width;

	@JsonProperty("height")
	private int height;
}