package com.reportportal.services.api.widget.edit.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FiltersItem{
	private String name;
	private String value;
}