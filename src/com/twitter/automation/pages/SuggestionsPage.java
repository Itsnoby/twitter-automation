package com.twitter.automation.pages;

import com.twitter.automation.utils.base.BasePage;
import com.twitter.automation.utils.base.Locator;
import com.twitter.automation.utils.base.LocatorTypes;
import com.twitter.automation.utils.reporting.Reporter;

public class SuggestionsPage extends BasePage {
    private Locator container = new Locator(LocatorTypes.ID, "page-container");
    private Locator leftDashboard = container.concat(new Locator(LocatorTypes.XPATH, "//div[contains(@class,'dashboard-left')]"));
    private Locator rightDashboard = container.concat(new Locator(LocatorTypes.XPATH, "//div[contains(@class,'dashboard-right')]"));
    private Locator mainBlock = container.concat(new Locator(LocatorTypes.XPATH, "//div[@class='content-main']"));

    private Locator suggestionsList = mainBlock.concat(new Locator(LocatorTypes.ID, "stream-items-id"));
    private Locator suggestion = suggestionsList.concat(new Locator(LocatorTypes.XPATH, "/li"));
    private Locator suggestionByIndex = suggestionsList.concat(new Locator(LocatorTypes.XPATH, "/li[%d]"));
    private Locator followButtonByIndex = suggestionByIndex.concat(new Locator(LocatorTypes.XPATH, "//div[contains(@class,'user-actions') and contains(@class,'not-following')]/button[contains(@class,'user-actions-follow-button')]"));
    private Locator unfollowButtonByIndex = suggestionByIndex.concat(new Locator(LocatorTypes.XPATH, "//div[contains(@class,'user-actions') and not(contains(@class,'not-following'))]/button[contains(@class,'user-actions-follow-button')]"));

    public void open() {
        Reporter.log("Open Suggestions page");
        driver().get(BASE_URL + "who_to_follow/suggestions");

        waitForVisibility("Wait for left block visibility", leftDashboard);
        waitForVisibility("Wait for right block visibility", rightDashboard);
        waitForVisibility("Wait for main block visibility", mainBlock);
    }

    public int getSuggestionsAmount() {
        return getCount("Get suggestions amount", suggestion);
    }

    public void clickSuggestionFollowButton(int suggestIndex) {
        click("Click 'Follow' button for suggestion #" + suggestIndex,
                followButtonByIndex, suggestIndex);
        waitForVisibility("Wait for 'Unfollow' button for suggestion #" + suggestIndex,
                unfollowButtonByIndex, suggestIndex);
    }


}
