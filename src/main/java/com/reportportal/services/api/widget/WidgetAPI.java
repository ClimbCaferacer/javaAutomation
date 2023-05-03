package com.reportportal.services.api.widget;

import com.reportportal.services.api.AbstractReportPortalAPI;
import com.reportportal.services.api.AbstractResponseWrapper;
import com.reportportal.services.api.widget.create.request.ContentParameters;
import com.reportportal.services.api.widget.create.request.CreateWidgetRequest;
import com.reportportal.services.api.widget.create.request.Filters;
import com.reportportal.services.api.widget.create.response.PostWidgetResponse;
import com.reportportal.services.api.widget.get.response.GetWidgetResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class WidgetAPI extends AbstractReportPortalAPI {

    public AbstractResponseWrapper<GetWidgetResponse> getWidgetById(String projectName, int widgetId) {
        return new AbstractResponseWrapper<>(
                getClient()
                        .get(Endpoint.WIDGET, projectName, widgetId)){
        };
    }

    public AbstractResponseWrapper<PostWidgetResponse> createWidget(String projectName, String widgetName) {
        return new AbstractResponseWrapper<>(
                getClient().body(CreateWidgetRequest.builder()
                                .filterIds(List.of("4"))
                                .widgetType("launchesTable")
                                .contentParameters(ContentParameters.builder()
                                        .contentFields(List.of("name", "number", "status", "statistics$executions$total", "statistics$executions$passed", "statistics$executions$skipped", "statistics$executions$failed"))
                                        .itemsCount(50)
                                        .build())
                                .name(widgetName + RandomStringUtils.random(5))
                                .description("Description")
                                .filters(List.of(Filters.builder().name("DEMO_FILTER").value("4").build()))
                                .share(true)
                                .build())
                        .post(Endpoint.POST_WIDGET, projectName)) {
        };
    }


    private static final class Endpoint {
        public static final String WIDGET = "/api/v1/{projectName}/widget/{widgetId}";
        public static final String POST_WIDGET = "/api/v1/{projectName}/widget";
    }
}
