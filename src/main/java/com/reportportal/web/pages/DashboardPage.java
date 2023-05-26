package com.reportportal.web.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class DashboardPage extends BaseWebPage {

    private static final String PAGE_PATH = "/dashboard";

    public String addNewWidget(String widgetType, String widgetName) {
        $x("//span[text()='Add new widget']").shouldBe(Condition.visible).click();
        $x(format("//div[text()='%s']", widgetType)).shouldBe(Condition.visible).click();
        waitForPageToLoad();
        $x("//span[text()='Next step']").shouldBe(Condition.visible).click();

        $x("//div[contains(@class,'widgetWizardModal')]//span[text()='DEMO_FILTER']").shouldBe(Condition.visible).click();

        if(widgetType.equals("Component health check")) {
            $x("//input[@placeholder='Enter an attribute key']").shouldBe(Condition.visible).sendKeys("key");
        }
        $x("//span[text()='Next step']").shouldBe(Condition.visible).click();

        $x("//input[@placeholder='Enter widget name']").shouldBe(Condition.visible).clear();
        $x("//input[@placeholder='Enter widget name']").sendKeys(widgetName);
        $x("//button[text()='Add']").shouldBe(Condition.visible).click();
        waitForPageToLoad();
        return widgetName;
    }

    public boolean isWidgetPresentOnDashboard(String widgetName) {
        return $x(format("//div[contains(@class,'widgetHeader') and text()='%s']", widgetName)).exists();
    }
}
