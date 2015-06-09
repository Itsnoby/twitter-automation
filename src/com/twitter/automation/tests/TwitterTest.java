package com.twitter.automation.tests;

import com.twitter.automation.actions.Actions;
import com.twitter.automation.utils.base.BaseTest;
import com.twitter.automation.utils.base.DataProviderPool;
import org.testng.annotations.Test;

public class TwitterTest extends BaseTest {

    @Test(dataProvider = DataProviderPool.USER_CREDENTIALS, dataProviderClass = DataProviderPool.class)
    public void loginTest(String login, String password) {
        Actions.generalActions().login(login, password);
    }


}
