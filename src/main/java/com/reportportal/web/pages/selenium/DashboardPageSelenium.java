package com.reportportal.web.pages.selenium;

import com.reportportal.web.pages.IDashboardPage;
import com.reportportal.web.pages.IPage;
import org.openqa.selenium.By;

import static com.reportportal.utils.DriverManager.getDriver;
import static java.lang.String.format;

public class DashboardPageSelenium extends BaseWebPageSelenium implements IDashboardPage {

    @Override
    public DashboardPageSelenium addNewWidget(String widgetType, String widgetName) {

        getDriver().findElement(By.xpath(ADD_NEW_WIDGET_XPATH)).click();
        getDriver().findElement(By.xpath(format(WIDGET_TYPE_XPATH, widgetType))).click();
        waitForPageToLoad();
        getDriver().findElement(By.xpath(NEXT_STEP_BUTTON_XPATH)).click();
        waitForPageToLoad();
        getDriver().findElement(By.xpath(WIDGET_WIZARD_DEMO_FILTER_XPATH)).click();

        if(widgetType.equals("Component health check")) {
            getDriver().findElement(By.xpath(ATTRIBUTE_KEY_FIELD_XPATH)).sendKeys("key");
        }
        getDriver().findElement(By.xpath(NEXT_STEP_BUTTON_XPATH)).click();

        getDriver().findElement(By.xpath(WIDGET_NAME_FIELD_XPATH)).clear();
        getDriver().findElement(By.xpath(WIDGET_NAME_FIELD_XPATH)).sendKeys(widgetName);
        getDriver().findElement(By.xpath(ADD_WIDGET_BUTTON_XPATH)).click();
        waitForPageToLoad();
        return new DashboardPageSelenium();
    }

    public boolean isWidgetPresentOnDashboard(String widgetName) {
        return getDriver().findElement(By.xpath(format(WIDGET_BLOCK_BY_NAME_XPATH, widgetName))).isDisplayed();
    }

    @Override
    public IPage open() {

        return null;
    }

    @Override
    public boolean isPageOpenedUnderUser(String login) {
        return false;
    }

    @Override
    public boolean isCurrentlyOpened() {
        return false;
    }
}
