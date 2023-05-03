package com.reportportal.definitions;

import com.reportportal.services.api.dashboard.DashboardAPI;
import com.reportportal.web.pages.AllDashboardsPage;
import com.reportportal.web.pages.DashboardPage;
import com.reportportal.web.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class StepDefinitions {

    LoginPage loginPage = new LoginPage();
    AllDashboardsPage allDashboardsPage = new AllDashboardsPage();
    DashboardPage dashboardPage = new DashboardPage();

    DashboardAPI dashboardAPI = new DashboardAPI();

    @Given("User with {string} and {string} authenticate to report portal")
    public void user_with_and_authenticate_to_report_portal(String login, String password) {
        loginPage.login(login, password);

    }

    @When("User opens dashboard {string}")
    public void userOpensDashboard(String dashboardName) {
        allDashboardsPage.waitForPageToLoad();
        allDashboardsPage.openDashboard(dashboardName);
    }

    @And("Creates new widget {string}")
    public void createsNewWidget(String widgetName) {
        dashboardPage.addNewWidget("Component health check", widgetName);
    }

    @Then("{string} present on dashboard")
    public void presentOnDashboard(String widgetName) {
        dashboardPage.waitForPageToLoad();
        Assertions.assertThat(dashboardPage.isWidgetPresentOnDashboard(widgetName))
                .as("Widget not created").isTrue();
    }

    @Then("User {string} navigated to dashboards page")
    public void userNavigatedToDashboardsPage(String login) {
        allDashboardsPage.waitForPageToLoad();
        Assertions.assertThat(allDashboardsPage.isCurrentlyOpened())
                .as("<All Dashboards> page not opened.").isTrue();
        Assertions.assertThat(allDashboardsPage.isPageOpenedUnderUser(login))
                .as("Incorrect user").isTrue();
    }

    @Then("{string} not present on dashboard")
    public void notPresentOnDashboard(String dashboardName) {
        Assertions.assertThat(allDashboardsPage.isDashboardPresent(dashboardName))
                .as("Dashboard %s should not be present", dashboardName).isFalse();
    }
}
