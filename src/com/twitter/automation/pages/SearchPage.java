package com.twitter.automation.pages;

import com.twitter.automation.utils.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    private WebDriver driver;

    private By searchInput = By.id("sb_form_q");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        Reporter.log("Open Search page");
        driver.get("http://www.bing.com/");
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(searchInput));
    }

    public void typeSearchQuery(String value) {
        Reporter.log("Type search query: " + value);
        WebElement input = driver.findElement(searchInput);
        input.clear();
        input.sendKeys(value + Keys.ENTER);
    }

}
