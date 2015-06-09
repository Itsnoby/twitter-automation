package com.twitter.automation.pages;

import com.twitter.automation.utils.base.BasePage;
import com.twitter.automation.utils.base.Locator;
import com.twitter.automation.utils.base.LocatorTypes;

public class NavigationBar extends BasePage {
    private Locator bar = new Locator(LocatorTypes.XPATH, "//div[@class='global-nav']");

    public void waitForLoad() {
        waitForVisibility("Wait until navigation bar is displayed", bar);
    }
}
