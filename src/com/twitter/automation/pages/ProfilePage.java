package com.twitter.automation.pages;

import com.twitter.automation.utils.base.BasePage;
import com.twitter.automation.utils.base.Locator;
import com.twitter.automation.utils.base.LocatorTypes;
import com.twitter.automation.utils.reporting.Reporter;

public class ProfilePage extends BasePage {
    private Locator profileNav = new Locator(LocatorTypes.XPATH, "//div[@class='ProfileNav']");
    private Locator profileNavTweetsNumber = profileNav.concat(new Locator(LocatorTypes.XPATH, "//a[@data-nav='tweets']/span[@class='ProfileNav-value']"));
    private Locator profileNavFollowingNumber = profileNav.concat(new Locator(LocatorTypes.XPATH, "//a[@data-nav='following']/span[@class='ProfileNav-value']"));

    public void open(String login) {
        Reporter.log("Open profile page for user " + login);
        driver().get(BASE_URL + login);
    }

    public boolean isTweetsNumberLabelPresent()
    {
        return isPresent("Check is tweets number label present", profileNavTweetsNumber);
    }

    public int getTweetsNumber() {
        String label = getText("Get tweets number", profileNavTweetsNumber);
        return Integer.parseInt(label);
    }

    public boolean isFollowingNumberLabelPresent()
    {
        return isPresent("Check is following number label present", profileNavFollowingNumber);
    }

    public int getFollowingNumber() {
        String label = getText("Get following number", profileNavFollowingNumber);
        return Integer.parseInt(label);
    }

}
