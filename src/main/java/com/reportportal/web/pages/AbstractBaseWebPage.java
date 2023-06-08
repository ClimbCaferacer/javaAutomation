package com.reportportal.web.pages;

public abstract class AbstractBaseWebPage extends AbstractPage implements IPage {

    public static final String AVATAR_XPATH = "//div[contains(@class, 'sidebar__bottom-block')]//img[@alt='avatar']";
    public static final String LOGOUT_XPATH = "//div[text()='Logout']";


}
