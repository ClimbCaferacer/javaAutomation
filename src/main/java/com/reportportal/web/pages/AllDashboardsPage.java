package com.reportportal.web.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class AllDashboardsPage extends BaseWebPage {

    private static final String PAGE_PATH = "/dashboard";

    @Override
    public String getPagePath() {
        return PAGE_PATH;
    }

    public void openDashboard(String dashbordName) {
        $x("//div[contains(@class,'gridRow')]/a[text()='DEMO DASHBOARD']").shouldBe(Condition.visible).click();
    }
}
