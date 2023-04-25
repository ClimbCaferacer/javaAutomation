package com.reportportal;

import com.codeborne.selenide.Configuration;
import com.reportportal.configuration.CommonProps;
import com.reportportal.services.RestAssuredConsoleFilter;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    public static final CommonProps PROPS = ConfigFactory.create(CommonProps.class);

    @BeforeClass
    public void setWebDriver() {
        Configuration.baseUrl = PROPS.baseUrl();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new SessionFilter(),
                new CookieFilter(),
                new ErrorLoggingFilter());
    }
}
