package com.twitter.automation.utils;

import java.text.SimpleDateFormat;

public class Reporter {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("H:mm:ss:SSS");

    public static void log(String msg) {
        org.testng.Reporter.log("[" + FORMAT.format(System.currentTimeMillis()) + "]: " + msg + "<br></br>");
    }
}
