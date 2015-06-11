package com.twitter.automation.actions;

import com.twitter.automation.pages.Pages;
import com.twitter.automation.pages.ProfilePage;
import com.twitter.automation.pages.TweetDialog;
import com.twitter.automation.utils.base.BaseActions;
import com.twitter.automation.utils.reporting.Reporter;
import org.testng.Assert;

public class TweetsActions extends BaseActions {

    public void SendTweet(String tweet) {
        Reporter.logAction("Send tweet:");

        TweetDialog dialog = Pages.tweetDialog();
        Pages.navigationBar().clickTweetButton();
        dialog.waitForVisibility();
        dialog.typeTweet(tweet);
        dialog.clickTweetButton();
        dialog.waitForInvisibility();
    }

    public void waitForTweetsAmount(String login, int amount) {
        Reporter.logAction(String.format("Wait for %d tweet(s) for user %s:", amount, login));
        ProfilePage profilePage = Pages.profilePage();

        profilePage.open(login);
        if (amount > 0) {
            Assert.assertTrue(
                    profilePage.isTweetsNumberLabelPresent(),
                    "Tweets number label must be visible!");
        }

        long timestampStart = System.currentTimeMillis();
        int tweets;
        while ((tweets = (profilePage.isTweetsNumberLabelPresent()) ? profilePage.getTweetsNumber() : 0) != amount
                && System.currentTimeMillis() - timestampStart <= profilePage.ELEMENT_TIMEOUT_SECONDS * 1000)  {
            wait(profilePage.ELEMENT_MICRO_TIMEOUT_SECONDS);
            driver().navigate().refresh();
        }

        Assert.assertEquals(tweets, amount, "Unexpected amount of tweets!");
    }
}
