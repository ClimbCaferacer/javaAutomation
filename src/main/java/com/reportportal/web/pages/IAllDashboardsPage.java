package com.reportportal.web.pages;

public interface IAllDashboardsPage extends IPage {

    static final String PAGE_PATH = "/dashboard";
    public static final String DASHBOARD_PAGE_BY_NAME_XPATH = "//div[contains(@class,'gridRow')]/a[text()='%s']";

    IDashboardPage openDashboard(String dashboardName);

    boolean isDashboardPresent(String dashboardName);

    boolean isPageOpenedUnderUser(String login);

    boolean isCurrentlyOpened();
}
