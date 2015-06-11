package com.twitter.automation.utils.base;

import com.twitter.automation.utils.reporting.Reporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    protected String getText(String message, Locator locator, Object... args) {
        Reporter.log(message);

        WebElement element = driver().findElement(locator.getLocator(args));
        String type = element.getTagName().toLowerCase();

        if (type.equals("input") || type.equals("textarea")) {
            String placeholder = element.getAttribute("placeholder");
            return (placeholder != null && placeholder.length() > 0)
                    ? element.getAttribute("value").replace(placeholder, "")
                    : element.getAttribute("value");
        }
        if (type.equals("select")) {
            return new Select(element).getFirstSelectedOption().getText();
        }
        return element.getText();
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

    protected void waitForInvisibility(String message, Locator locator, Object... args) {
        waitForInvisibility(ELEMENT_TIMEOUT_SECONDS, message, locator, args);
    }

    protected void waitForInvisibility(int timeout, String message, Locator locator, Object... args) {
        Reporter.log(message);

        driver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getLocator(args)));
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void waitToBeClickable(String message, Locator locator, Object... args) {
        waitToBeClickable(ELEMENT_TIMEOUT_SECONDS, message, locator, args);
    }

    protected void waitToBeClickable(int timeout, String message, Locator locator, Object... args) {
        Reporter.log(message);

        driver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator.getLocator(args)));
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        // wait until the element on the same place
        WebElement element = driver().findElement(locator.getLocator(args));
        Point location;
        do {
            location = element.getLocation();
            BaseActions.wait(0, 500);
        } while (!location.equals(element.getLocation()));
    }

    protected int getCount(String message, Locator locator, Object... args) {
        return this.getCount(0, message, locator, args);
    }

    protected int getCount(int waitInSeconds, String message, Locator locator, Object... args) {
        Reporter.log(message);

        driver().manage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS);
        int size =  driver().findElements(locator.getLocator(args)).size();
        driver().manage().timeouts().implicitlyWait(ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return size;
    }

    protected boolean isPresent(String message, Locator locator, Object... args) {
        return getCount(message, locator, args) > 0;
    }

    protected boolean isPresent(int waitInSeconds, String message, Locator locator, Object... args) {
        return getCount(waitInSeconds, message, locator, args) > 0;
    }

    protected void executeJS(String message, String script, Locator locator, Object... args) {
        Reporter.log(message);
        ((JavascriptExecutor) driver()).executeScript(script, driver().findElement(locator.getLocator(args)));
    }
}
