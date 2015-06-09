package com.twitter.automation.utils.base;

import com.twitter.automation.utils.reporting.Reporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage extends DriverContainer {
    public static int ELEMENT_MICRO_TIMEOUT_SECONDS = 2;
    public static int ELEMENT_EXTRASMALL_TIMEOUT_SECONDS = 5;
    public static int ELEMENT_SMALL_TIMEOUT_SECONDS = 15;
    public static int ELEMENT_TIMEOUT_SECONDS = 60;
    public static int ELEMENT_LONG_TIMEOUT_SECONDS = 120;
    public static int ELEMENT_EXTRALONG_TIMEOUT_SECONDS = 180;
    public static int ELEMENT_MEGA_EXTRALONG_TIMEOUT_SECONDS = 360;

    public static String BASE_URL = "https://twitter.com/";

    protected void click(String message, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement element = driver().findElement(locator.getLocator(args));
        element.click();
    }

    protected void type(String message, String value, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement input = driver().findElement(locator.getLocator(args));
        input.clear();
        input.sendKeys(value);
    }

    protected void waitForVisibility(String message, Locator locator, Object... args) {
        waitForVisibility(ELEMENT_TIMEOUT_SECONDS, message, locator, args);
    }

    protected void waitForVisibility(int timeout, String message, Locator locator, Object... args) {
        Reporter.log(message);

        driver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getLocator(args)));
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }


}
