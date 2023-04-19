package com.reportportal.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public abstract class AbstractPage {
    private static final String PATH = "/";
    private static final int SECONDS_FOR_PAGE_LOAD = 30;
    private static final int POLL_INTERVAL = 1;

    public void open() {
        Selenide.open(getPagePath());
    }

    public void openPage(String path) {
        Selenide.open(path);
    }

    public void refresh(){
        Selenide.refresh();
    }

    public boolean isCurrentlyOpened() {
        return WebDriverRunner.getWebDriver().getCurrentUrl().contains(getPageUrl());
    }

    public boolean isPageOpenedUnderUser(String user) {
        return WebDriverRunner.getWebDriver().getCurrentUrl().contains(user);
    }

    public String getPageTitle() {
        return Selenide.title();
    }

    protected String getPageUrl() {
        return getPagePath();
    }

    protected String getPagePath() {
        return PATH;
    }

    public void waitForPageToLoad() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        boolean isJQuery = (boolean) js
                .executeScript("if (window.jQuery) { return true; } else { return false; }");
        await().atMost(SECONDS_FOR_PAGE_LOAD, SECONDS).pollInterval(POLL_INTERVAL, SECONDS).until(() -> {
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                return !isJQuery || (Long) js.executeScript("return jQuery.active") == 0;
            }
            return false;
        });
    }
}
