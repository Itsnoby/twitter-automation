package com.twitter.automation.utils.reporting;

import com.twitter.automation.utils.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

public class ScreenshotMaker extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        org.testng.Reporter.setCurrentTestResult(result);

        String imageName = result.getName() + "-" + System.currentTimeMillis() + ".png";
        String destFile = "html" + File.separatorChar + imageName;

        try {

            File scrFile = ((TakesScreenshot) BaseTest.driver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(destFile));
            Reporter.log(
                    String.format(
                            "Screenshot saved:<br></br><a href = '%s'><img src = '%s' width='600'/></a>",
                            imageName, imageName));

        } catch (Exception e) {
            Reporter.log("Error saving screenshot!");
        }
    }
}
