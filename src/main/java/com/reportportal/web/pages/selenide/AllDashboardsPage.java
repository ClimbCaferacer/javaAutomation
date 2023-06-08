package com.reportportal.web.pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.reportportal.web.pages.IAllDashboardsPage;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class AllDashboardsPage extends BaseWebPage implements IAllDashboardsPage {

    @Override
    public AllDashboardsPage open() {
        Selenide.open(PAGE_PATH);
        return new AllDashboardsPage();
    }

    @Override
    public boolean isPageOpenedUnderUser(String user) {
        return WebDriverRunner.getWebDriver().getCurrentUrl().contains(user);
    }

    @Override
    public boolean isCurrentlyOpened() {
        return WebDriverRunner.getWebDriver().getCurrentUrl().contains(getPageUrl());
    }

    public boolean isWidgetPresentOnDashboard(String widgetName) {
        return false;
    }

    @Override
    public String getPagePath() {
        return PAGE_PATH;
    }

    public DashboardPage openDashboard(String dashboardName) {
        $x(format(DASHBOARD_PAGE_BY_NAME_XPATH, dashboardName)).shouldBe(Condition.visible).click();
        waitForPageToLoad();
        return new DashboardPage();
    }

    public boolean isDashboardPresent(String dashboardName) {
        return $x(format(DASHBOARD_PAGE_BY_NAME_XPATH, dashboardName)).isDisplayed();
    }
}
