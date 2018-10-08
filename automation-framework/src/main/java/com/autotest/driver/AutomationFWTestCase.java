package com.autotest.driver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.autotest.configure.ResultExcelConfig;
import com.autotest.framework.excelentity.BrowserEnvTest;
import com.autotest.framework.excelentity.Scenario;
import com.autotest.framework.excelentity.TestCase;
import com.autotest.framework.excelentity.TestRun;
import com.autotest.framework.excelentity.TestStep;
import com.autotest.framework.reporting.HTMLReporting;




/**
 * @Name: AutomationFWTestCase
 * @author DatNguyen
 * @Description: This class will control all work flow of framework
 * @CreatedDate: 05/03/2014
 */
@RunWith(ExtendedRunner.class)
public class AutomationFWTestCase extends Environment {

    public static ArrayList<String> browser;
    private static Object[][] listTestcase , listStep = null;
    private static String[][] listScenario = null;
    private String[] testData = null;
    private static boolean isSkipStep , isSkipTestcase = false;
    private final ArrayList<Integer> seperateRowList = new ArrayList<Integer>();
    private final ArrayList<String> testCaseNameList = new ArrayList<String>();
    //private boolean jenkins = false;
    private boolean jenkins = true;
    private int iScenario = 0;
    private int iTestCase = 0;


    // declare variables
    ////////////////////////////////////////////////////////////////
    int iCountPassSce;
    int iCountFailSce;
    int iCountSkipSce;
    int iCountAllSce;

    int iCountPassTC    = 0;
    int iCountFailTC    = 0;
    int iCountSkipTC    = 0;
    int iCountAllTC     = 0;

    int iCountPassStep  = 0;
    int iCountFailStep  = 0;
    int iCountSkipStep  = 0;
    int iCountAllStep   = 0;

    int iRowScenario    = 0;
    int iRowTestCase    = 0;

    List listEndRowTC               = new ArrayList<String>();
    List<String> listStartRowTC     = new ArrayList<String>();

    public static List<BrowserEnvTest> listBrowserEnvTest   = new ArrayList<BrowserEnvTest>();

    //List<Scenario> listScenarios        = new ArrayList<Scenario>();
    public static List<Scenario> listScenarios;
    public static List<TestCase> listTestCases        = new ArrayList<TestCase>();
    public static List<TestStep> listSteps            = new ArrayList<TestStep>();

    public static TestRun testrun = new TestRun();
    public static BrowserEnvTest browserEnvTest;
    Scenario scenario;
    TestCase testcase;
    TestStep step;


    // Reporting
    public static HTMLReporting htmlReporting;

    enum Result {
        PASS,
        FAIL,
        SKIP,
        SKIPByInvalid
    };
    /////////////////////////////////////////////////////////////////


