package com.reportportal.web.pages.selenium;

import com.reportportal.web.pages.IAllDashboardsPage;
import com.reportportal.web.pages.selenide.AllDashboardsPage;
import org.openqa.selenium.By;

import static com.reportportal.utils.DriverManager.getDriver;
import static java.lang.String.format;

public class AllDashboardsPageSelenium extends BaseWebPageSelenium implements IAllDashboardsPage {

    @Override
    public AllDashboardsPage open() {
        getDriver().get(getPagePath());
        waitForPageToLoad();
        return new AllDashboardsPage();
    }

    @Override
    public boolean isCurrentlyOpened() {
            return getDriver().getCurrentUrl().contains(getPageUrl());
    }

    public boolean isWidgetPresentOnDashboard(String widgetName) {
        return false;
    }

    @Override
    public boolean isPageOpenedUnderUser(String user) {
        return getDriver().getCurrentUrl().contains(user);
    }

    @Override
    public String getPagePath() {
        return PAGE_PATH;
    }

    public DashboardPageSelenium openDashboard(String dashbordName) {
        getDriver().findElement(By.xpath(format(DASHBOARD_PAGE_BY_NAME_XPATH, dashbordName))).click();
        return new DashboardPageSelenium();
    }

    public boolean isDashboardPresent(String dashboardName) {
        return getDriver().findElements(By.xpath(format(DASHBOARD_PAGE_BY_NAME_XPATH, dashboardName))).size() > 0;
    }
}
