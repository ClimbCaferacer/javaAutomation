package com.reportportal.definitions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.reportportal.configuration.CommonProps;
import com.reportportal.services.api.dashboard.DashboardAPI;
import com.reportportal.web.pages.IAllDashboardsPage;
import com.reportportal.web.pages.IDashboardPage;
import com.reportportal.web.pages.ILoginPage;
import com.reportportal.web.pages.selenide.AllDashboardsPage;
import com.reportportal.web.pages.selenide.DashboardPage;
import com.reportportal.web.pages.selenide.LoginPage;
import com.reportportal.web.pages.selenium.AllDashboardsPageSelenium;
import com.reportportal.web.pages.selenium.DashboardPageSelenium;
import com.reportportal.web.pages.selenium.LoginPageSelenium;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

import static com.reportportal.utils.DriverManager.closeDriver;
import static com.reportportal.utils.DriverManager.getDriver;


@Slf4j
public class CucumberHooks {

    public static final CommonProps PROPS = ConfigFactory.create(CommonProps.class);
    static ILoginPage loginPage;
    static IAllDashboardsPage allDashboardsPage;
    static IDashboardPage dashboardPage;

    DashboardAPI dashboardAPI = new DashboardAPI();


    @BeforeAll(order = 0)
    public static void before_all() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new SessionFilter(),
                new CookieFilter(),
                new ErrorLoggingFilter());
        if ("selenium".equals(PROPS.driver())) {
            loginPage = new LoginPageSelenium();
            allDashboardsPage = new AllDashboardsPageSelenium();
            dashboardPage = new DashboardPageSelenium();
            System.out.println("Using Selenium driver");
        } else {
            WebDriverRunner.setWebDriver(getDriver());
            loginPage = new LoginPage();
            allDashboardsPage = new AllDashboardsPage();
            dashboardPage = new DashboardPage();
            System.out.println("Using Selenide driver");
        }
        //Configuration.browserSize = "1920x1080";


    }

    @Before
    public void beforeScenario() {
        loginPage.open();
    }

    @After
    public void afterScenario() {
        if ("selenium".equals(PROPS.driver())) {
            closeDriver();
        } else {
            Selenide.closeWebDriver();
        }
    }

    @After("@deleteWidget")
    public void deleteWidget() {
        var widgetId = dashboardAPI.getDashboardById(PROPS.defaultProjectName(), 17).asDto()
                .getWidgets().stream()
                .filter(widget -> widget.getWidgetName().contains("NEW WIDGET"))
                .findFirst().get().getWidgetId();
        dashboardAPI.deleteWidget(PROPS.defaultProjectName(), 17, widgetId);
    }

    @AfterAll
    public static void after() {
        //getDriver().quit();
    }
}