    public static void beforeRunner() throws Exception
    {
        try {
        	utils.createResultFolder();
        	log.configure();
            // Get framework path
            String projectPath = new File("").getAbsolutePath();

            //String projectPath = "D:/01_WORKING/09_LIBERTY/AutomationCode/automationtest";

            projectPath = projectPath.replace("\\", "/");
            String frameworkPath = projectPath.substring(0, projectPath.lastIndexOf("/"));

            //String frameworkPath = "D:/01_WORKING/09_LIBERTY/AutomationCode/";

            String projectName = projectPath.substring(projectPath.lastIndexOf("/") + 1);
            log.info("This is project path...." + projectPath);

            // Config FW
            AutoTestConstants.FWConfigPath = frameworkPath + "/" + AutoTestConstants.CONFIG_FOLDER + "/" + AutoTestConstants.CONFIG_FILENAME;
            ConfigurationLoading.getProjectConfig();
            ConfigurationLoading.getExcelFWConfig();

            // Get test run environment
            AutomationFWTestCase.testrun.setProjectName(utils.getXmlNodeValue("project", "PROJECT_NAME"));

            // create a Screenshot folder
            AutoTestConstants.screenshootFolder = AutoTestConstants.resultFolder + "IMG/";
            new File(AutoTestConstants.screenshootFolder).mkdir();

            // copy JavaScript folder to Results folder
            String jsFolder = AutoTestConstants.resultFolder + "js/";
            new File(jsFolder).mkdir();
            utils.copyJavaScriptFoldertoResult(projectPath + "/src/main/java/com/cerebos/javascript", jsFolder);

            AutoTestConstants.excelDataInput = frameworkPath + "/" + AutoTestConstants.DATAINPUT_FOLDER + "/" + AutoTestConstants.EXCEL_FILENAME;
            AutoTestConstants.firefoxProfile = projectPath + "/" + AutoTestConstants.JARS_FOLDER + "/FirefoxProfile";
            AutoTestConstants.ieDriver = projectPath + "/" + AutoTestConstants.JARS_FOLDER + "/IEDriverServer.exe";
            AutoTestConstants.chromeDriver = projectPath + "/" + AutoTestConstants.JARS_FOLDER + "/chromedriver.exe";

            log.info("DA CHAY DC TOI DAY");
            // Get all scripts of Application
            excelhandle.getAllScript();

            //Get list browser
            AutomationFWTestCase.browser = excelhandle.getBrowsers();
            // print out Cross browser
            String logText = "CROSS BROWSER FOR RUNNING TEST: ";
            for(int i=0; i< AutomationFWTestCase.browser.size(); i++)
                if(i == AutomationFWTestCase.browser.size() - 1)
                    logText = logText + AutomationFWTestCase.browser.get(i).toUpperCase();
                else
                    logText = logText + AutomationFWTestCase.browser.get(i).toUpperCase() + "-";

            log.info(logText);
            log.info(log.logLine);
            log.info("DA CHAY DC TOI DAY 2");
            if (AutoTestConstants.crossBrowser < AutomationFWTestCase.browser.size()) {
                if (AutomationFWTestCase.browser.get(AutoTestConstants.crossBrowser) != null) {
                    AutoTestConstants.RUNTIME_BROWSER = AutomationFWTestCase.browser.get(AutoTestConstants.crossBrowser);
                    log.info("================== PROJECT NAME: " + projectName + " ==================");
                    log.info("");
                }
            }
        }
        catch (Exception e) {
            log.error("COULD NOT EXECUTE BEFORERUNNER");
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
        }


    }


