package com.twitter.automation.actions;

import com.twitter.automation.pages.LoginPage;
import com.twitter.automation.pages.Pages;
import com.twitter.automation.pages.TweetDialog;
import com.twitter.automation.utils.base.BaseActions;
import com.twitter.automation.utils.base.BasePage;
import com.twitter.automation.utils.reporting.Reporter;

public class GeneralActions extends BaseActions {

    public void login(String login, String password) {
        Reporter.logAction("Log in:");

        driver().get(BasePage.BASE_URL);

        LoginPage page = Pages.loginPage();
        page.waitForLoad();
        page.typeLogin(login);
        page.typePassword(password);
        page.clickLoginButton();

        Pages.navigationBar().waitForLoad();
    }

    public void SendTweet(String tweet) {
        Reporter.logAction("Send tweet:");

        TweetDialog dialog = Pages.tweetDialog();
        Pages.navigationBar().clickTweetButton();
        dialog.waitForVisibility();
        dialog.typeTweet(tweet);
        dialog.clickTweetButton();
        dialog.waitForInvisibility();
    }
}
