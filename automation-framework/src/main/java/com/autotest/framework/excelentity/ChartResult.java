package com.autotest.framework.excelentity;


/**
 * @Name: ChartResult
 * @author DatNguyen
 * @Description: This class is declare ChartResult object and attributes
 * @CreatedDate: 04/03/2014
 */
public class ChartResult
{
    private int _colStepPassed;
    private int _rowStepPassed;
    private int _colStepFailed;
    private int _rowStepFailed;
    private int _colStepSkiped;
    private int _rowStepSkiped;

    private int _colTestCasePassed;
    private int _rowTestCasePassed;
    private int _colTestCaseFailed;
    private int _rowTestCaseFailed;
    private int _colTestCaseSkiped;
    private int _rowTestCaseSkiped;

    public ChartResult() {
    }

    public void setColStepPassed(int iColStepPassed) {
        this._colStepPassed =  iColStepPassed;
    }

    public void setRowStepPassed(int iRowStepPassed) {
        this._rowStepPassed = iRowStepPassed;
    }

    public void setColStepFailed(int iColStepFailed) {
        this._colStepFailed = iColStepFailed;
    }

    public void setRowStepFailed(int iRowStepFailed) {
        this._rowStepFailed = iRowStepFailed;
    }

    public void setColStepSkiped(int iColStepSkiped) {
        this._colStepSkiped = iColStepSkiped;
    }

    public void setRowStepSkiped(int iRowStepSkiped) {
        this._rowStepSkiped = iRowStepSkiped;
    }

    public void setColTestCasePassed(int iColTestCasePassed) {
        this._colTestCasePassed = iColTestCasePassed;
    }

    public void setRowTestCasePassed(int iRowTestCasePassed) {
        this._rowTestCasePassed = iRowTestCasePassed;
    }

    public void setColTestCaseFailed(int iColTestCaseFailed) {
        this._colTestCaseFailed = iColTestCaseFailed;
    }

    public void setRowTestCaseFailed(int iRowTestCaseFailed) {
        this._rowTestCaseFailed = iRowTestCaseFailed;
    }

    public void setColTestCaseSkiped(int iColTestCaseSkiped) {
        this._colTestCaseSkiped = iColTestCaseSkiped;
    }

    public void setRowTestCaseSkiped(int iRowTestCaseSkiped) {
        this._rowTestCaseSkiped = iRowTestCaseSkiped;
    }
}
