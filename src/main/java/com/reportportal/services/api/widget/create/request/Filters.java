package com.reportportal.services.api.widget.create.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Filters {
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
}
