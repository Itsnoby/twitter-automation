package com.twitter.automation.pages;

import com.twitter.automation.utils.base.BasePage;
import com.twitter.automation.utils.base.Locator;
import com.twitter.automation.utils.base.LocatorTypes;

public class LoginPage extends BasePage {
    private Locator loginForm = new Locator(LocatorTypes.XPATH, "//div[@id='page-container']//form[contains(@class,'signin')]");
    private Locator loginEmailInput = new Locator(LocatorTypes.ID, "signin-email");
    private Locator loginPassInput = new Locator(LocatorTypes.ID, "signin-password");
    private Locator loginButton = loginForm.concat(new Locator(LocatorTypes.XPATH, "//button[@type='submit']"));

    public void waitForLoad() {
        waitForVisibility("Wait until login page is loaded", loginForm);
    }

    public void typeLogin(String value) {
        type("Type login: " + value, value, loginEmailInput);
    }

    public void typePassword(String value) {
        type("Type password: " + value, value, loginPassInput);
    }

    public void clickLoginButton() {
        click("Click 'Log in' button", loginButton);
    }
}
