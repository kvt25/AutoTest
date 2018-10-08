package com.autotest.framework.reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.autotest.framework.excelentity.BrowserEnvTest;
import com.autotest.framework.excelentity.TestRun;
import com.autotest.utils.AutoTestUtils;

public class TestTestRunReport
{
    public static void main(String[] args) throws Exception {

        AutoTestUtils utils = new AutoTestUtils();
        String startTime = utils.getDatetimeStamp();
        String endTime = utils.getDatetimeStamp();
        double time = utils.calculateStepExecutionTime(startTime, endTime);
        // XML Data
        TestRun testrun = new TestRun();
        testrun.setProjectName("Automation Framework V2.0");
        testrun.setRunDate("run date");
        testrun.setBrowserRun("Firefox--Chrome-InternetExplorer-Safari-Opera");

        BrowserEnvTest browserEnvTest1 = new BrowserEnvTest();
        browserEnvTest1.setBrowserName("Firefox");
        browserEnvTest1.setBrowserVersion("29");
        browserEnvTest1.setFailScenarios(4);
        browserEnvTest1.setPassScenarios(2);
        browserEnvTest1.setSkipScenarios(1);

        BrowserEnvTest browserEnvTest2 = new BrowserEnvTest();
        browserEnvTest2.setBrowserName("Chrome");
        browserEnvTest2.setBrowserVersion("30");
        browserEnvTest2.setFailScenarios(1);
        browserEnvTest2.setPassScenarios(2);
        browserEnvTest2.setSkipScenarios(3);

        TestRunReport testrunreport = new TestRunReport();
        testrunreport.getTestRuns().add(testrun);
        testrunreport.getBrowserEnvTests().add(browserEnvTest1);
        testrunreport.getBrowserEnvTests().add(browserEnvTest2);

        // Create Transformer
        TransformerFactory tf = TransformerFactory.newInstance();
        //StreamSource xslt = new StreamSource("src/com/sai/framework/reporting/TestRunReportStylesheet.xsl");
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

        File file = new File("C:\\Users\\dat.nguyenhuu\\Desktop\\testTestRun1.html");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bf = new BufferedWriter(fw);

        // insert page title
        bf.write(writer.toString());
        bf.close();
    }


}
