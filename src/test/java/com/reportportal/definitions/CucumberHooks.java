package com.reportportal.definitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.reportportal.configuration.CommonProps;
import com.reportportal.services.api.dashboard.DashboardAPI;
import com.reportportal.web.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import org.aeonbits.owner.ConfigFactory;

import java.util.Objects;


public class CucumberHooks {

    public static final CommonProps PROPS = ConfigFactory.create(CommonProps.class);
    LoginPage loginPage = new LoginPage();
    DashboardAPI dashboardAPI = new DashboardAPI();


    @Before(order = 0)
    public void before() {
        Configuration.baseUrl = PROPS.baseUrl();
        Configuration.browserSize = "1920x1080";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new SessionFilter(),
                new CookieFilter(),
                new ErrorLoggingFilter());
        loginPage.open();
        loginPage.waitForPageToLoad();
    }

    @After("@CreateWidget")
    public void deleteWidget() {
        var widgetId = dashboardAPI.getDashboardById("superadmin_personal", 17).asDto()
                .getWidgets().stream()
                .filter(widget -> Objects.equals(widget.getWidgetName(), "NEW WIDGET"))
                .findFirst().get().getWidgetId();
        dashboardAPI.deleteWidget("superadmin_personal", 17, widgetId);
    }

    @After
    public void after() {
        Selenide.closeWebDriver();
    }
}
