package com.reportportal.services.widget.get.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetWidgetResponse {
    @JsonProperty("owner")
    private String owner;

    @JsonProperty("appliedFilters")
    private List<AppliedFiltersItem> appliedFilters;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("share")
    private boolean share;

    @JsonProperty("id")
    private int id;

    @JsonProperty("contentParameters")
    private ContentParameters contentParameters;

    @JsonProperty("widgetType")
    private String widgetType;

    @JsonProperty("content")
    private Content content;
}
