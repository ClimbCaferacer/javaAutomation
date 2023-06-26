package com.reportportal;

import com.reportportal.services.api.dashboard.DashboardAPI;
import com.reportportal.services.api.widget.WidgetAPI;
import com.reportportal.web.pages.IAllDashboardsPage;
import com.reportportal.web.pages.IDashboardPage;
import com.reportportal.web.pages.ILoginPage;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected ILoginPage loginPage;
    protected IAllDashboardsPage allDashboardsPage;
    protected IDashboardPage dashboardPage;

    protected final WidgetAPI widgetAPI = new WidgetAPI();
    protected final DashboardAPI dashboardAPI = new DashboardAPI();


    @BeforeAll
    public static void before_all() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new SessionFilter(),
                new CookieFilter(),
                new ErrorLoggingFilter());
    }



}
