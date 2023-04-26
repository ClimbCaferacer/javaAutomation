package com.reportportal.web.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class BaseWebPage extends AbstractPage {

    public void logout() {
        $x("//div[contains(@class, 'sidebar__bottom-block')]//img[@alt='avatar']").click();
        $x("//div[text()='Logout']").shouldBe(Condition.visible).click();
    }
}
