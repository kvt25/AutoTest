package com.autotest.framework.reporting;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.autotest.framework.excelentity.BrowserEnvTest;
import com.autotest.framework.excelentity.TestRun;

@XmlRootElement
public class TestRunReport
{
    private final List<TestRun> testruns = new ArrayList<TestRun>();
    private final List<BrowserEnvTest> browserEnvTests = new ArrayList<BrowserEnvTest>();

    private String _projectName;

    public String getProjectName() {
        return _projectName;
    }

    public void setProjectName(String projectName) {
        this._projectName = projectName;
    }
    @XmlElement(name="testrun")
    public List<TestRun> getTestRuns() {
        return testruns;
    }

    @XmlElement(name="browserEnvTest")
    public List<BrowserEnvTest> getBrowserEnvTests() {
        return browserEnvTests;
    }

}

