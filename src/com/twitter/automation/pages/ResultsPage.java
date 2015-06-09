package com.twitter.automation.pages;

import com.twitter.automation.utils.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;

    private By resultsBlock = By.id("b_results");
    private By resultTitle = By.xpath("//ol[@id='b_results']/li//h2");

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForResultsToLoad() {
        Reporter.log("Wait for results block");
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(resultsBlock));
    }

    public List<String> getResults() {
        Reporter.log("Get results");
        List<WebElement> results = driver.findElements(resultTitle);

        List<String> titles = new ArrayList<String>(results.size());
        for (WebElement r: results) {
            titles.add(r.getText());
        }

        return titles;
    }
}
