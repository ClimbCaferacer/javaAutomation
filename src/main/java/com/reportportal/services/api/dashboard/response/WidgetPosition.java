package com.reportportal.services.api.dashboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WidgetPosition{

	@JsonProperty("positionY")
	private int positionY;

	@JsonProperty("positionX")
	private int positionX;
}