package com.autotest.driver;

import java.io.File;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.autotest.utils.ExcelHandler;
import com.autotest.utils.StackTraceInfo;

public class BeforeRunner extends Environment
{

    private final ExcelHandler eh;

    public BeforeRunner (ExcelHandler eh){
        this.eh = eh;
    }

    public void beforeRunner() throws Exception
    {
        try {
            // Get framework path
            //String projectPath = new File("").getAbsolutePath();

            String projectPath = "D:\\01_WORKING\\09_LIBERTY\\AutomationCode";

            projectPath = projectPath.replace("\\", "/");
            String frameworkPath = projectPath.substring(0, projectPath.lastIndexOf("/"));
            String projectName = projectPath.substring(projectPath.lastIndexOf("/") + 1);

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
            utils.copyJavaScriptFoldertoResult(projectPath + "/src/com/sai/javascript", jsFolder);

            AutoTestConstants.excelDataInput = frameworkPath + "/" + AutoTestConstants.DATAINPUT_FOLDER + "/" + AutoTestConstants.EXCEL_FILENAME;
            AutoTestConstants.firefoxProfile = frameworkPath + "/" + AutoTestConstants.JARS_FOLDER + "/FirefoxProfile";
            AutoTestConstants.ieDriver = frameworkPath + "/" + AutoTestConstants.JARS_FOLDER + "/IEDriverServer.exe";
            AutoTestConstants.chromeDriver = frameworkPath + "/" + AutoTestConstants.JARS_FOLDER + "/chromedriver.exe";


            // Get all scripts of Application
            eh.getAllScript();

            //Get list browser
            AutomationFWTestCase.browser = eh.getBrowsers();
            // print out Cross browser
            String logText = "CROSS BROWSER FOR RUNNING TEST: ";
            for(int i=0; i< AutomationFWTestCase.browser.size(); i++)
                if(i == AutomationFWTestCase.browser.size() - 1)
                    logText = logText + AutomationFWTestCase.browser.get(i).toUpperCase();
                else
                    logText = logText + AutomationFWTestCase.browser.get(i).toUpperCase() + "-";

            log.info(logText);
            log.info(log.logLine);


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

}
