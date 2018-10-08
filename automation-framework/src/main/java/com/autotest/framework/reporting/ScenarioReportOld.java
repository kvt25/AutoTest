package com.autotest.framework.reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScenarioReportOld
{
    public static void main(String[] args) {
        ScenarioReportOld a = new ScenarioReportOld();
        a.createTestScenarioReport();
    }

    private final String reportFile;
    private final String pageTitle = "TEST SCENARIO DETAIL REPORT";


    public ScenarioReportOld() {
        //this.reportFile = AutoTestConstants.excelResultFile;
        this.reportFile = "D:/01_WORKING/01_JAVA/AutomationWorkspaceHybris/Results/AutomationExcelFW_13-51-41_24-Jun-2014/AutomationExcelFW_FirefoxDefault_13-51-42_24-Jun-2014.xls";
    }

    //public void createTestScenarioReport(String scenarioName, List<TestCase> listTestCases) {
    public void createTestScenarioReport() {
        try {
            File file = new File(this.reportFile.substring(0, this.reportFile.indexOf(".xls")) + HTMLTagFormat.HTML_FILE_EXTENSION);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bf = new BufferedWriter(fw);

            // insert page title
            bf.write("<html><head><title>Automation Test Results</title></head>");
            bf.write("<body>");

            // insert page name
            bf.write("<h4 align=\"center\">"
                    + "<font size=\"6\" face=\"Arial\" color=\"660066\">"
                    + "<b><u>Test Scenario Results</u></b></font></h4>");
            bf.write("<h4><font size=\"6\" face=\"Arial\" color=\"660066\"><font size=\"4.5\" face=\"Arial\" color=\"660000\">"
                    + "<u>Test Details :</u></font></font></h4>");

            // insert information table
            bf.write("<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\">"
                    + "<tbody>"

                    + "<tr>"
                    + "<td width=\"150\" bgcolor=\"#153E7E\" align=\"left\">"
                    + "<font size=\"2.75\" face=\"Arial\" color=\"#E0E0E0\">"
                    + "<b>Run Date</b>"
                    + "</font>"
                    + "</td>"
                    + "<td width=\"150\" align=\"left\">"
                    + "<font size=\"2.75\" face=\"Arial\" color=\"#153E7E\">"
                    + "<b>Thu Dec 22 01:11:24 GMT+05:30 2011</b>"
                    + "</font>"
                    + "</td>"
                    + "</tr>"

                    + "<tr>"
                    + "<td width=\"150\" bgcolor=\"#153E7E\" align=\"left\">"
                    + "<font size=\"2.75\" face=\"Arial\" color=\"#E0E0E0\">"
                    + "<b>Run Environment</b>"
                    + "</font>"
                    + "</td><td width=\"150\" align=\"left\">"
                    + "<font size=\"2.75\" face=\"Arial\" color=\"#153E7E\">"
                    + "<b>Production</b>"
                    + "</font>"
                    + "</td>"
                    + "</tr>"

                    + "<tr>"
                    + "<td width=\"150\" bgcolor=\"#153E7E\" align=\"left\">"
                    + "<font size=\"2.75\" face=\"Arial\" color=\"#E0E0E0\"><b>Release</b></font></td><td width=\"150\" align=\"left\"><font size=\"2.75\" face=\"Arial\" color=\"#153E7E\"><b>Beta 1.3</b></font></td></tr></tbody></table>");

            bf.write("</body></html>");
            bf.close();
            //            File f = new File(testSuiteReportFile);
            //            if (!f.getParentFile().exists()) {
            //                f.getParentFile().mkdirs();
            //            }
            //            FileWriter fw = new FileWriter(f, true);
            //            BufferedWriter bf = new BufferedWriter(fw);
            //
            //            bf.write("<html><head><title>Test Suite Detail Report</title></head>");
            //            bf.write("<body><p><FORM><INPUT TYPE=\"button\" VALUE=\"Back to Test Run\" onClick=\"history.go(-1);return true;\"></FORM> </p>");
            //            bf.write("<p>Test Suite Detail Report</p>");
            //            bf.write("<table width=400 border=1>");
            //            bf.write("<tr><td width= 200><div align=right>Test Suite Name: </div></td><td width=200><a href=\"./../chart/"
            //                    + summary.getReportFile() + "\" target=\"_blank\">" + testSuite.getName() + "</td></tr>");
            //            bf.write("<tr><td width= 200><div align=right>Run on Browser: </div></td><td width=200>"
            //                    + testSuite.getBrowser() + "</td></tr>");
            //            bf.write("<tr><td><div align=right>Start Time: </div></td><td>" + summary.getStartTime()
            //                + "</td></tr>");
            //            bf.write("<tr><td><div align=right>End Time: </div></td><td>" + summary.getEndTime() + "</td></tr>");
            //            int total = testSuite.getPassStep()
            //                    + testSuite.getFailStep() + testSuite.getBlockStep() + testSuite.getWarningStep();
            //            bf.write("<tr><td><div align=right>Total TestSteps: </div></td><td>" + total + "</td></tr>");
            //            bf.write("<tr><td><div align=right>Passed TestSteps: </div></td><td>" + testSuite.getPassStep()
            //                + "</td></tr>");
            //            bf.write("<tr><td><div align=right>Failed TestSteps: </div></td><td>" + testSuite.getFailStep()
            //                + "</td></tr>");
            //            bf.write("<tr><td><div align=right>Blocked TestSteps: </div></td><td>"
            //                    + testSuite.getBlockStep() + "</td></tr>");
            //            bf.write("<tr><td><div align=right>Warning TestSteps: </div></td><td>"
            //                    + testSuite.getWarningStep() + "</td></tr>");
            //            if (testSuite.getStatus().equals(EStatus.PASSED)) {
            //                bf.write("<tr><td><div align=right>Status: </div></td><td bgcolor=\"00FF00\">"
            //                        + testSuite.getStatus() + "</td></tr>");
            //            } else if (testSuite.getStatus().equals(EStatus.FAILED)) {
            //                bf.write("<tr><td><div align=right>Status: </div></td><td bgcolor=\"FF0000\">"
            //                        + testSuite.getStatus() + "</td></tr>");
            //            } else {
            //                bf.write("<tr><td><div align=right>Status: </div></td><td>"
            //                        + testSuite.getStatus() + "</td></tr>");
            //            }
            //            bf.write("</table>");
            //            bf.write("<p>List of test cases and test steps in details:</p>");
            //            bf.write("<table width=100% border=1><tr  bgcolor=\"3399FF\"><td><strong>No.</strong></td><td><strong>TestCase Name</strong></td><td><strong>TestCase Description</strong></td><td><strong>TestCase Status</strong></td><td><strong>No.</strong></td><td><strong>Test Step Description</strong></td><td><strong>Expected Result</strong></td><td><strong>Actual Result</strong></td><td><strong>TestStep Status</strong></td><td></tr>");
            //
            //            for (TestCase tc : testSuite.getListTestCase()) {
            //                bf.write("<tr><td>" + tc.getIndex() + "</td>");
            //                bf.write("<td>" + tc.getName() + "</td>");
            //                bf.write("<td>" + tc.getDescription() + "</td>");
            //                if (tc.getStatus().equals(EStatus.FAILED)) {
            //                    bf.write("<td bgcolor=\"FF0000\">" + tc.getStatus() + "</td>");
            //                } else if (tc.getStatus().equals(EStatus.PASSED)) {
            //                    bf.write("<td bgcolor=\"00FF00\">" + tc.getStatus() + "</td>");
            //                } else {
            //                    bf.write("<td>" + tc.getStatus()  + "</td>");
            //                }
            //                for (TestStep testStep : tc.getListTestStep()) {
            //                    if (tc.getListTestStep().indexOf(testStep) != 0) {
            //                        bf.write("<tr><td></td>");
            //                        bf.write("<td></td>");
            //                        bf.write("<td></td>");
            //                        bf.write("<td></td>");
            //                    }
            //                    bf.write("<td>" + testStep.getIndex() + "</td>");
            //                    bf.write("<td>" + testStep.getDescription() + "</td>");
            //                    bf.write("<td>" + testStep.getExpected() + "</td>");
            //
            //                    // Process Step Status
            //                    if (testStep.getStatus().equals(EStatus.FAILED) || testStep.getStatus().equals(EStatus.WARNING)) {
            //                        bf.write("<td> <a href=\"./../" + testStep.getScreenShot() + "\" target=\"_blank\">"
            //                                + testStep.getActualResult() + "</a></td>");
            //                    } else if (testStep.getStatus().equals(EStatus.BLOCKED)) {
            //                        bf.write("<td>" + EStatus.BLOCKED + "</td>");
            //                    } else {
            //                        bf.write("<td>" + testStep.getActualResult() + "</td>");
            //                    }
            //
            //                    // Process Actual Result
            //                    if (testStep.getStatus().equals(EStatus.PASSED)) {
            //                        bf.write("<td bgcolor=\"00FF00\">" + testStep.getStatus() + "</td></tr>");
            //                    } else if (testStep.getStatus().equals(EStatus.FAILED)) {
            //                        bf.write("<td bgcolor=\"FF0000\">" + testStep.getStatus() + "</td></tr>");
            //                    } else if (testStep.getStatus().equals(EStatus.BLOCKED)) {
            //                        bf.write("<td bgcolor=\"FFFF00\">" + testStep.getStatus() + "</td></tr>");
            //                    } else if (testStep.getStatus().equals(EStatus.WARNING)) {
            //                        bf.write("<td bgcolor=\"FFA500\">" + testStep.getStatus() + "</td></tr>");
            //                    }
            //                }
            //            }
            //

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
