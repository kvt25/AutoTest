package com.autotest.framework.entity;

import java.util.List;

import com.autotest.framework.datadefined.EIsRun;
import com.autotest.framework.datadefined.EStatus;

public class TestCase
{
    private int index;
    private String name;
    private String description;
    private EIsRun isRun;
    private EStatus status;
    private List<TestStep> listTestStep;
    private int startRow, endRow;
    private TestSuite testSuite;
    private int passStep, failStep, skipStep,blockStep,warningStep;
    private int runTimes = 1;
    private int[] dataSet = new int[0];
    private int order = 1;
    private String id;

    public TestCase clone(int order, int dataSet) throws CloneNotSupportedException {
        TestCase testCase = new TestCase();
        testCase.setIndex(index);
        testCase.setName(name);
        testCase.setDescription(description);
        testCase.setIsRun(isRun);
        testCase.setStatus(EStatus.SKIPPED);
        testCase.setListTestStep(listTestStep);;
        testCase.setStartRow(startRow);
        testCase.setEndRow(endRow);
        testCase.setTestSuite(testSuite);
        testCase.setPassStep(passStep);
        testCase.setFailStep(failStep);
        testCase.setSkipStep(skipStep);
        testCase.setBlockStep(blockStep);
        testCase.setRunTimes(runTimes);
        int[] s = new int[1];
        s[0] = dataSet;
        testCase.setDataSet(s);
        testCase.setOrder(order + 1);
        return testCase;
    }

    public TestCase() {
        setStatus(EStatus.SKIPPED);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EIsRun getIsRun() {
        return isRun;
    }

    public void setIsRun(EIsRun isRun) {
        this.isRun = isRun;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public List<TestStep> getListTestStep() {
        return listTestStep;
    }

    public void setListTestStep(List<TestStep> listTestStep) {
        this.listTestStep = listTestStep;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int i) {
        this.startRow = i;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int i) {
        this.endRow = i;
    }

    public TestSuite getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuite ts) {
        this.testSuite = ts;
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

    public int getWarningStep() {
        return warningStep;
    }

    public void setWarningStep(int i) {
        this.warningStep = this.warningStep + i;
    }

    public int getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(int i) {
        this.runTimes = i;
    }

    public int[] getDataSet() {
        return dataSet;
    }

    public void setDataSet(int[] i) {
        this.dataSet = i;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}
