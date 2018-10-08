package com.autotest.framework.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestRun
{
    private String runTime;
    private List<TestScenario> listTestScenario;
    private ReportFolder reportFolder;
    private int passStep, failStep, blockStep, warningStep;
    private int passTC, failTC;
    private int passTS, failTS;
    private int passSce, failSce;
    private String history;

    public TestRun(String runTime) {
        setTime(runTime);
    }

    public String getTime() {
        return runTime;
    }

    public void setTime(String runTime) {
        this.runTime = runTime;
    }

    public List<TestScenario> getListTestScenario() {
        return listTestScenario;
    }

    public void setListTestScenario(List<String> listTestScenario) {
        List<TestScenario> sce = new ArrayList<TestScenario>();
        for (String s : listTestScenario)
        {
            TestScenario tsce = new TestScenario();
            tsce.setName(s);
            sce.add(tsce);
        }
        this.listTestScenario = sce;
    }

    public ReportFolder getReportFolder() {
        return reportFolder;
    }

    public void setReportFolder(ReportFolder reportFolder) {
        this.reportFolder = reportFolder;
    }

    public int getPassStep() {
        return passStep;
    }

    public void setPassStep(int i) {
        this.passStep = this.passStep + i;
    }

    public int getFailStep() {
        return failStep;
    }

    public void setFailStep(int i) {
        this.failStep = this.failStep + i;
    }

    public int getBlockStep() {
        return blockStep;
    }

    public void setBlockStep(int i) {
        this.blockStep = this.blockStep + i;
    }

    public int getPassTC() {
        return passTC;
    }

    public void setPassTC(int i) {
        this.passTC = this.passTC + i;
    }

    public int getFailTC() {
        return failTC;
    }

    public void setFailTC(int i) {
        this.failTC = this.failTC + i;
    }

    public int getPassTS() {
        return passTS;
    }

    public void setPassTS(int i) {
        this.passTS = this.passTS + i;
    }

    public int getFailTS() {
        return failTS;
    }

    public void setFailTS(int i) {
        this.failTS = this.failTS + i;
    }

    public int getWarningStep() {
        return warningStep;
    }

    public void setWarningStep(int i) {
        this.warningStep = this.warningStep + i;
    }

    public int getPassSce() {
        return passSce;
    }

    public void setPassSce(int i) {
        this.passSce = this.passSce + i;
    }

    public int getFailSce() {
        return failSce;
    }

    public void setFailSce(int i) {
        this.failSce = this.failSce + i;
    }

    public String getHistoricalReport() {
        return history;
    }

    /*
     * public void setHistoricalReport(String history) { String temp =
     * history.substring(history.lastIndexOf("/") + 1); this.history = temp; }
     */

    public void setHistoricalReport(String history) {

        String imgFile = new File(history + "_files").listFiles()[1].getName();
        String temp = history.substring(history.lastIndexOf("/") + 1) + "_files/"
                + imgFile;
        this.history = temp;
    }

}
