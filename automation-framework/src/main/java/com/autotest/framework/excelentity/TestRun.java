package com.autotest.framework.excelentity;



/**
 * @Name: Scenario
 * @author DatNguyen
 * @Description: This class is declare Scenario object and attributes
 * @CreatedDate: 05/03/2014
 */
public class TestRun
{
    private String _projectName;
    private String _runDate;
    private String _windowName;
    private String _windowVersion;
    private String _browsersRun;
    //private final List<BrowserEnvTest> browserEnvTests = new ArrayList<BrowserEnvTest>();

    public TestRun() {

    }

    public String getProjectName() {
        return _projectName;
    }

    public void setProjectName(String projectName) {
        this._projectName = projectName;
    }

    public String getRunDate() {
        return _runDate;
    }

    public void setRunDate(String runDate)
    {
        this._runDate = runDate;
    }

    public String getWindowName() {
        return _windowName;
    }

    public void setWindowName(String windowName) {
        this._windowName = windowName;
    }

    public String getWindowVersion() {
        return _windowVersion;
    }

    public void setWindowVersion(String windowVersion) {
        this._windowVersion = windowVersion;
    }

    public String getBrowserRun() {
        return _browsersRun;
    }

    public void setBrowserRun(String browserRun) {
        this._browsersRun = browserRun;
    }
}
