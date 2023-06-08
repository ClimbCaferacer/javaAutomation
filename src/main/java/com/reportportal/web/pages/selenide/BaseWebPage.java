package com.reportportal.web.pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.reportportal.web.pages.AbstractBaseWebPage;
import com.reportportal.web.pages.IPage;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$x;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public abstract class BaseWebPage extends AbstractBaseWebPage implements IPage {

    static{
        Configuration.baseUrl = PROPS.baseUrl();
    }

    public void logout() {
        $x(AVATAR_XPATH).click();
        $x(LOGOUT_XPATH).shouldBe(Condition.visible).click();
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
