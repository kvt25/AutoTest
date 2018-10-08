package TestRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.autotest.configure.AutoTestConstants;
import com.autotest.configure.Environment;
import com.autotest.configure.service.ConfigurationService;
import com.autotest.framework.excelentity.AutoTestResultModel;
import com.autotest.framework.excelentity.Scenario;
import com.autotest.utils.AutoTestUtils;
import com.autotest.utils.ExcelHandler;
import com.qsp.min.CheckoutFlows;
import com.qsp.min.LoginFlows;
import com.qsp.min.MyAccount;
import com.qsp.min.NavigationStorefront;
import com.qsp.min.SearchFlows;

import CommonFlows.BasicActions;

public class TestDriver  {
	BasicActions basicActions = new BasicActions();
	Object[][] listTestStep = null;
	String[] scenarioArray = null;
	AutoTestResultModel autoTestResultModel = null;
	
	@Before
	public void setUp() throws IOException {
		autoTestResultModel = new AutoTestResultModel();
		ConfigurationService.loadConfiguration();
	}
	

	@Test
	public void JunitStart() throws Exception {
		boolean execute = false;
		boolean skip = false;
		int skipIndex = 0;
				
		List<String> browsers = ExcelHandler.getBrowsers();
		String[][] scenarios = ExcelHandler.getScenarios();
		
		// loop for browser
		for (int r = 0; r < browsers.size(); r++) {
			int runIndex = 0;
			String scenarioResult = "";

			// ===========Excel result file
			// get the file by copy the old one
			autoTestResultModel.setResultFileName(ExcelHandler.createOutputFile(browsers.get(r)));
			
			// open to write
			//ExcelHandler.excelWritableOpen();

			// Display list of Scenario
			for (int i = 1; i < scenarios[0].length; i++) {
				scenarioArray = scenarios[i - 1][1].toString().split(",");
				runIndex = runIndex + 1;

				ExcelHandler.writeResult(0, runIndex, scenarios[i - 1][0]);

				ExcelHandler.writeResult(14, runIndex, AutoTestUtils.getDatetimeStamp());
			}
			for (int j = 0; j < scenarioArray.length; j++) {
				// Return list of test Step Name
				runIndex = runIndex + 1;

				ExcelHandler.writeResult(5, runIndex, scenarioArray[j].toString());

				listTestStep = ExcelHandler.getListTestCases(scenarioArray[j].toString());

				// ================================
				if (listTestStep != null) {
					int testCaseIndex = runIndex;      		
					for (int k = 0; k < listTestStep.length; k++) {
						runIndex = runIndex + 1;
						// COL_TESTCASE_NAME
						ExcelHandler.writeResult(6, runIndex, listTestStep[k][0].toString());
						// Start Time
						ExcelHandler.writeResult(14, runIndex, AutoTestUtils.getDatetimeStamp());

						String[] testData = ExcelHandler.getDataByTestStep(scenarioArray[j].toString(), listTestStep[k][0].toString());

						if (testData == null || testData.length == 0) {
							if (listTestStep[k][0].toString().equalsIgnoreCase("startTheBrowser")
									&& browsers.get(r).equalsIgnoreCase("FirefoxDefault")) {
								testData = new String[1];
								testData[0] = "FirefoxDefault";
								execute = basicActions.excelReadableRunStep("com.qsp.min.TestScriptManager",
										listTestStep[k][0].toString(), testData);

							} else if (listTestStep[k][0].toString().equalsIgnoreCase("startTheBrowser")
									&& browsers.get(r).equalsIgnoreCase("Chrome")) {
								testData = new String[1];
								testData[0] = "Chrome";
								execute = basicActions.excelReadableRunStep("com.qsp.min.TestScriptManager",
										listTestStep[k][0].toString(), testData);

							} else {
								testData = new String[0];
								execute = basicActions.excelReadableRunStep("com.qsp.min.TestScriptManager",
										listTestStep[k][0].toString(), testData);

							}

						} else {
							String data = "";
							execute = basicActions.excelReadableRunStep("com.qsp.min.TestScriptManager",
									listTestStep[k][0].toString(), testData);

							// Handle test data for writing
							for (int t = 0; t < testData.length; t++) {
								data = data + " || " + testData[t];
							}
							ExcelHandler.writeResult(7, runIndex, data);
						} // end else

						// Check run status of step and write result
						if (!execute || AutoTestConstants.result[0].toString().equals("false")) {
							if (skip) {
								ExcelHandler.writeResult(12, runIndex, "SKIP");
								ExcelHandler.addScreenShoot(13, runIndex, AutoTestConstants.SCREENSHOT_PATH, "SreenshotLink");
							} else {
								ExcelHandler.writeResult(12, runIndex, "FAIL");
								ExcelHandler.writeResult(11, runIndex, AutoTestConstants.result[1].toString());
								ExcelHandler.addScreenShoot(13, runIndex, AutoTestConstants.SCREENSHOT_PATH, "SreenshotLink");
								skip = true;
							}
							// break;
						} else {
							ExcelHandler.writeResult(12, runIndex, "PASS");
							// ===================Add screenshot=====================

							// COL_SCREENSHOT
							ExcelHandler.addScreenShoot(13, runIndex, AutoTestConstants.SCREENSHOT_PATH, "SreenshotLink");
						}
					}
	        		
					if (AutoTestConstants.result[0].toString().equals("false")) {
						ExcelHandler.writeResult(4, testCaseIndex, "FAIL");

					} else {
						ExcelHandler.writeResult(4, testCaseIndex, "PASS");
					}

					// Status of Scenario:
					ExcelHandler.writeResult(1, runIndex, scenarioResult);

					ExcelHandler.excelWritableSetBorder();
					ExcelHandler.excelWritableFillStatus();
					ExcelHandler.excelWritableDrawChart();

				}

				// reset skip
				skip = false;
			}

			ExcelHandler.excelWritableClose();
		}

	}
}
