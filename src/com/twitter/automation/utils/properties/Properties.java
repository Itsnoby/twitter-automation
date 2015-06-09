package com.twitter.automation.utils.properties;

import org.apache.commons.lang3.SystemUtils;

import java.nio.file.Paths;

public class Properties {

    public static BrowserTypes getBrowser() {
        try {
            return BrowserTypes.valueOf(System.getProperty(com.twitter.automation.utils.properties.PropertiesNames.BROWSER.toString()).toUpperCase());
        } catch (Exception e) {
            return BrowserTypes.CHROME;
        }
    }

    public static String GetDriverPath() {
        String basePath = System.getProperty(com.twitter.automation.utils.properties.PropertiesNames.DRIVERS_DIR.toString());
        if (basePath == null)
            return null;

        if (SystemUtils.IS_OS_WINDOWS) {
            return Paths.get(basePath, "chromedriver.exe").toString();
        }
        if (SystemUtils.IS_OS_LINUX) {
            return Paths.get(basePath, "chromedriver_linux").toString();
        }
        if (SystemUtils.IS_OS_MAC) {
            return Paths.get(basePath, "chromedriver_mac").toString();
        }

        return null;
    }
}
