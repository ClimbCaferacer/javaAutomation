package com.reportportal.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class LoginPage extends AbstractPage {

    private static final String PAGE_PATH = "/ui/#login";

    @Override
    public String getPagePath() {
        return PAGE_PATH;
    }

    public SelenideElement emailInputFieldLocator() {
        return $x("//input[@name='login']");
    }

    public SelenideElement passInputFieldLocator() {
        return $x("//input[@name='password']").shouldBe(Condition.exist,Condition.visible);
    }

    public SelenideElement submitButtonLocator() {
        return $x("//button[@type='submit']").shouldBe(Condition.exist,Condition.visible);
    }

    public void login(String mail, String pass) {
        emailInputFieldLocator().sendKeys(mail);
        passInputFieldLocator().sendKeys(pass);
        submitButtonLocator().click();
    }
}
