package com.reportportal.web.pages;

import com.codeborne.selenide.Selenide;
import com.reportportal.configuration.CommonProps;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

@Slf4j
public abstract class AbstractPage {

    private static final String PATH = "////";
    public static final CommonProps PROPS = ConfigFactory.create(CommonProps.class);

    public void openPage(String path) {
        //Selenide.open(path);
    }

    public void refresh(){
        //Selenide.refresh();
    }

    public String getPageTitle() {
        return Selenide.title();
    }

    protected String getPageUrl() {
        return getPagePath();
    }

    protected String getPagePath() {
        return PATH;
    }

/*    @BeforeSuite
    public void setWebDriver() {
        Configuration.baseUrl = PROPS.baseUrl();



        //api
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new SessionFilter(),
                new CookieFilter(),
                new ErrorLoggingFilter());
    }*/
}
