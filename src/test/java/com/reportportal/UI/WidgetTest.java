package com.reportportal.UI;

import com.reportportal.BaseTest;
import org.assertj.core.api.Assertions;


public class WidgetTest extends BaseTest {

    //@Test
    public void createWidgetTest() {
        loginPage.open();
        loginPage.login("superadmin", "erebus");
        loginPage.waitForPageToLoad();
        allDashboardsPage.openDashboard("DEMO DASHBOARD");
        dashboardPage.addNewWidget("Component health check", "NEW WIDGET");
        Assertions.assertThat(dashboardPage.isWidgetPresentOnDashboard("NEW WIDGET"))
                .as("Widget not created").isTrue();


    }


}
