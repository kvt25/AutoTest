package com.autotest.framework.excelentity;



/**
 * @Name: TestStep
 * @author DatNguyen
 * @Description: This class is declare TestStep object and attributes
 * @CreatedDate: 05/03/2014
 */
public class TestStep
{
    private int _index;
    private String _scenarioID;
    private String _scenarioStatus;
    private String _testcaseID;
    private String _testcaseStatus;
    private String _testcaseRunNumber;
    private String _automationName;
    private String _stepNumber;
    private String _stepDescription;
    private String _expectedResult;
    private String _result;
    private String _obtainedResult;
    private String _screenshot;
    private String _startTimeExec;
    private String _endTimeExec;
    private String _testData;

    public TestStep() {

    }

    public int getIndex() {
        return this._index;
    }

    public void setIndex(int index) {
        this._index = index;
    }

    public String getScenarioID() {
        return this._scenarioID;
    }

    public void setScenarioID(String scenarioid) {
        this._scenarioID = scenarioid;
    }

    public String getScenarioStatus() {
        return this._scenarioStatus;
    }

    public void setScenarioStatus(String scenarioStatus) {
        this._scenarioStatus = scenarioStatus;
    }

    public String getTestcaseID() {
        return this._testcaseID;
    }

    public void setTestcaseID(String testcaseid) {
        this._testcaseID = testcaseid;
    }
    public String getTestcaseStatus() {
        return this._testcaseStatus;
    }

    public void setTestcaseStatus(String testcaseStatus) {
        this._testcaseStatus = testcaseStatus;
    }

    public String getTestcaseRunNumber() {
        return this._testcaseRunNumber;
    }

    public void setTestcaseRunNumber(String testcaseRunNumber) {
        this._testcaseRunNumber = testcaseRunNumber;
    }

    public String getAutomationName() {
        return this._automationName;
    }

    public void setAutomationName(String automationName) {
        this._automationName = automationName;
    }

    public String getStepNumber() {
        return this._stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this._stepNumber = stepNumber;
    }

    public String getStepDescription() {
        return this._stepDescription;
    }

    public void setStepDescription(String stepDes) {
        this._stepDescription = stepDes;
    }

    public String getExpectedResult() {
        return this._expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this._expectedResult = expectedResult;
    }

    public String getResult() {
        return this._result;
    }

    public void setResult(String result) {
        this._result = result;
    }

    public String getObtainedResult() {
        return this._obtainedResult;
    }

    public void setObtainedResult(String obtainedResult) {
        this._obtainedResult = obtainedResult;
    }

    public String getScreenshot() {
        return this._screenshot;
    }

    public void setScreenshot(String screenshot) {
        this._screenshot = screenshot;
    }

    public String getStartTimeExec() {
        return this._startTimeExec;
    }

    public void setStartTimeExec(String startTimeExec) {
        this._startTimeExec = startTimeExec;
    }

    public String getEndTimeExec() {
        return this._endTimeExec;
    }

    public void setEndTimeExec(String endTimeExec) {
        this._endTimeExec = endTimeExec;
    }

    public String getTestData() {
        return this._testData;
    }

    public void setTestData(String testData) {
        this._testData = testData;
    }
}

