package com.reportportal.web.pages;

public interface IPage {

    int SECONDS_FOR_PAGE_LOAD = 30;
    int POLL_INTERVAL = 1;

    IPage open();
    void waitForPageToLoad();

    boolean isPageOpenedUnderUser(String login);

    boolean isCurrentlyOpened();
}
