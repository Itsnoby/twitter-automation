package com.twitter.automation.actions;

import com.twitter.automation.pages.Pages;
import com.twitter.automation.pages.ProfilePage;
import com.twitter.automation.utils.base.BaseActions;
import com.twitter.automation.utils.reporting.Reporter;
import org.testng.Assert;

public class UsersActions extends BaseActions {

    public void waitForFollowersAmount(String login, int amount) {
        Reporter.logAction(String.format("Wait for %d following(s) for user %s:", amount, login));
        ProfilePage profilePage = Pages.profilePage();

        profilePage.open(login);
        if (amount > 0) {
            Assert.assertTrue(
                    profilePage.isFollowingNumberLabelPresent(),
                    "Following number label must be visible!");
        }

        long timestampStart = System.currentTimeMillis();
        int following;
        while ((following = (profilePage.isFollowingNumberLabelPresent()) ? profilePage.getFollowingNumber() : 0) != amount
                && System.currentTimeMillis() - timestampStart <= profilePage.ELEMENT_TIMEOUT_SECONDS * 1000)  {
            wait(profilePage.ELEMENT_MICRO_TIMEOUT_SECONDS);
            driver().navigate().refresh();
        }

        Assert.assertEquals(following, amount, "Unexpected amount of following users!");
    }
}
