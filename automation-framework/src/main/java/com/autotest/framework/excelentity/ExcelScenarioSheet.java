package com.autotest.framework.excelentity;

public class ExcelScenarioSheet
{
    private int rowBrowserName;
    private int colBrowserName;
    private int rowScenarioHeader;
    private int colScenario;
    private int colExecuteFlag;
    private int colTestCaseIDs;

    public int getRowBrowserName() {
        return this.rowBrowserName;
    }

    public void setRowBrowserName(int rowBrowserName) {
        this.rowBrowserName = rowBrowserName;
    }

    public int getColBrowserName() {
        return this.colBrowserName;
    }

    public void setColBrowserName(int colBrowserName) {
        this.colBrowserName = colBrowserName;
    }

    public int getRowScenarioHeader() {
        return this.rowScenarioHeader;
    }

    public void setRowScenarioHeader(int rowScenarioHeader) {
        this.rowScenarioHeader = rowScenarioHeader;
    }

    public int getColScenario() {
        return this.colScenario;
    }

    public void setColScenario(int colScenario) {
        this.colScenario = colScenario;
    }

    public int getColExecuteFlag() {
        return this.colExecuteFlag;
    }

    public void setColExecuteFlag(int colExecuteFlag) {
        this.colExecuteFlag = colExecuteFlag;
    }

    public int getColTestCaseIDs() {
        return this.colTestCaseIDs;
    }

    public void setColTestCaseIDs(int colTestCaseIDs) {
        this.colTestCaseIDs = colTestCaseIDs;
    }

}
