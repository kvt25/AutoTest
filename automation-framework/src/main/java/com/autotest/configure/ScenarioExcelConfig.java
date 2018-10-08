package com.autotest.configure;

public class ScenarioExcelConfig
{
	private ScenarioExcelConfig () {
		throw new IllegalStateException("ScenarioExcelConfig Class");
	}
	
    public static final String XML_CATEGORY = "scenarioSheetConfig";
    public static final String ROW_BROWSER_NAME = "ROW_BROWSER_NAME";
    public static final String COL_BROWSER_NAME = "COL_BROWSER_NAME";
    public static final String ROW_SCENARIO_HEADER = "ROW_SCENARIO_HEADER";
    public static final String COL_SCENARIO_NAME = "COL_SCENARIO_NAME";
    public static final String COL_EXECUTE_FLAG = "COL_EXECUTE_FLAG";
    public static final String COL_TESTCASEIDS = "COL_TESTCASEIDS";
    public static final String COL_DESCRIPTION = "COL_DESCRIPTION";
}
