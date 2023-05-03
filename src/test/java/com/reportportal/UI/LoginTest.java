package com.reportportal.UI;

import com.codeborne.selenide.Selenide;
import com.reportportal.BaseTest;
import com.reportportal.web.pages.AllDashboardsPage;
import com.reportportal.web.pages.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] users() {

        return new Object[][]{
                {"superadmin", "erebus"},
                {"default", "1q2w3e"}
        };
    }

    @Test(dataProvider = "users")
    public void loginTest(String login, String password) {
        loginPage.open();
        loginPage.login(login, password);
        allDashboardsPage.waitForPageToLoad();
        Assertions.assertThat(allDashboardsPage.isCurrentlyOpened())
                .as("<All Dashboards> page not opened.").isTrue();
        Assertions.assertThat(allDashboardsPage.isPageOpenedUnderUser(login))
                .as("Incorrect user").isTrue();

    }

    @AfterMethod
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
