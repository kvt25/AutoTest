package com.autotest.framework.excelentity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


/**
 * @Name: TestCase
 * @author DatNguyen
 * @Description: This class is declare TestCase object and attributes
 * @CreatedDate: 05/03/2014
 */
public class TestCase
{
    private int _index;
    private String _id;
    private String _scenarioID;
    private String _name;
    private int _runNumber;
    private String _result;
    private String _errorMessage;
    private int _stepAmount;
    private final List<TestStep> testSteps = new ArrayList<TestStep>();

    public TestCase() {

    }

    public int getIndex() {
        return this._index;
    }

    public void setIndex(int index) {
        this._index = index;
    }

    public String getID() {
        return this._id;
    }

    public void setID(String id) {
        this._id = id;
    }

    public String getScenarioID() {
        return this._scenarioID;
    }

    public void setScenarioID(String scenarioid) {
        this._scenarioID = scenarioid;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public int getRunNumber() {
        return this._runNumber;
    }

    public void setRunNumber(int runNumber) {
        this._runNumber = runNumber;
    }

    public String getResult() {
        return this._result;
    }

    public void setResult(String result) {
        this._result = result;
    }

    public String getErrorMessage() {
        return this._errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this._errorMessage = errorMessage;
    }

    public int getStepAmount() {
        return this._stepAmount;
    }

    public void setStepAmount(int stepAmount) {
        this._stepAmount = stepAmount;
    }

    @XmlElement(name="testStep")
    public List<TestStep> getTestSteps() {
        return testSteps;
    }
}