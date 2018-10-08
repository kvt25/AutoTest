package com.autotest.driver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.autotest.configure.ChartConfig;
import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.autotest.configure.InputDataExcelConfig;
import com.autotest.configure.ResultExcelConfig;
import com.autotest.configure.ScenarioExcelConfig;
import com.autotest.configure.TestCaseExcelConfig;
import com.autotest.utils.StackTraceInfo;

public class ConfigurationLoading extends Environment
{
    public static void getProjectConfig() throws Exception {
        try {
            if(AutoTestConstants.FWConfigPath != "") {
                // set project config
                AutoTestConstants.PROJECT_NAME = utils.getXmlNodeValue("project", "PROJECT_NAME");
                AutoTestConstants.BUILD_VERSION = utils.getXmlNodeValue("project", "BUILD_VERSION");
                AutoTestConstants.BUILD_DATE = utils.getXmlNodeValue("project", "BUILD_DATE");

                // set file config
                AutoTestConstants.EXCEL_FILENAME = utils.getXmlNodeValue("file", "EXCEL_FILENAME");

                log.info("DONE: CONFIGURED PROJECT VARIABLES");
                log.info(log.logLine);
            }
            else {
                log.error("FW CONFIGURE PATH WAS NOT SET!");
            }
        }
        catch(Exception e) {
            log.error("ERROR ON SETTING PROJECT CONFIGURATION");
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }

    public static void getExcelFWConfig() throws Exception{
        try {
            if(AutoTestConstants.FWConfigPath != "") {

                // set ROW_RESULT_HEADER
                ResultExcelConfig.ROW_RESULT_HEADER = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "ROW_RESULT_HEADER"));

                // set ExcelConfig variables of Scenario sheet
                ScenarioExcelConfig.ROW_BROWSER_NAME = Integer.parseInt(utils.getXmlNodeValue(ScenarioExcelConfig.XML_CATEGORY, "ROW_BROWSER_NAME"));
                ScenarioExcelConfig.COL_BROWSER_NAME = Integer.parseInt(utils.getXmlNodeValue(ScenarioExcelConfig.XML_CATEGORY, "COL_BROWSER_NAME"));
                ScenarioExcelConfig.ROW_SCENARIO_HEADER = Integer.parseInt(utils.getXmlNodeValue(ScenarioExcelConfig.XML_CATEGORY, "ROW_SCENARIO_HEADER"));
                ScenarioExcelConfig.COL_SCENARIO_NAME = Integer.parseInt(utils.getXmlNodeValue(ScenarioExcelConfig.XML_CATEGORY, "COL_SCENARIO_NAME"));
                ScenarioExcelConfig.COL_EXECUTE_FLAG = Integer.parseInt(utils.getXmlNodeValue(ScenarioExcelConfig.XML_CATEGORY, "COL_EXECUTE_FLAG"));
                ScenarioExcelConfig.COL_TESTCASEIDS = Integer.parseInt(utils.getXmlNodeValue(ScenarioExcelConfig.XML_CATEGORY, "COL_TESTCASEIDS"));
                ScenarioExcelConfig.COL_DESCRIPTION = Integer.parseInt(utils.getXmlNodeValue(ScenarioExcelConfig.XML_CATEGORY, "COL_DESCRIPTION"));

                // set ExcelConfig variables of TestCase sheet
                TestCaseExcelConfig.ROW_TESTCASE_HEADER = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "ROW_TESTCASE_HEADER"));
                TestCaseExcelConfig.COL_TESTCASEID = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_TESTCASEID"));
                TestCaseExcelConfig.COL_TESTCASE_NAME = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_TESTCASE_NAME"));
                TestCaseExcelConfig.COL_EXECUTE_FLAG = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_EXECUTE_FLAG"));
                TestCaseExcelConfig.COL_EXECUTE_TIMES = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_EXECUTE_TIMES"));
                TestCaseExcelConfig.COL_TESTDATA = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_TESTDATA"));
                TestCaseExcelConfig.COL_AUTOMATION_STEPNAME = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_AUTOMATION_STEPNAME"));
                TestCaseExcelConfig.COL_STEP_NUMBER = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_STEP_NUMBER"));
                TestCaseExcelConfig.COL_STEP_DESCRIPTION = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_STEP_DESCRIPTION"));
                TestCaseExcelConfig.COL_EXPECTED_RESULT = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_EXPECTED_RESULT"));
                TestCaseExcelConfig.COL_TESTCASE_TYPE = Integer.parseInt(utils.getXmlNodeValue(TestCaseExcelConfig.XMLCategory, "COL_TESTCASE_TYPE"));

                // set ExcelConfig variables of InputData sheet
                InputDataExcelConfig.ROW_INPUTDATA_HEADER = Integer.parseInt(utils.getXmlNodeValue(InputDataExcelConfig.XMLCategory, "ROW_INPUTDATA_HEADER"));
                InputDataExcelConfig.COL_VARIABLE_NAME = Integer.parseInt(utils.getXmlNodeValue(InputDataExcelConfig.XMLCategory, "COL_VARIABLE_NAME"));
                InputDataExcelConfig.COL_VARIABLE_VALUE = Integer.parseInt(utils.getXmlNodeValue(InputDataExcelConfig.XMLCategory, "COL_VARIABLE_VALUE"));
                InputDataExcelConfig.COL_FUNCTION_NAME = Integer.parseInt(utils.getXmlNodeValue(InputDataExcelConfig.XMLCategory, "COL_FUNCTION_NAME"));
                InputDataExcelConfig.COL_TESTDATA_NAME = Integer.parseInt(utils.getXmlNodeValue(InputDataExcelConfig.XMLCategory, "COL_TESTDATA_NAME"));
                InputDataExcelConfig.COL_TESTDATA_VALUE1 = Integer.parseInt(utils.getXmlNodeValue(InputDataExcelConfig.XMLCategory, "COL_TESTDATA_VALUE1"));

