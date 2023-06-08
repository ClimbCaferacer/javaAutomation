package com.reportportal.web.pages;

import org.openqa.selenium.WebElement;


public interface ILoginPage extends IPage {

    String PAGE_PATH = "/ui/#login";
    String LOGIN_FIELD_XPATH = "//input[@name='login']";
    String PASSWORD_FIELD_XPATH = "//input[@name='password']";
    String SUBMIT_BUTTON_XPATH = "//button[@type='submit']";

    default void login(String mail, String pass) {
        emailInputFieldLocator().sendKeys(mail);
        passInputFieldLocator().sendKeys(pass);
        submitButtonLocator().click();
        waitForPageToLoad();
    }

    WebElement emailInputFieldLocator();
    WebElement passInputFieldLocator();

    WebElement submitButtonLocator();

    boolean isCurrentlyOpened();

    boolean isPageOpenedUnderUser(String login);

}
