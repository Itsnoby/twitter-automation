package com.twitter.automation.utils.base;

import org.testng.annotations.DataProvider;

public class DataProviderPool {
    public static final String USER_CREDENTIALS = "userCredentials";

    /**
     * @return User's login and password.
     */
    @DataProvider(name = USER_CREDENTIALS)
    public static Object[][] getUserLoginData() {
        return new String[][] {
                {"qa3907test", "password123123"}
        };
    }
}
