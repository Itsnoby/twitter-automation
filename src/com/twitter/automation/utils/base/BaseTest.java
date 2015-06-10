package com.twitter.automation.utils.base;

import com.twitter.automation.actions.Actions;
import com.twitter.automation.pages.Pages;
import com.twitter.automation.utils.properties.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    private static WebDriver driver;

    public static WebDriver driver() {
        return driver;
    }

    @BeforeClass
    public void setUp() {
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

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(BasePage.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();

        Pages.clear();
        Actions.clear();
    }

    protected void skipTest(String message) {
        throw new SkipException(message);
    }
}
