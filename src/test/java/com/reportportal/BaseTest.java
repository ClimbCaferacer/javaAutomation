package com.reportportal;

import com.codeborne.selenide.Configuration;
import com.reportportal.configuration.CommonProps;
import com.reportportal.services.api.dashboard.DashboardAPI;
import com.reportportal.services.api.widget.WidgetAPI;
import com.reportportal.web.pages.AllDashboardsPage;
import com.reportportal.web.pages.DashboardPage;
import com.reportportal.web.pages.LoginPage;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    protected LoginPage loginPage = new LoginPage();
    protected AllDashboardsPage allDashboardsPage = new AllDashboardsPage();
    protected DashboardPage dashboardPage = new DashboardPage();

    protected final WidgetAPI widgetAPI = new WidgetAPI();
    protected final DashboardAPI dashboardAPI = new DashboardAPI();

    public static final CommonProps PROPS = ConfigFactory.create(CommonProps.class);

    @BeforeSuite
    public void setWebDriver() {
        Configuration.baseUrl = PROPS.baseUrl();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new SessionFilter(),
                new CookieFilter(),
                new ErrorLoggingFilter());
    }
}
