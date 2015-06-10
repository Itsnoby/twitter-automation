package com.twitter.automation.pages;

import com.twitter.automation.utils.base.BasePage;
import com.twitter.automation.utils.base.Locator;
import com.twitter.automation.utils.base.LocatorTypes;

public class TweetDialog extends BasePage {
    private Locator dialog = new Locator(LocatorTypes.ID, "global-tweet-dialog");
    private Locator tweetInput = new Locator(LocatorTypes.ID, "tweet-box-global");
    private Locator tweetButton = dialog.concat(new Locator(LocatorTypes.XPATH, "//div[@class='tweet-button']/button"));

    public void waitForVisibility() {
        waitForVisibility("Wait for Tweet dialog visibility", dialog);
    }

    public void waitForInvisibility() {
        waitForInvisibility("Wait for Tweet dialog invisibility", dialog);
    }

    public void typeTweet(String value) {
        executeJS(
                "Type tweet: " + value,
                String.format("arguments[0].innerHTML = \"%s\"", value), tweetInput);
    }

    public void clickTweetButton() {
        waitToBeClickable("Wait for 'Tweet' button to be clickable", tweetButton);
        click("Click 'Tweet' button", tweetButton);
    }
}
