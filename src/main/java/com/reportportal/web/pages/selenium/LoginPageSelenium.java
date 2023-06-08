package com.reportportal.web.pages.selenium;

import com.reportportal.web.pages.ILoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.reportportal.utils.DriverManager.getDriver;


public class LoginPageSelenium extends BaseWebPageSelenium implements ILoginPage {



    @Override
    public LoginPageSelenium open() {
        getDriver().get(PROPS.baseUrl() + getPagePath());
        waitForPageToLoad();
        return new LoginPageSelenium();
    }

    @Override
    public boolean isPageOpenedUnderUser(String login) {
        return false;
    }

    @Override
    public boolean isCurrentlyOpened() {
        return false;
    }

    @Override
    public String getPagePath() {
        return PAGE_PATH;
    }

    @Override
    public WebElement emailInputFieldLocator() {
        return getDriver().findElement(By.xpath(LOGIN_FIELD_XPATH));
    }
    @Override
    public WebElement passInputFieldLocator() {
        return getDriver().findElement(By.xpath(PASSWORD_FIELD_XPATH));
    }
    @Override
    public WebElement submitButtonLocator() {
        return getDriver().findElement(By.xpath(SUBMIT_BUTTON_XPATH));
    }

}
