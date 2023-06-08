package com.reportportal.web.pages.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.reportportal.web.pages.ILoginPage;

import static com.codeborne.selenide.Selenide.$x;


public class LoginPage extends BaseWebPage implements ILoginPage {

    private static final String PAGE_PATH = "/ui/#login";

    @Override
    public LoginPage open() {
        Selenide.open(getPagePath());
        waitForPageToLoad();
        return new LoginPage();
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

    public SelenideElement emailInputFieldLocator() {
        return $x(LOGIN_FIELD_XPATH);
    }

    public SelenideElement passInputFieldLocator() {
        return $x(PASSWORD_FIELD_XPATH).shouldBe(Condition.exist,Condition.visible);
    }

    public SelenideElement submitButtonLocator() {
        return $x(SUBMIT_BUTTON_XPATH).shouldBe(Condition.exist,Condition.visible);
    }
}
