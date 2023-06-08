package com.reportportal.configuration.web.driver;

import com.reportportal.configuration.web.IFramework;
import org.openqa.selenium.WebDriver;

public class SeleniumContext implements IFramework {

    private WebDriver driver;

    public SeleniumContext(WebDriver driver) {
        this.driver = driver;
    }
}
