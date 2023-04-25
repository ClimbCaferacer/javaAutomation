package com.reportportal.UI;

import com.reportportal.BaseTest;
import com.reportportal.web.pages.AllDashboardsPage;
import com.reportportal.web.pages.DashboardPage;
import com.reportportal.web.pages.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class WidgetTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    AllDashboardsPage allDashboardsPage = new AllDashboardsPage();
    DashboardPage dashboardPage = new DashboardPage();
    @Test
    public void createWidgetTest() {
        loginPage.open();
        loginPage.login("superadmin", "erebus");
        allDashboardsPage.waitForPageToLoad();
        allDashboardsPage.openDashboard("DEMO DASHBOARD");
        var widgetName = dashboardPage.addNewWidget("Component health check");
        Assertions.assertThat(dashboardPage.isWidgetPresentOnDashboard(widgetName))
                .as("Widget not created").isTrue();


    }


}
