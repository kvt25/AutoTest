package com.autotest.driver;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.autotest.utils.StackTraceInfo;

public class AfterRunner extends Environment
{

    public static void afterTestRunner() {
        try {
            if (AutoTestConstants.crossBrowser < AutomationFWTestCase.browser.size()) {
                log.info("");
                log.info("================== END with " + AutomationFWTestCase.browser.get(AutoTestConstants.crossBrowser).toUpperCase() + " ==================");
                log.info("");
                AutoTestConstants.crossBrowser = AutoTestConstants.crossBrowser + 1;
                //driver.browserControling.browserQuit();

                // 18/11/2015: disable generate report via HTML format
                /*
                // Generate BrowserEnvTest report via HTML format
                SchemaGeneration.createTestScenarioReport();
                ScenarioReport scenarioReport = new ScenarioReport();
                // add browserEnvTest object to ScenarioReport
                scenarioReport.getBrowserEnvTests().add(AutomationFWTestCase.browserEnvTest);
                // add testscenario objects to ScenarioReport
                for(int i = 0; i < AutomationFWTestCase.listScenarios.size(); i++) {
                    scenarioReport.getScenarios().add(AutomationFWTestCase.listScenarios.get(i));
                }
                // add testscase objects to ScenarioReport
                for(int i = 0; i < AutomationFWTestCase.listTestCases.size(); i++) {
                    scenarioReport.getTestCases().add(AutomationFWTestCase.listTestCases.get(i));
                }
                // add teststep objects to ScenarioReport
                for(int i = 0; i < AutomationFWTestCase.listSteps.size(); i++) {
                    scenarioReport.getTestSteps().add(AutomationFWTestCase.listSteps.get(i));
                }

                // Create Transformer
                TransformerFactory tf = TransformerFactory.newInstance();
                //StreamSource xslt = new StreamSource("src/main/java/com/sai/framework/reporting/ScenarioReportStylesheet.xsl");
                StreamSource xslt = new StreamSource("src/main/resources/ScenarioReportStylesheet.xsl");
                Transformer transformer = tf.newTransformer(xslt);

                // Source
                JAXBContext jc = JAXBContext.newInstance(ScenarioReport.class);
                JAXBSource source = new JAXBSource(jc, scenarioReport);

                // Result
                StringWriter writer = new StringWriter();
                StreamResult result = new StreamResult(writer);

                // Transform
                transformer.transform(source, result);
                String testRunFilePath = AutoTestConstants.excelResultFile.substring(0, AutoTestConstants.excelResultFile.lastIndexOf(".xls")) + ".html";
                File file = new File(testRunFilePath);
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bf = new BufferedWriter(fw);

                // insert page title
                bf.write(writer.toString());
                bf.close();

                // add file path to BrowserEnvTest object
                AutomationFWTestCase.browserEnvTest.setLinkResult(testRunFilePath.substring(testRunFilePath.lastIndexOf("/") + 1));
                */
            }
        }
        catch(Exception e) {
            log.error("COULD NOT EXECUTE AFTER TEST RUNNER");
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());

        }
    }

    public static void afterClassRunner() {
        try {
            // end of test run
            getTestRunEnv();
            
            // 18/11/2015: disable generate report via HTML format
            /*
            // generate test run report via HTML format
            SchemaGeneration.createTestRunReport();
            TestRunReport testrunreport = new TestRunReport();
            testrunreport.getTestRuns().add(AutomationFWTestCase.testrun);
            for(int i = 0; i < AutomationFWTestCase.listBrowserEnvTest.size(); i++) {
                testrunreport.getBrowserEnvTests().add(AutomationFWTestCase.listBrowserEnvTest.get(i));
            }

            // Create Transformer
            TransformerFactory tf = TransformerFactory.newInstance();
            //StreamSource xslt = new StreamSource("src/main/java/com/sai/framework/reporting/TestRunReportStylesheet.xsl");
            StreamSource xslt = new StreamSource("src/main/resources/TestRunReportStylesheet.xsl");
            Transformer transformer = tf.newTransformer(xslt);

            // Source
            JAXBContext jc = JAXBContext.newInstance(TestRunReport.class);
            JAXBSource source = new JAXBSource(jc, testrunreport);

            // Result
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            // Transform
            transformer.transform(source, result);
            String testRunFilePath = AutoTestConstants.excelResultFile.substring(0, AutoTestConstants.excelResultFile.lastIndexOf("/")) + "/masterReport.html";
            File file = new File(testRunFilePath);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bf = new BufferedWriter(fw);

            // insert page title
            bf.write(writer.toString());
            bf.close();
            */
            // sending email
            //utils.sendEmailReport();
        }
        catch(Exception e) {
            log.error("COULD NOT EXECUTE AFTER CLASS RUNNER");
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
        }
    }

    public static void getTestRunEnv() {
        try {
            // Get Project Name
            AutomationFWTestCase.testrun.setProjectName(AutoTestConstants.PROJECT_NAME);
            // Get Run Date
            AutomationFWTestCase.testrun.setRunDate(AutoTestConstants.testRun_RunDate);
            // Get Window Name
            AutomationFWTestCase.testrun.setWindowName(System.getProperty("os.name"));
            // Get Window Version
            AutomationFWTestCase.testrun.setWindowVersion(System.getProperty("os.version"));
            // Get Browsers Running
            String browserList = "";
            for (int i=0; i< AutomationFWTestCase.browser.size(); i++) {
                if(i == AutomationFWTestCase.browser.size()-1) {
                    browserList = browserList + AutomationFWTestCase.browser.get(i);
                }
                else {
                    browserList = browserList + AutomationFWTestCase.browser.get(i) + "--";
                }
            }
            AutomationFWTestCase.testrun.setBrowserRun(browserList);

        }
        catch(Exception e) {
            log.error("COULD NOT GET TEST RUN ENVIRONMENT");
            log.error(StackTraceInfo.getCurrentClassName());
            log.error(StackTraceInfo.getCurrentFileName());
            log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
        }
    }


}
