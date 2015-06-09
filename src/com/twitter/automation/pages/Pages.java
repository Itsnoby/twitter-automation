package com.twitter.automation.pages;

import com.twitter.automation.utils.ObjectsCollection;
import com.twitter.automation.utils.base.BasePage;

public final class Pages {
    private static ObjectsCollection<BasePage> pages = new ObjectsCollection<BasePage>();

     public static void clear() {
        pages.clear();
    }

    public static LoginPage loginPage() {
        return pages.getInstance(LoginPage.class);
    }

    public static NavigationBar navigationBar() {
        return pages.getInstance(NavigationBar.class);
    }

}
