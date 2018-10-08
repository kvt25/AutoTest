package com.autotest.framework.entity;

import java.util.List;

import com.autotest.framework.datadefined.EStatus;

import junit.framework.TestSuite;

public class TestScenario
{
    private String name;
    private List<TestSuite> listTestSuite;
    private TestRun testRun;
    private int passStep, failStep, skipStep, blockStep, warningStep;
    private int passTC, failTC, blockTC;
    private int passTS, failTS, blockTS;
    private EStatus status;

    public TestScenario() {
        setStatus(EStatus.SKIPPED);
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestSuite> getListTestSuite() {
        return listTestSuite;
    }

    public void setListTestSuite(List<TestSuite> listTestSuite) {
        this.listTestSuite = listTestSuite;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
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

    public int getSkipStep() {
        return skipStep;
    }

    public void setSkipStep(int i) {
        this.skipStep = this.skipStep + i;
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

    public int getBlockTC() {
        return blockTC;
    }

    public void setBlockTC(int i) {
        this.blockTC = this.blockTC + i;
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

    public int getBlockTS() {
        return blockTS;
    }

    public void setBlockTS(int i) {
        this.blockTS = this.blockTS + i;
    }

    public int getWarningStep() {
        return warningStep;
    }

    public void setWarningStep(int i) {
        this.warningStep = this.warningStep + i;
    }

}
