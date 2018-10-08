package com.autotest.framework.library;
import java.text.MessageFormat;

import org.openqa.selenium.WebDriver;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.autotest.utils.StackTraceInfo;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class SeleniumFunctions extends Environment{

    CommonFunctions commonFunctions;

    public SeleniumFunctions(CommonFunctions commonFunctions)
    {
        this.commonFunctions = commonFunctions;
    }

    private WebDriver getWebDriver()
    {
        return commonFunctions.getWebDriver();
    }


    public void waitForPageLoad(String time) throws Exception {

        try {
            Selenium selenium = new WebDriverBackedSelenium(getWebDriver(), AutoTestConstants.defaultURLSelenium);
            selenium.waitForPageToLoad(time);
            log.info("waitForPageLoad() function is executed");
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }


    // execute JavaScript: getEval("javascript code as string"); It returns result of Java script.
    public String getEval(String script) throws Exception {

        try {
            String rExecutedScript = "";
            Selenium selenium = new WebDriverBackedSelenium(getWebDriver(), AutoTestConstants.defaultURLSelenium);
            rExecutedScript = selenium.getEval(script);
            log.info(MessageFormat.format("getEval of Script= \"{0}\" is \"{1}\"", script, rExecutedScript));
            return rExecutedScript;
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }


    public void click(String locator) throws Exception {

        try {
            Selenium selenium = new WebDriverBackedSelenium(getWebDriver(), AutoTestConstants.defaultURLSelenium);
            selenium.click(locator);
            log.info(MessageFormat.format("Object with XPATH = \"{0}\" is clicked", locator));
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }


    public boolean isElementPresent(String locator) throws Exception {

        try {
            boolean bResult = false;
            Selenium selenium = new WebDriverBackedSelenium(getWebDriver(), AutoTestConstants.defaultURLSelenium);
            bResult = selenium.isElementPresent(locator);
            if(bResult) {
                log.info(MessageFormat.format("Object with XPATH= \"{0}\" is presented on browser", locator));
            }
            else {
                log.error(MessageFormat.format("Object with XPATH= \"{0}\" is not presented on browser", locator));
            }
            return bResult;
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }


    public void stop() throws Exception {

        try {
            Selenium selenium = new WebDriverBackedSelenium(getWebDriver(), AutoTestConstants.defaultURLSelenium);
            selenium.stop();
            log.info("Stoped Selenium");
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }


    public void type(String xpath, String value) throws Exception {

        try {
            Selenium selenium = new WebDriverBackedSelenium(getWebDriver(), AutoTestConstants.defaultURLSelenium);
            int i = selenium.getText(xpath).length();
            if (i == 0) {
                selenium.type(xpath, value);
            }
            else {
                for (int j = 0;j <= i; j++) {
                    selenium.typeKeys(xpath, "\b");     // "\b" type a space and a back space to trigger javascript
                }
                selenium.type(xpath, value);
            }
            log.info(MessageFormat.format("Type Value= \"{0}\" on Object with XPATH= \"{1}\" is executed", xpath, value));
        }
        catch (Exception e) {
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }
}

