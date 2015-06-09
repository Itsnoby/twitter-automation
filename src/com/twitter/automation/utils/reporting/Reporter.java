package com.twitter.automation.utils.reporting;

import java.text.SimpleDateFormat;

public class Reporter {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("H:mm:ss:SSS");

    public static void log(String msg) {
        org.testng.Reporter.log("[" + FORMAT.format(System.currentTimeMillis()) + "]: " + msg + "<br></br>");
    }

    public static void logAction(String msg) {
        log(String.format("<b>%s</b>", msg));
    }
}
