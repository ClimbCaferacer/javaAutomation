package com.reportportal;

import com.reportportal.services.api.dashboard.DashboardAPI;
import com.reportportal.services.api.widget.WidgetAPI;
import com.reportportal.web.pages.IAllDashboardsPage;
import com.reportportal.web.pages.IDashboardPage;
import com.reportportal.web.pages.ILoginPage;

public abstract class BaseTest {

    protected ILoginPage loginPage;
    protected IAllDashboardsPage allDashboardsPage;
    protected IDashboardPage dashboardPage;

    protected final WidgetAPI widgetAPI = new WidgetAPI();
    protected final DashboardAPI dashboardAPI = new DashboardAPI();





}
