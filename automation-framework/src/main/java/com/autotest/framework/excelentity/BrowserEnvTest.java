package com.autotest.framework.excelentity;

import java.util.List;

public class BrowserEnvTest
{
    private String _browserName;
    private String _browserVersion;
    private String _runDate;
    private int _passAmount, _failAmount, _skipAmount;
    private List<Scenario> listScenario;
    private String _linkResult;

    public BrowserEnvTest() {

    }

    public String getBrowserName() {
        return _browserName;
    }

    public void setBrowserName(String browserName) {
        this._browserName = browserName;
    }

    public String getBrowserVersion() {
        return _browserVersion;
    }

    public void setBrowserVersion(String browserVersion)
    {
        this._browserVersion = browserVersion;
    }

    public String getRunDate() {
        return _runDate;
    }

    public void setRunDate(String runDate)
    {
        this._runDate = runDate;
    }

    public int getPassScenarios() {
        return _passAmount;
    }

    public void setPassScenarios(int passAmount) {
        this._passAmount = passAmount;
    }

    public int getFailScenarios() {
        return _failAmount;
    }

    public void setFailScenarios(int failAmount) {
        this._failAmount = failAmount;
    }

    public int getSkipScenarios() {
        return _skipAmount;
    }

    public void setSkipScenarios(int skipAmount) {
        this._skipAmount = skipAmount;
    }

    public String getLinkResult() {
        return _linkResult;
    }

    public void setLinkResult(String linkResult) {
        this._linkResult = linkResult;
    }
}
