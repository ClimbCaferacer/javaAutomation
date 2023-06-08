package com.reportportal.web.pages.selenide;

import com.codeborne.selenide.Condition;
import com.reportportal.web.pages.IDashboardPage;
import com.reportportal.web.pages.IPage;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class DashboardPage extends BaseWebPage implements IDashboardPage {

    private static final String PAGE_PATH = "/dashboard";

    public DashboardPage addNewWidget(String widgetType, String widgetName) {
        $x(ADD_NEW_WIDGET_XPATH).shouldBe(Condition.visible).click();
        $x(format(WIDGET_TYPE_XPATH, widgetType)).shouldBe(Condition.visible).click();
        waitForPageToLoad();
        $x(NEXT_STEP_BUTTON_XPATH).shouldBe(Condition.visible).click();

        $x(WIDGET_WIZARD_DEMO_FILTER_XPATH).shouldBe(Condition.visible).click();

        if(widgetType.equals("Component health check")) {
            $x(ATTRIBUTE_KEY_FIELD_XPATH).shouldBe(Condition.visible).sendKeys("key");
        }
        $x(NEXT_STEP_BUTTON_XPATH).shouldBe(Condition.visible).click();

        $x(WIDGET_NAME_FIELD_XPATH).shouldBe(Condition.visible).clear();
        $x(WIDGET_NAME_FIELD_XPATH).sendKeys(widgetName);
        $x(ADD_WIDGET_BUTTON_XPATH).shouldBe(Condition.visible).click();
        waitForPageToLoad();
        return new DashboardPage();
    }

    public boolean isWidgetPresentOnDashboard(String widgetName) {
        return $x(format(WIDGET_BLOCK_BY_NAME_XPATH, widgetName)).exists();
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
