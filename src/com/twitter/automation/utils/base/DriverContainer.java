package com.twitter.automation.utils.base;

import org.openqa.selenium.WebDriver;

public abstract class DriverContainer {
    private WebDriver driver = BaseTest.driver();

    protected WebDriver driver() {
        return driver;
    }
}
