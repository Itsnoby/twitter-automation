package com.twitter.automation.actions;

import com.twitter.automation.pages.ResultsPage;
import com.twitter.automation.pages.SearchPage;
import com.twitter.automation.utils.Reporter;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class GeneralActions {
    private WebDriver driver;

    private SearchPage searchPage;
    private ResultsPage resultsPage;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;

        searchPage = new SearchPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    public List<String> search(String query) {
        Reporter.log("Search:");
        searchPage.open();
        searchPage.typeSearchQuery(query);
        resultsPage.waitForResultsToLoad();
        return resultsPage.getResults();
    }
}