    /**
     * This class is Driver Script: drive our Automation Framework by Junit .
     * @throws Exception string message
     */
    //@Test
    //@Repeat(AutoTestConstants.NUMBROWSER)
    public void driverScript() throws Exception {

        try {

            // Declare variable for run-time executing test suite
            String curScenario, curScenarioStatus, curTestCaseID, curTestCaseStatus;
            int curRunNumber;
log.info("this is 1");
            if (AutoTestConstants.crossBrowser < browser.size()) {

                listScenarios = new ArrayList<Scenario>();

                // Create BrowserEnvTest object
                browserEnvTest = new BrowserEnvTest();

                // Get runtime browser
                AutoTestConstants.curBrowser = browser.get(AutoTestConstants.crossBrowser);
                browserEnvTest.setBrowserName(AutoTestConstants.curBrowser);
                browserEnvTest.setBrowserVersion(ConfigurationLoading.getPropertyBrowserVersion(AutoTestConstants.curBrowser));
                log.info("this is 2");
                // get rundate of test run
                if (AutoTestConstants.crossBrowser == 0) {
                    AutoTestConstants.testRun_RunDate = utils.getDatetimeStamp();
                }
                // Create result excel file
                AutoTestConstants.EXCEL_RESULT_FILE = excelhandle.createOutputFile(AutoTestConstants.curBrowser);
                // Create htmlReporting object
                htmlReporting = new HTMLReporting(AutoTestConstants.EXCEL_RESULT_FILE);
                // Open excel file for writing result
                excelhandle.excelWritableOpen();
                log.info("this is 3");
                log.info("================== START with " + AutoTestConstants.curBrowser.toUpperCase() + " ==================");
                log.info("");

                // Checking GRID running or not then start browser
                if (AutoTestConstants.HUBADDRESS.equals("")) {
                    driver.browserControling.startBrowser(AutoTestConstants.curBrowser);
                }
                else {
                    driver.browserControling.startBrowser(AutoTestConstants.HUBADDRESS, AutoTestConstants.curBrowser);
                }
                log.info("this is 4");
                // Get Scenario list
                listScenario = excelhandle.getScenarios();

                int index = ResultExcelConfig.ROW_RESULT_HEADER;      // variable index is used for controlling row number of writing result

                for (int i = 1; i <= listScenario.length; i++) {    // variable i is used for controlling scenario list

                    // set current Scenario is executing
                    curScenario = listScenario[i - 1][0];

                    int iTestCaseOfSce = 0;
                    isSkipTestcase = false;
                    index = index + 1;
                    excelhandle.writeResult(ResultExcelConfig.COL_SCENARIO_NAME, index, listScenario[i - 1][0]);
                    iScenario = index;
                    log.info("this is 5");
                    // Add scenario information into scenario object
                    scenario = new Scenario();
                    scenario.setIndex(iScenario);
                    scenario.setName(listScenario[i - 1][0]);
                    scenario.setTestCaseList(listScenario[i - 1][1]);
                    scenario.setDescription(excelhandle.excelReadableGetScenarioDesc(listScenario[i - 1][0]));

                    // Get List Testcase of Scenario
                    listTestcase = excelhandle.excelReadableGetTestCase(listScenario[i - 1][0]);
                    if (listTestcase.length == 0) {
                        excelhandle.writeResult(ResultExcelConfig.COL_SCENARIO_STATUS, index, "THIS SCENARIO DO NOT HAVE TESTCASE");
                        break;
                    }
                    else {
                        // Add test case name into list
                        for (int l = 0; l < listTestcase.length; l++)
                            testCaseNameList.add(listTestcase[l][0].toString());
                    }

                    excelhandle.writeResult(ResultExcelConfig.COL_START_TIME, index, utils.getDatetimeStamp());
                    log.info("this is 6");
                    // set all counting of test cases = 0
                    //                    iCountPassTC = 0;
                    //                    iCountFailTC = 0;
                    //                    iCountSkipTC = 0;
                    //                    iCountAllTC  = 0;

                    for (int j = 1; j <= listTestcase.length; j++) {
                        isSkipStep = false;
                        int runtime = Integer.parseInt(listTestcase[j - 1][1].toString());

                        String[] temp = listTestcase[j - 1][2].toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
                        ArrayList<String> setData = new ArrayList<String>();

                        if (!temp[0].equals("0")) {
                            for (int z = 0; z < temp.length; z++) {
                                setData.add(temp[z]);
                            }
                        }

                        if (runtime < setData.size()) {
                            runtime = setData.size();
                        }

                        for (int m = 1; m <= runtime; m++) {
                            // set current test case ID and run-number
                            curTestCaseID = listTestcase[j - 1][0].toString();
                            curRunNumber = m;

                            //Will process this if want to skip testcase in one scenario
                            isSkipStep = false;
                            index = index + 1;
                            excelhandle.writeResult(ResultExcelConfig.COL_TESTCASE_NUMBER, index, listTestcase[j - 1][0].toString());
                            excelhandle.writeResult(ResultExcelConfig.COL_TESTCASE_NAME, index, listTestcase[j - 1][3].toString());
                            iTestCase = index;
                            excelhandle.writeResult(ResultExcelConfig.COL_RUN_NUMBER, index, Integer.toString(m));

                            //Get List Step Name base on Test Case
                            listStep = excelhandle.excelReadableGetStepName(listTestcase[j - 1][0].toString());

                            // add test cases info to testcase object
                            testcase = new TestCase();
                            testcase.setIndex(index);
                            testcase.setID(listTestcase[j - 1][0].toString());
                            testcase.setScenarioID(scenario.getName());
                            testcase.setName(listTestcase[j - 1][3].toString());
                            testcase.setRunNumber(m);
                            testcase.setStepAmount(listStep.length);

                            if (listStep.length == 0) {
                                excelhandle.writeResult(ResultExcelConfig.COL_TESTCASE_STATUS, index, "THIS TEST CASE DO NOT HAVE STEP");
                                break;
                            }

                            int rowScenario = iScenario;
                            int rowTestCase = iTestCase;

                            excelhandle.writeResult(ResultExcelConfig.COL_START_TIME, index, utils.getDatetimeStamp());

                            for (int k = 1; k <= listStep.length; k++) {
                                index = index + 1;
                                excelhandle.writeResult(ResultExcelConfig.COL_AUTOMATION_STEPNAME, index, listStep[k - 1][0].toString());
                                excelhandle.writeResult(ResultExcelConfig.COL_STEP_NUMBER, index, listStep[k - 1][1].toString());
                                excelhandle.writeResult(ResultExcelConfig.COL_STEP_DESCRIPTION, index, listStep[k - 1][2].toString());
                                excelhandle.writeResult(ResultExcelConfig.COL_EXPECTED_RESULTS, index, listStep[k - 1][3].toString());
                                excelhandle.writeResult(ResultExcelConfig.COL_START_TIME, index, utils.getDatetimeStamp());

                                // add step info to step object
                                step = new TestStep();
                                step.setIndex(index);
                                step.setScenarioID(scenario.getName());
                                step.setTestcaseID(testcase.getID());
                                step.setAutomationName(listStep[k - 1][0].toString());
                                step.setStepNumber(listStep[k - 1][1].toString());
                                step.setStepDescription(listStep[k - 1][2].toString());
                                step.setExpectedResult(listStep[k - 1][3].toString());
                                step.setStartTimeExec(utils.getDatetimeStamp());
                                step.setScenarioID(curScenario);
                                step.setTestcaseID(curTestCaseID);
                                step.setTestcaseRunNumber(Integer.toString(curRunNumber));

                                boolean exec = false;
                                if (!isSkipStep && !isSkipTestcase) {
                                    log.info("");
                                    log.info("================== START ==================");
                                    log.info("================== Scenario ==================");
                                    log.info(listScenario[i - 1][0]);
                                    log.info("================== TestCase ==================");
                                    log.info(listTestcase[j - 1][0].toString());
                                    log.info("================== Step Name ==================");
                                    log.info(listStep[k - 1][0].toString());
                                    log.info("================== Data ==================");

                                    if (setData.size() == 0) {
                                        testData = excelhandle.excelReadableGetData(listTestcase[j - 1][0].toString(), listStep[k - 1][0].toString(), m);
                                    } else if (setData.size() > 0) {
                                        testData = excelhandle.excelReadableGetData(listTestcase[j - 1][0].toString(), listStep[k - 1][0].toString(), Integer.parseInt(setData.get(m - 1)));
                                    }

                                    if (testData.length > 0) {
                                        String data = "";
                                        for (int z = 0; z < testData.length; z++) {
                                            data = data + " -- " + testData[z];
                                        }
                                        excelhandle.writeResult(ResultExcelConfig.COL_TESTDATA, index, data);
                                    }

                                    log.info("================== Execute details ==================");
                                    exec = excelhandle.excelReadableRunStep(listStep[k - 1][0].toString(), testData);
                                    excelhandle.writeResult(ResultExcelConfig.COL_END_TIME, index, utils.getDatetimeStamp());

                                    // add step data to step object
                                    String data = "";
                                    for(int z = 0; z < testData.length; z++) {
                                        if( z == testData.length -1 )
                                            data = data + testData[z];
                                        else
                                            data = data + testData[z] + "--";
                                    }
                                    step.setTestData(data);
                                    // add end execution time to step object
                                    step.setEndTimeExec(utils.getDatetimeStamp());

                                    if (!exec) {
                                        //excelhandle.excelWritableResult(ResultExcelConfig.COL_AUTOMATION_STEP_STATUS, index, "Invalid Step name");
                                        excelhandle.writeResult(ResultExcelConfig.COL_AUTOMATION_STEP_STATUS, index, "FAIL");
                                        excelhandle.writeResult(ResultExcelConfig.COL_OBTAINED_RESULTS, index, "Invalid Step name");

                                        // add step info to step object
                                        step.setStartTimeExec("N/A");
                                        step.setEndTimeExec("N/A");
                                        step.setObtainedResult("Invalid Step name");
                                        step.setScreenshot("N/A");
                                        break;
                                    }
                                }
                                else {
                                    AutoTestConstants.result[0] = "skip";
                                    step.setStartTimeExec("N/A");
                                    step.setEndTimeExec("N/A");
                                    step.setObtainedResult("SKIP because previous step is FAIL");
                                }

                                excelhandle.excelWritableClose();

                                File check = new File(AutoTestConstants.EXCEL_RESULT_FILE);
                                Workbook workbook = Workbook.getWorkbook(check);
                                Sheet sheettemp = workbook.getSheet(AutoTestConstants.RESULTS_SHEET_NAME);
                                String status = sheettemp.getCell(ResultExcelConfig.COL_TESTCASE_STATUS, rowTestCase).getContents();

                                excelhandle.excelWritableOpen();

                                // Set executed status of step, testcase, scenario
                                if (AutoTestConstants.result[0].toString().equals("true")) {
                                    excelhandle.writeResult(ResultExcelConfig.COL_OBTAINED_RESULTS, index, AutoTestConstants.result[1].toString());
                                    excelhandle.writeResult(ResultExcelConfig.COL_AUTOMATION_STEP_STATUS, index, "PASS");
                                    // set executed status to step object
                                    step.setObtainedResult(AutoTestConstants.result[1].toString());
                                    step.setResult(Result.PASS.toString());

                                    // add step object to list
                                    listSteps.add(step);
                                    // add step into test case object
                                    testcase.getTestSteps().add(step);

                                    if (k == listStep.length) {
                                        if (!status.equals("FAIL")) {
                                            excelhandle.writeResult(ResultExcelConfig.COL_TESTCASE_STATUS, rowTestCase, "PASS");
                                            // set executed status to testcase object
                                            testcase.setResult(Result.PASS.toString());
                                            // add testcase object to list
                                            listTestCases.add(testcase);
                                            // add testcase into scenario object
                                            scenario.getTestCases().add(testcase);
                                        }

                                        if (j == listTestcase.length) {
                                            if (!sheettemp.getCell(ResultExcelConfig.COL_SCENARIO_STATUS, rowScenario).getContents().equals("FAIL")) {
                                                excelhandle.writeResult(ResultExcelConfig.COL_SCENARIO_STATUS, rowScenario, "PASS");
                                                // set executed status to scenario object
                                                scenario.setResult(Result.PASS.toString());
                                                listScenarios.add(scenario);
                                            }
                                        }
                                    }
                                }
                                else if (AutoTestConstants.result[0].toString().equals("false")) {
                                    jenkins = false;
                                    log.info("================== FAIL ==================");
                                    log.error(AutoTestConstants.result[1].toString());
                                    excelhandle.writeResult(ResultExcelConfig.COL_OBTAINED_RESULTS, index, AutoTestConstants.result[1].toString());
                                    excelhandle.writeResult(ResultExcelConfig.COL_AUTOMATION_STEP_STATUS, index, "FAIL");

                                    String screenshotPath = driver.elementEventControling.captureScreen();

                                    excelhandle.addScreenShoot(ResultExcelConfig.COL_SCREENSHOT, index, screenshotPath, "Link");

                                    excelhandle.writeResult(ResultExcelConfig.COL_TESTCASE_STATUS, rowTestCase, "FAIL");

                                    excelhandle.writeResult(ResultExcelConfig.COL_SCENARIO_STATUS, rowScenario, "FAIL");

                                    step.setObtainedResult(AutoTestConstants.result[1].toString());
                                    step.setResult(Result.FAIL.toString());
                                    step.setScreenshot(screenshotPath.replace(":", "."));
                                    listSteps.add(step);
                                    // add step into test case
                                    testcase.getTestSteps().add(step);

                                    testcase.setResult(Result.FAIL.toString());
                                    listTestCases.add(testcase);
                                    scenario.getTestCases().add(testcase);

                                    scenario.setResult(Result.FAIL.toString());
                                    listScenarios.add(scenario);

                                    //                                    if (j == listTestcase.length) {
                                    //                                        scenario.setResult(Result.FAIL.toString());
                                    //                                        listScenarios.add(scenario);
                                    //                                    }

                                    isSkipStep = true;
                                }
                                else if (AutoTestConstants.result[0].toString().equals("skip")) {

                                    excelhandle.writeResult(ResultExcelConfig.COL_AUTOMATION_STEP_STATUS, index, "SKIP");

                                    step.setObtainedResult("SKIP because previous step is FAIL");
                                    step.setResult(Result.SKIP.toString());
                                    listSteps.add(step);
                                    // add step into test case
                                    testcase.getTestSteps().add(step);

                                    if (!status.equals("FAIL")) {
                                        excelhandle.writeResult(ResultExcelConfig.COL_TESTCASE_STATUS, rowTestCase, "SKIP");
                                        testcase.setResult(Result.SKIP.toString());
                                        listTestCases.add(testcase);
                                        scenario.getTestCases().add(testcase);

                                        scenario.setResult(Result.SKIP.toString());
                                        listScenarios.add(scenario);
                                    }

                                }
                                log.info("================== END ==================");
                                log.info("");
                            }
                            index = index + 1;
                            seperateRowList.add(index);

                            // count test case of scenario
                            iTestCaseOfSce++;
                        }
                    }

                    // set test case amount of scenario
                    scenario.setTestCaseAmount(iTestCaseOfSce);
                }
                excelhandle.excelWritableSetBorder();
                excelhandle.excelWritableFillStatus();
                excelhandle.excelWritableDrawChart();
                excelhandle.excelWritableSeperate(seperateRowList);
                //excelhandle.excelWritableTestCaseGrouping(testCaseNameList);
                //excelhandle.excelWritableScenarioGrouping();
                excelhandle.excelWritableClose();
                //excelhandle.excelCopySendMail();
                ///////////////
                // get scenario obj list
                log.info("//////////////////////////////////////////////////////");
                iCountPassSce   = 0;
                iCountFailSce   = 0;
                iCountSkipSce   = 0;
                iCountAllSce    = 0;
                for (int z = 0; z < listScenarios.size(); z++) {

                    log.info("SCENARIO: " + z);
                    log.info("INDEX: " + listScenarios.get(z).getIndex());
                    log.info("NAME: " + listScenarios.get(z).getName());
                    log.info("RESULT: " + listScenarios.get(z).getResult());
                    log.info("TESTCASE AMOUNT: " + listScenarios.get(z).getTestCaseAmount());

                    if(listScenarios.get(z).getResult().equals(Result.PASS.toString())) {
                        iCountPassSce++;
                    }
                    else if(listScenarios.get(z).getResult().equals(Result.FAIL.toString())) {
                        iCountFailSce++;
                    }
                    else {
                        iCountSkipSce++;
                    }
                }
                log.info("//////////////////////////////////////////////////////");
                // get testcase obj list
                for (int z = 0; z < listTestCases.size(); z++) {
                    log.info("TESTCASE: " + z);
                    log.info("INDEX: " + listTestCases.get(z).getIndex());
                    log.info("ID: " + listTestCases.get(z).getID());
                    log.info("SCENARIO ID: " + listTestCases.get(z).getScenarioID());
                    log.info("NAME: " + listTestCases.get(z).getName());
                    log.info("RUN NUMBER: " + listTestCases.get(z).getRunNumber());
                    log.info("RESULT: " + listTestCases.get(z).getResult());
                    log.info("ERROR MESSAGE: " + listTestCases.get(z).getErrorMessage());
                    log.info("STEP AMOUNT: " + listTestCases.get(z).getStepAmount());

                    // write list step of test case
                    log.info("List step is:");
                    for(int w = 0; w < testcase.getTestSteps().size(); w++) {
                        log.info("Step number" + w + " is:" + testcase.getTestSteps().get(w).getAutomationName());
                    }

                    if(listTestCases.get(z).getResult().equals(Result.PASS.toString())) {
                        iCountPassTC++;
                    }
                    else if(listTestCases.get(z).getResult().equals(Result.FAIL.toString())) {
                        iCountFailTC++;
                    }
                    else {
                        iCountSkipTC++;
                    }
                }
                // get step obj list
                log.info("//////////////////////////////////////////////////////");
                for (int z = 0; z < listSteps.size(); z++) {
                    log.info("STEP: " + z);
                    log.info("INDEX: " + listSteps.get(z).getIndex());
                    log.info("SCENARIO ID: " + listSteps.get(z).getScenarioID());
                    log.info("TESTCASE ID: " + listSteps.get(z).getTestcaseID());
                    log.info("AUTOMATION NAME: " + listSteps.get(z).getAutomationName());
                    log.info("STEP NUMBER: " + listSteps.get(z).getStepNumber());
                    log.info("STEP DESCRIPTION: " + listSteps.get(z).getStepDescription());
                    log.info("EXPECTED RESULT: " + listSteps.get(z).getExpectedResult());
                    log.info("RESULT: " + listSteps.get(z).getResult());
                    log.info("OBTAINED RESULT: " + listSteps.get(z).getObtainedResult());
                    log.info("SCREENSHOT: " + listSteps.get(z).getScreenshot());
                    log.info("START TIME EXEC: " + listSteps.get(z).getStartTimeExec());
                    log.info("END TIME EXEC: " + listSteps.get(z).getEndTimeExec());

                    if(listSteps.get(z).getResult().equals(Result.PASS.toString())) {
                        iCountPassStep++;
                    }
                    else if(listSteps.get(z).getResult().equals(Result.FAIL.toString())) {
                        iCountFailStep++;
                    }
                    else {
                        iCountSkipStep++;
                    }
                }
                //////////////////////////////////////////////////////////////
                log.info("SUMMARY");
                log.info("Total SCENARIOS: " + listScenarios.size());
                log.info("Passed Scenarios: " + iCountPassSce);
                log.info("Failed Scenarios: " + iCountFailSce);
                log.info("Skiped Scenarios: " + iCountSkipSce);
                log.info("");
                log.info("Total TESTCASES: " + listTestCases.size());
                log.info("Passed Testcases: " + iCountPassTC);
                log.info("Failed Testcases: " + iCountFailTC);
                log.info("Skiped Testcases: " + iCountSkipTC);
                log.info("");
                log.info("Total TEST STEPS: " + listSteps.size());
                log.info("Passed Steps: " + iCountPassStep);
                log.info("Failed Steps: " + iCountFailStep);
                log.info("Skiped Steps: " + iCountSkipStep);

                // add information for BrowserEnvTest object
                browserEnvTest.setPassScenarios(iCountPassSce);
                browserEnvTest.setFailScenarios(iCountFailSce);
                browserEnvTest.setSkipScenarios(iCountSkipSce);
                listBrowserEnvTest.add(browserEnvTest);
                // generate html report
                //htmlReporting.parseXLS();

                /*
                if (!jenkins) {
                    assertTrue("PROJECT FAIL", false);
                }
                */


                //excelhandle.excelWritableClose();

            }
            else if (browser.size() == 0) {
                log.info("PLEASE CONFIGURE AT LEAST ONE BROWSER FOR RUN");
            }
        } catch (Exception e) {
            excelhandle.excelWritableClose();
            log.error("COULD NOT FINISH RUN AUTOMATION BECAUSE EXCEPTION: " + e.getMessage());
        }
    }


    @After
    public void afterTest() throws Exception
    {
        AfterRunner.afterTestRunner();
    }

    @AfterClass
    public static void afterClass() throws Exception
    {
        AfterRunner.afterClassRunner();
    }






}

