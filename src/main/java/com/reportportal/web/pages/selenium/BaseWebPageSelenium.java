package com.reportportal.web.pages.selenium;

import com.reportportal.web.pages.AbstractBaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static com.reportportal.utils.DriverManager.getDriver;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


public abstract class BaseWebPageSelenium extends AbstractBaseWebPage {

    public void logout() {
        getDriver().findElement(By.xpath(AVATAR_XPATH)).click();
        getDriver().findElement(By.xpath(LOGOUT_XPATH)).click();
    }
    public void waitForPageToLoad() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
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
