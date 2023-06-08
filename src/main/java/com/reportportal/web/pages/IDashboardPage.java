package com.reportportal.web.pages;

public interface IDashboardPage extends IPage {

    String PAGE_PATH = "/dashboard";

    String ADD_NEW_WIDGET_XPATH = "//span[text()='Add new widget']";
    String WIDGET_TYPE_XPATH = "//div[text()='%s']";
    String NEXT_STEP_BUTTON_XPATH = "//span[text()='Next step']";
    String WIDGET_WIZARD_DEMO_FILTER_XPATH = "//div[contains(@class,'widgetWizardModal')]//span[text()='DEMO_FILTER']";
    String ATTRIBUTE_KEY_FIELD_XPATH = "//input[@placeholder='Enter an attribute key']";
    String WIDGET_NAME_FIELD_XPATH = "//input[@placeholder='Enter widget name']";
    String ADD_WIDGET_BUTTON_XPATH = "//button[text()='Add']";
    String WIDGET_BLOCK_BY_NAME_XPATH = "//div[contains(@class,'widgetHeader') and text()='%s']";

    IDashboardPage addNewWidget(String widgetType, String widgetName);
    boolean isWidgetPresentOnDashboard(String widgetName);
}
