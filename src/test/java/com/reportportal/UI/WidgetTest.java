package com.reportportal.UI;

import com.reportportal.BaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class WidgetTest extends BaseTest {

    @Test
    public void createWidgetTest() {
        loginPage.open();
        loginPage.login("superadmin", "erebus");
        allDashboardsPage.waitForPageToLoad();
        allDashboardsPage.openDashboard("DEMO DASHBOARD");
        var widgetName = dashboardPage.addNewWidget("Component health check", "NEW WIDGET");
        Assertions.assertThat(dashboardPage.isWidgetPresentOnDashboard(widgetName))
                .as("Widget not created").isTrue();


    }


}
