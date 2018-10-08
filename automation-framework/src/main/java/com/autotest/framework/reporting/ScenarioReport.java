package com.autotest.framework.reporting;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.autotest.configure.AutoTestConstants;
import com.autotest.framework.excelentity.BrowserEnvTest;
import com.autotest.framework.excelentity.Scenario;
import com.autotest.framework.excelentity.TestCase;
import com.autotest.framework.excelentity.TestStep;

@XmlRootElement
public class ScenarioReport
{
    private final List<BrowserEnvTest> browserEnvTests = new ArrayList<BrowserEnvTest>();
    private final List<Scenario> scenarios = new ArrayList<Scenario>();
    private final List<TestCase> testCases = new ArrayList<TestCase>();
    private final List<TestStep> testSteps = new ArrayList<TestStep>();

    private String _projectName;

    public ScenarioReport() {
        setProjectName(AutoTestConstants.PROJECT_NAME);
    }

    public String getProjectName() {
        return _projectName;
    }

    public void setProjectName(String projectName) {
        this._projectName = projectName;
    }

    @XmlElement(name="browserEnvTest")
    public List<BrowserEnvTest> getBrowserEnvTests() {
        return browserEnvTests;
    }

    @XmlElement(name="scenario")
    public List<Scenario> getScenarios() {
        return scenarios;
    }

    @XmlElement(name = "testcase")
    public List<TestCase> getTestCases() {
        return testCases;
    }

    @XmlElement(name="testStep")
    public List<TestStep> getTestSteps() {
        return testSteps;
    }
}

