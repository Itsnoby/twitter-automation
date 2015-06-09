package com.twitter.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Field;

public class BaseTest {
    public static WebDriver driver;

    @BeforeClass
    public void driverSetUp() {
        System.out.println("Browser is: " + Properties.getBrowser().toString());

        switch (Properties.getBrowser()) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
            default:
                String driverPath = Properties.GetDriverPath();
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");

                driver = new ChromeDriver(options) {
                    @Override
                    public WebElement findElement(By by) {
                        try {
                            return by.findElement(this);
                        } catch (org.openqa.selenium.NoSuchElementException nse) {
                            Field f = null;
                            try {
                                f = Throwable.class.getDeclaredField("detailMessage");
                            } catch (NoSuchFieldException e) {
                                throw nse;
                            }
                            f.setAccessible(true);
                            try {
                                String error = "\n" + by.toString() + "\n" + f.get(nse);
                                f.set(nse, error);
                            } catch (IllegalAccessException ia) { }
                            throw nse;
                        }
                    }
                };
                break;
        }
    }

    @AfterClass
    public void driverTearDown() {
        driver.quit();
    }
}
