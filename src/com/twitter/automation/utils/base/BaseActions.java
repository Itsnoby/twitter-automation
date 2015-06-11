package com.twitter.automation.utils.base;

public abstract class BaseActions extends DriverContainer {
    public static void wait(int seconds) {
        wait(seconds, 0);
    }

    public static void wait(int seconds, int milliseconds) {
        try {
            Thread.sleep(seconds * 1000 + milliseconds);
        } catch (Exception e) { }
    }
}
