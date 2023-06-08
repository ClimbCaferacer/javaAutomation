package com.reportportal.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import static com.reportportal.definitions.CucumberHooks.*;

public class StepDefinitions {

    @Given("User with {string} and {string} authenticate to report portal")
    public void user_with_and_authenticate_to_report_portal(String login, String password) {
        loginPage.login(login, password);
    }

    @When("User opens dashboard {string}")
    public void userOpensDashboard(String dashboardName) {
        allDashboardsPage.openDashboard(dashboardName);
    }

    @And("Creates new widget {string} with name {string}")
    public void createsNewWidget(String widgetType, String widgetName) {
        dashboardPage.addNewWidget(widgetType, widgetName);
    }

    @Then("{string} present on dashboard")
    public void presentOnDashboard(String widgetName) {
        Assertions.assertThat(dashboardPage.isWidgetPresentOnDashboard(widgetName))
                .as("Widget not created").isTrue();
    }

    @Then("User {string} navigated to dashboards page")
    public void userNavigatedToDashboardsPage(String login) {
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
