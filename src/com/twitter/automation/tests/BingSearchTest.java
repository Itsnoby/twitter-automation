package com.twitter.automation.tests;

import com.twitter.automation.actions.GeneralActions;
import com.twitter.automation.utils.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BingSearchTest extends BaseTest {
    GeneralActions generalActions;

    @BeforeClass
    public void setUp() {
        generalActions = new GeneralActions(driver);
    }

    @Test
     public void searchTest() {
        String query = "TestNG";
        List<String> results = generalActions.search(query);

        boolean resultsContainQuery = false;
        for (String r: results) {
            if (StringUtils.containsIgnoreCase(r, query))
                resultsContainQuery = true;
        }

        Assert.assertTrue(resultsContainQuery, "Search string is not found in results!");
    }

    @Test
    public void eachResultContainsQueryTest() {
        String query = "selenium";
        List<String> results = generalActions.search(query);

        for (String r: results) {
            Assert.assertTrue(
                    StringUtils.containsIgnoreCase(r, query),
                    String.format("Search result '%s' is not containing string '%s'!", r, query));
        }
    }
}