                // set ExcelConfig variables of Result sheet
                ResultExcelConfig.ROW_RESULT_HEADER = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "ROW_RESULT_HEADER"));
                ResultExcelConfig.COL_SCENARIO_NAME = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_SCENARIO_NAME"));
                ResultExcelConfig.COL_SCENARIO_STATUS = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_SCENARIO_STATUS"));
                ResultExcelConfig.COL_TESTCASE_NUMBER = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_TESTCASE_NUMBER"));
                ResultExcelConfig.COL_RUN_NUMBER = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_RUN_NUMBER"));
                ResultExcelConfig.COL_TESTCASE_STATUS = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_TESTCASE_STATUS"));
                ResultExcelConfig.COL_TESTCASE_NAME = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_TESTCASE_NAME"));
                ResultExcelConfig.COL_AUTOMATION_STEPNAME = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_AUTOMATION_STEPNAME"));
                ResultExcelConfig.COL_TESTDATA = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_TESTDATA"));
                ResultExcelConfig.COL_STEP_NUMBER = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_STEP_NUMBER"));
                ResultExcelConfig.COL_STEP_DESCRIPTION = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_STEP_DESCRIPTION"));
                ResultExcelConfig.COL_EXPECTED_RESULTS = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_EXPECTED_RESULTS"));
                ResultExcelConfig.COL_OBTAINED_RESULTS = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_OBTAINED_RESULTS"));
                ResultExcelConfig.COL_AUTOMATION_STEP_STATUS = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_AUTOMATION_STEP_STATUS"));
                ResultExcelConfig.COL_SCREENSHOT = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_SCREENSHOT"));
                ResultExcelConfig.COL_START_TIME = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_START_TIME"));
                ResultExcelConfig.COL_END_TIME = Integer.parseInt(utils.getXmlNodeValue(ResultExcelConfig.XMLCategory, "COL_END_TIME"));

                // set ExcelConfig variables of Chart
                ChartConfig.COL_STEP_PASSED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "COL_STEP_PASSED"));
                ChartConfig.ROW_STEP_PASSED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "ROW_STEP_PASSED"));
                ChartConfig.COL_STEP_FAILED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "COL_STEP_FAILED"));
                ChartConfig.ROW_STEP_FAILED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "ROW_STEP_FAILED"));
                ChartConfig.COL_STEP_SKIPED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "COL_STEP_SKIPED"));
                ChartConfig.ROW_STEP_SKIPED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "ROW_STEP_SKIPED"));

                ChartConfig.COL_TESTCASE_PASSED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "COL_TESTCASE_PASSED"));
                ChartConfig.ROW_TESTCASE_PASSED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "ROW_TESTCASE_PASSED"));
                ChartConfig.COL_TESTCASE_FAILED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "COL_TESTCASE_FAILED"));
                ChartConfig.ROW_TESTCASE_FAILED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "ROW_TESTCASE_FAILED"));
                ChartConfig.COL_TESTCASE_SKIPED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "COL_TESTCASE_SKIPED"));
                ChartConfig.ROW_TESTCASE_SKIPED = Integer.parseInt(utils.getXmlNodeValue(ChartConfig.XMLCategory, "ROW_TESTCASE_SKIPED"));

                log.info("DONE: CONFIGURED EXCEL FRAMEWORK VARIABLES");
                log.info(log.logLine);
            }
            else {
                log.error("FW CONFIGURE PATH WAS NOT SET!");
            }
        }
        catch(Exception e) {
            log.error("ERROR ON SETTING EXCEL CONFIGURATION");
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
    }

	public enum Browser {
		FirefoxDefault, Chrome, InternetExplorer, Safari, Opera
	}

	public static String getPropertyBrowserVersion(String browserName)
			throws Exception {
		String browserVer = "";
		try {
			Properties prop = new Properties();
			InputStream input = null;
			input = new FileInputStream(
					"src/main/java/com/cerebos/properties/browsersetting.properties");

			// load a properties file
			prop.load(input);
			// get the property value
			switch (Browser.valueOf(browserName)) {
			case FirefoxDefault:
				browserVer = prop.getProperty("firefox_version");
				break;
			case Chrome:
				browserVer = prop.getProperty("chromedriver_version");
				break;
			case InternetExplorer:
				browserVer = prop.getProperty("ie_version");
				break;
			case Safari:
				browserVer = prop.getProperty("safari_version");
				break;
			case Opera:
				browserVer = prop.getProperty("opera_version");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			log.error("ERROR ON LOADING BROWSER VERSION PROPERTY");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab
					+ e.getMessage());
			throw new Exception();
		}
		return browserVer;

	}

    /*public static String getPropertyBrowserVersion(String browserName) throws Exception{
        String browserVer = "";
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream("src/main/java/com/oneliberty/properties/browsersetting.properties");

            // load a properties file
            prop.load(input);
            // get the property value
            switch (browserName) {
                case "FirefoxDefault":  browserVer = prop.getProperty("firefox_version");
                break;
                case "Chrome": browserVer = prop.getProperty("chromedriver_version");
                break;
                case "InternetExplorer":  browserVer = prop.getProperty("ie_version");
                break;
                case "Safari":  browserVer = prop.getProperty("safari_version");
                break;
                case "Opera":  browserVer = prop.getProperty("opera_version");
                break;
                default:
                    break;
            }
        }
        catch(Exception e) {
            log.error("ERROR ON LOADING BROWSER VERSION PROPERTY");
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
            throw new Exception();
        }
        return browserVer;

    }*/
}
