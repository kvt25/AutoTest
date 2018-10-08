package com.autotest.framework.excelentity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name: Scenario
 * @author DatNguyen
 * @Description: This class is declare Scenario object and attributes
 * @CreatedDate: 05/03/2014
 */
public class Scenario
{
    private int _index;
    private String _name;
    private String _testcaseList;
    private String _description;
    private String _result;
    private int _testcaseAmount;
    private final List<TestCase> listTestCase = new ArrayList<TestCase>();

    public Scenario() {

    }

    public int getIndex() {
        return _index;
    }

    public void setIndex(int index) {
        this._index = index;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public String getTestCaseList() {
        return _testcaseList;
    }

    public void setTestCaseList(String testcaseList)
    {
        this._testcaseList = testcaseList;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description)
    {
        this._description = description;
    }

    public String getResult() {
        return _result;
    }

    public void setResult(String result) {
        this._result = result;
    }

    public int getTestCaseAmount() {
        return _testcaseAmount;
    }

    public void setTestCaseAmount(int TestCaseAmount) {
        this._testcaseAmount = TestCaseAmount;
    }

    public List<TestCase> getTestCases() {
        return listTestCase;
    }
}
