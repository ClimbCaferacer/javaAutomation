package com.reportportal.configuration.web.driver;

import com.reportportal.configuration.web.IFramework;
import org.openqa.selenium.WebDriver;

public class SelenideContext implements IFramework {

    public SelenideContext(WebDriver driver) {
        //WebDriverRunner.setWebDriver(driver);
    }
}
