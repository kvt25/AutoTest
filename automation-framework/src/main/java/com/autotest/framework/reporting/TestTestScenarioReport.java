package com.autotest.framework.reporting;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.autotest.framework.excelentity.BrowserEnvTest;
import com.autotest.framework.excelentity.Scenario;
import com.autotest.framework.excelentity.TestCase;
import com.autotest.framework.excelentity.TestStep;

public class TestTestScenarioReport
{
	public static void main(String[] args) throws Exception {
		// XML Data
		Scenario scenario1 = new Scenario();
		scenario1.setIndex(1);
		scenario1.setName("TEST1");
		scenario1.setResult("PASSED");
		scenario1.setDescription("Testing");

		Scenario scenario2 = new Scenario();
		scenario2.setIndex(2);
		scenario2.setName("TEST2");
		scenario2.setResult("FAILED");
		scenario2.setDescription("Testing");

		Scenario scenario3 = new Scenario();
		scenario3.setIndex(3);
		scenario3.setName("TEST3");
		scenario3.setResult("SKIPPED");
		scenario3.setDescription("Testing");


		BrowserEnvTest browserEnvTest1 = new BrowserEnvTest();
		browserEnvTest1.setBrowserName("Firefox");
		browserEnvTest1.setBrowserVersion("29");
		browserEnvTest1.setFailScenarios(4);
		browserEnvTest1.setPassScenarios(2);
		browserEnvTest1.setSkipScenarios(1);

		TestCase testcase1 = new TestCase();
		testcase1.setID("TestcaseID1");
		testcase1.setName("testcase1");
		testcase1.setResult("PASS");
		testcase1.setRunNumber(1);
		testcase1.setScenarioID("ScenarioID1");



		TestStep step1 = new TestStep();
		step1.setScenarioID("ScenarioID1");
		step1.setTestcaseID("TestcaseID1");
		step1.setScenarioStatus("PASS");
		step1.setTestcaseRunNumber("1");
		step1.setTestcaseStatus("PASS");

		testcase1.getTestSteps().add(step1);

		ScenarioReport scenarioreport = new ScenarioReport();
		scenarioreport.getBrowserEnvTests().add(browserEnvTest1);
		scenarioreport.getScenarios().add(scenario1);
		scenarioreport.getScenarios().add(scenario2);
		scenarioreport.getScenarios().add(scenario3);
		scenarioreport.getTestCases().add(testcase1);

		scenarioreport.getTestSteps().add(step1);


		// Create Transformer
		TransformerFactory tf = TransformerFactory.newInstance();
		//StreamSource xslt = new StreamSource("src/com/sai/framework/reporting/ScenarioReportStylesheet.xsl");
		StreamSource xslt = new StreamSource("src/main/resources/ScenarioReportStylesheet.xsl");
		
		Transformer transformer = tf.newTransformer(xslt);

		// Source
		JAXBContext jc = JAXBContext.newInstance(ScenarioReport.class);
		JAXBSource source = new JAXBSource(jc, scenarioreport);

		// Result
		StreamResult result = new StreamResult(System.out);

		// Transform
		transformer.transform(source, result);
	}


}
