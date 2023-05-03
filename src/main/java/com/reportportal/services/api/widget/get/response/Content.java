package com.reportportal.services.api.widget.get.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Content{

	@JsonProperty("result")
	private List<ResultItem> result;
}