package com.reportportal.services.api.widget.delete.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteWidgetResponse {

    @JsonProperty("message")
    String message;
}
