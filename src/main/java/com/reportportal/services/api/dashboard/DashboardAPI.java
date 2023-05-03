package com.reportportal.services.api.dashboard;

import com.reportportal.services.api.AbstractReportPortalAPI;
import com.reportportal.services.api.AbstractResponseWrapper;
import com.reportportal.services.api.dashboard.response.GetDashboardResponse;
import com.reportportal.services.api.widget.delete.response.DeleteWidgetResponse;

public class DashboardAPI extends AbstractReportPortalAPI {

    public AbstractResponseWrapper<GetDashboardResponse> getDashboardById(String projectName, int dashboardId) {
        return new AbstractResponseWrapper<>(
                getClient()
                        .get(Endpoint.GET_DASHBOARD, projectName, dashboardId)){
        };
    }

    public AbstractResponseWrapper<DeleteWidgetResponse> deleteWidget(String projectName, int dashboardId, int widgetId) {
        return new AbstractResponseWrapper<>(
                getClient().delete(Endpoint.DELETE_WIDGET, projectName, dashboardId, widgetId)
        ) {
        };
    }
    private static final class Endpoint {
        public static final String GET_DASHBOARD = "/api/v1/{projectName}/dashboard/{dashboardId}";

        public static final String DELETE_WIDGET = "/api/v1/{projectName}/dashboard/{dashboardId}/{widgetId}";
    }
}
