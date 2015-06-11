package com.twitter.automation.tests;

import com.twitter.automation.actions.Actions;
import com.twitter.automation.pages.Pages;
import com.twitter.automation.pages.ProfilePage;
import com.twitter.automation.pages.SuggestionsPage;
import com.twitter.automation.utils.base.BaseTest;
import com.twitter.automation.utils.base.DataProviderPool;
import com.twitter.automation.utils.reporting.Reporter;
import org.testng.annotations.Test;

public class TwitterTest extends BaseTest {
    private String login;

    @Test(dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void loginTest(String login, String password) {
        Actions.generalActions().login(login, password);
        this.login = login;
    }

    @Test(dependsOnMethods = "loginTest")
    public void addTweet() {
        ProfilePage profilePage = Pages.profilePage();

        Reporter.logAction("Get tweets amount before:");
        profilePage.open(login);
        int tweetsBefore = (profilePage.isTweetsNumberLabelPresent())
                ? Pages.profilePage().getTweetsNumber()
                : 0;
        Actions.tweetsActions().SendTweet("Tweet message " + System.currentTimeMillis());

        Actions.tweetsActions().waitForTweetsAmount(login, tweetsBefore+1);
    }



    @Test(dependsOnMethods = "loginTest")
    public void followUser() {
        ProfilePage profilePage = Pages.profilePage();

        Reporter.logAction("Get following amount before:");
        profilePage.open(login);
        int followingBefore = (profilePage.isFollowingNumberLabelPresent())
                ? Pages.profilePage().getFollowingNumber()
                : 0;

        Reporter.logAction("Follow some user from suggestions:");
        SuggestionsPage suggestPage = Pages.suggestionsPage();
        suggestPage.open();
        if (suggestPage.getSuggestionsAmount() == 0)
            skipTest("Cannot follow new users from suggestions: the list is empty!");
        suggestPage.clickSuggestionFollowButton(1); // follow the first user from the list

        Actions.usersActions().waitForFollowersAmount(login, followingBefore+1);

    }


}
