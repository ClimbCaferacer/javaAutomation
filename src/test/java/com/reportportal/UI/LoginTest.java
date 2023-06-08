package com.reportportal.UI;

import com.codeborne.selenide.Selenide;
import com.reportportal.BaseTest;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.cucumber.java.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] users() {

        return new Object[][]{
                {"superadmin", "erebus"},
                {"default", "1q2w3e"}
        };
    }

    @Test
    @UseDataProvider("users")
    public void loginTest(String login, String password) {
        loginPage.open();
        loginPage.login(login, password);
        loginPage.waitForPageToLoad();
        Assertions.assertThat(allDashboardsPage.isCurrentlyOpened())
                .as("<All Dashboards> page not opened.").isTrue();
        Assertions.assertThat(allDashboardsPage.isPageOpenedUnderUser(login))
                .as("Incorrect user").isTrue();

    }

    @After
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
