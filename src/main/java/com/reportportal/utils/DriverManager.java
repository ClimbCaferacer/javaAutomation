package com.reportportal.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if(driver.get() == null) {
            create();
        }
        return driver.get();
    }

    public static void create() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-build-check");
        options.addArguments("--disable-dev-shm-usage");
        ChromeDriverService service = ChromeDriverService.createDefaultService();
        driver.set(new ChromeDriver(service, options));
    }

    public static void closeDriver() {
        System.out.println("removing driver");
        if(driver.get() != null) {
            driver.get().close();
        }
        driver.remove();
    }
}
