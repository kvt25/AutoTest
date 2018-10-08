package com.autotest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.autotest.configure.InputDataExcelConfig;
import com.autotest.configure.ResultExcelConfig;
import com.autotest.configure.ScenarioExcelConfig;
import com.autotest.configure.TestCaseExcelConfig;
import com.autotest.configure.service.ConfigurationService;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;

public class ExcelHandler {
	private static Log log = new Log(ExcelHandler.class);
	
	public static List<String> getBrowsers() throws Exception {
		try {
			// Open excel file
			List<String> browsers = new ArrayList<>();
			Workbook workbook = Workbook.getWorkbook(new File(ConfigurationService.getValue(AutoTestConstants.EXCEL_DATA_INPUT)));
			Sheet sheet = workbook.getSheet(AutoTestConstants.SCENARIO_SHEET_NAME);
			
			int browserColIndex = Integer.parseInt(ConfigurationService.getValue(ScenarioExcelConfig.COL_BROWSER_NAME));
			int browserRowIndex = Integer.parseInt(ConfigurationService.getValue(ScenarioExcelConfig.ROW_BROWSER_NAME));
			for (int i = browserColIndex + 1; i < sheet.getColumns(); i++) {
				if (!sheet.getCell(i, browserRowIndex).getContents().equals("")) {
					browsers.add(sheet.getCell(i, browserRowIndex).getContents());
				}
			}

			if (browsers.isEmpty()) {
				log.info("PLEASE CONFIGURE BROWSER NAME FOR EXECUTING TEST");
			}

			return browsers;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName());
			log.error("Function 'excelReadableGetBrowser()' throw exception: ");
			log.error(log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelReadableGetScenario
	 * @Description: This function will do. - Open Excel file - Get list Scenario -
	 *               Convert list scenario to Array String
	 * @author DatNguyen
	 * @return String[][] list valid scenario
	 * @throws Exception : null
	 * @CreatedDate: 05/03/2014
	 */
	public static final String[][] getScenarios() throws Exception {
		try {
			// Open excel file
			Workbook workbook = Workbook.getWorkbook(new File(AutoTestConstants.EXCEL_DATA_INPUT));
			Sheet sheet = workbook.getSheet(AutoTestConstants.SCENARIO_SHEET_NAME);

			// Get valid scenario from Scenario sheet
			ArrayList<String> listSceName = new ArrayList<String>();
			ArrayList<String> listSceTestCase = new ArrayList<String>();

			for (int i = ScenarioExcelConfig.ROW_SCENARIO_HEADER + 1; i < sheet.getRows(); i++) {
				String sceName = sheet.getCell(ScenarioExcelConfig.COL_SCENARIO_NAME, i).getContents().trim();
				String sceFlag = sheet.getCell(ScenarioExcelConfig.COL_EXECUTE_FLAG, i).getContents().trim();
				String sceTestCase = sheet.getCell(ScenarioExcelConfig.COL_TESTCASEIDS, i).getContents().trim();
				if (!sceName.equals("") && sceFlag.equals("Yes")) {
					if (!sceTestCase.equals("")) {
						listSceName.add(sceName);
						listSceTestCase.add(sceTestCase);
					} else {
						log.info(StackTraceInfo.getCurrentClassName());
						log.info(StackTraceInfo.getCurrentFileName());
						log.info(StackTraceInfo.getCurrentMethodName());
						log.info("SCENARIO '" + sceName + "': DO NOT HAVE TESTCASE - THIS SCENARIO WILL BE IGNORE");
					}
				}
			}

			// Convert list scenario to Array String
			String[][] listScenario = new String[listSceName.size()][2];

			for (int i = 0; i < listSceName.size(); i++) {
				listScenario[i][0] = listSceName.get(i);
				listScenario[i][1] = listSceTestCase.get(i);
			}

			return listScenario;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			return null;
		}
	}

	/**
	 * @FunctionName: excelReadableGetScenarioDesc
	 * @Description: This function will do. - Open Excel file - Get scenario
	 *               description which are belonged to specific scenario
	 * @author DatNguyen
	 * @return String: Scenario Description
	 * @throws Exception : null
	 * @CreatedDate: 02/07/2014
	 */
	public final String excelReadableGetScenarioDesc(String scename) throws Exception {
		String scenarioDes = "";
		try {
			Workbook workbook = Workbook.getWorkbook(new File(AutoTestConstants.EXCEL_DATA_INPUT));
			Sheet sheet = workbook.getSheet(AutoTestConstants.SCENARIO_SHEET_NAME);
			Cell cell = sheet.findCell(scename);
			int iRowScenario = cell.getRow();
			scenarioDes = sheet.getCell(ScenarioExcelConfig.COL_DESCRIPTION, iRowScenario).getContents();

			return scenarioDes;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			return null;
		}
	}

	/**
	 * @FunctionName: excelReadableGetTestCase
	 * @Description: This function will do. - Open Excel file - Get list test cases
	 *               which are belonged to specific scenario
	 * @author DatNguyen
	 * @return Object[][] list valid scenario
	 * @throws Exception : null
	 * @CreatedDate: 07/03/2014
	 */
	public final Object[][] excelReadableGetTestCase(String scename) throws Exception {
		ArrayList<String> listAllTestCase = new ArrayList<String>();
		ArrayList<String> listTestCase = new ArrayList<String>();
		Object[][] objTestCase = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(AutoTestConstants.EXCEL_DATA_INPUT));
			Sheet sheet = workbook.getSheet(AutoTestConstants.SCENARIO_SHEET_NAME);
			Cell cell = sheet.findCell(scename);
			int iRowScenario = cell.getRow();

			// Get list testcase and convert to arraylist
			String strTestCase = sheet.getCell(ScenarioExcelConfig.COL_TESTCASEIDS, iRowScenario).getContents()
					.replace(" ", "");
			String[] arrTestCase = strTestCase.split(",");
			for (int i = 0; i < arrTestCase.length; i++) {
				listAllTestCase.add(arrTestCase[i]);
			}

			// Get list Test case return
			sheet = workbook.getSheet(AutoTestConstants.TESTCASE_SHEET_NAME);
			for (int j = 0; j < listAllTestCase.size(); j++) {
				Cell cellTestcase = sheet.findCell(listAllTestCase.get(j));
				int testcaserow = cellTestcase.getRow();
				String isRun = sheet.getCell(TestCaseExcelConfig.COL_EXECUTE_FLAG, testcaserow).getContents();
				if (isRun.equals("Yes")) {
					listTestCase.add(listAllTestCase.get(j));
				}
			}

			// Set size for list Test Case
			objTestCase = new Object[listTestCase.size()][4];

			// Convert list testcase to array string
			for (int k = 0; k < listTestCase.size(); k++) {
				Cell cellTestcase = sheet.findCell(listTestCase.get(k));
				int testcaserow = cellTestcase.getRow();
				String executionTimes = sheet.getCell(TestCaseExcelConfig.COL_EXECUTE_TIMES, testcaserow).getContents()
						.replace(" ", "").trim();
				if ("#".equals(executionTimes.substring(0, 1)))
					executionTimes = executionTimes.substring(1);

				String[] runTime = executionTimes.split(",#");
				String testcasename = sheet.getCell(TestCaseExcelConfig.COL_TESTCASE_NAME, testcaserow).getContents();

				int times = Integer.parseInt(runTime[0]);
				objTestCase[k][0] = listTestCase.get(k);
				objTestCase[k][1] = times;
				objTestCase[k][3] = testcasename;
				ArrayList<String> dataset = new ArrayList<String>();

				// Get data list
				if (runTime.length == 1) {
					objTestCase[k][2] = 0;
				} else {
					for (int i = 1; i < runTime.length; i++) {
						dataset.add(runTime[i]);
					}
					objTestCase[k][2] = dataset;
				}
			}
			return objTestCase;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			return null;
		}
	}

	public static final Object[][] getListTestCases(String scenarioName) throws Exception {
		ArrayList<String> listTestCase = new ArrayList<String>();
		Object[][] objTestCase = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(AutoTestConstants.EXCEL_DATA_INPUT));
			Sheet sheet = workbook.getSheet(AutoTestConstants.TESTCASE_SHEET_NAME);
			Cell cellTestCasse = sheet.findCell(scenarioName);
			int iRowScenario = cellTestCasse.getRow();
			int iEndRowScenario = 0;

			// check if execute flag is YES

			if (sheet.getCell(2, iRowScenario).getContents().equals("Yes")) {
				for (int i = iRowScenario; i < sheet.getRows(); i++) {
					if (!sheet.getCell(5, i).getContents().equals("")
							|| !sheet.getCell(6, i).getContents().equals("")) {
						iEndRowScenario = i;
					} else {
						break;
					}

				}
				for (int i = iRowScenario; i <= iEndRowScenario; i++) {
					listTestCase.add(sheet.getCell(5, i).getContents().trim());
				}

				/*
				 * for(int i=0;i<listTestCase.size();i++) { System.out.
				 * println("Displaying list test case of excelReadableGetListTestCase mehtod: "
				 * + listTestCase.get(i)); }
				 */

				// Set size for list Test Case
				objTestCase = new Object[listTestCase.size()][4];
				// Get RunTime
				String executionTimes = sheet.getCell(TestCaseExcelConfig.COL_EXECUTE_TIMES, iRowScenario).getContents()
						.replace(" ", "").trim();
				if ("#".equals(executionTimes.substring(0, 1)))
					executionTimes = executionTimes.substring(1);
				String[] runTime = executionTimes.split(",#");
				int times = Integer.parseInt(runTime[0]);

				// Convert list testcase to array string
				for (int k = 0; k < listTestCase.size(); k++) {
					Cell cellTestcase = sheet.findCell(listTestCase.get(k));
					int testcaserow = cellTestcase.getRow();
					String testcasename = sheet.getCell(TestCaseExcelConfig.COL_TESTCASE_NAME, testcaserow)
							.getContents();
					// Add test step to object
					objTestCase[k][0] = listTestCase.get(k);
					objTestCase[k][1] = times;
					objTestCase[k][3] = testcasename;
				}
				return objTestCase;
			} else {
				// System.out.println("Execute flag is NO ==> Ignored...");
				return objTestCase;
			}
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			return null;
		}
	}

	/**
	 * @FunctionName: excelReadableRunStep
	 * @Description: This function will do. - Init Test Script instance - Check is
	 *               method exist in test script or not - Invoke method
	 * @author DatNguyen
	 * @Param clsName- string class name; stepName- string step name; param-
	 *        String[] test data
	 * @return boolean executed status
	 * @throws Exception : null
	 * @CreatedDate: 07/03/2014
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean excelReadableRunStep(String clsName, String stepName, String[] param) throws Exception {
		boolean isExec = false;
		try {
			// Init class instance
			Class cls = Class.forName(clsName);
			Object obj = cls.newInstance();
			Method[] tempmethod = cls.getDeclaredMethods();

			ArrayList<String> listMethod = new ArrayList<String>();
			for (int i = 0; i < tempmethod.length; i++) {
				listMethod.add(tempmethod[i].getName());
			}

			// Invoke method
			if (listMethod.contains(stepName)) {
				Method method = null;
				if (param.length == 0) {
					try {
						method = cls.getDeclaredMethod(stepName);
						method.invoke(obj);
					} catch (Exception e) {
						log.error(StackTraceInfo.getCurrentClassName());
						log.error(StackTraceInfo.getCurrentFileName());
						log.error(StackTraceInfo.getCurrentMethodName() + log.tab + "STEP '" + stepName
								+ "' REQUIRE DATA");
					}
				} else {
					try {
						method = cls.getDeclaredMethod(stepName, param.getClass());
						method.invoke(obj, (Object) param);
					} catch (Exception e) {
						log.error(StackTraceInfo.getCurrentClassName());
						log.error(StackTraceInfo.getCurrentFileName());
						log.error(StackTraceInfo.getCurrentMethodName() + log.tab + "STEP '" + stepName
								+ "' DO NOT REQUIRE DATA");
					}
				}
				isExec = true;
			} else {
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + "STEP '" + stepName
						+ "' DO NOT EXIST IN TESTSCRIPT");
			}
			return isExec;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			log.error("COULD NOT EXECUTE STEP: " + stepName);
			return isExec;
		}
	}

	/**
	 * 
	 * @param stepName
	 * @param param
	 * @return
	 * @throws Exception
	 */

	public void excelReadableRunStep1(String clsName, String stepName, String[] param) throws Exception {
		// boolean isExec = false;
		try {
			// Init class instance
			Class<?> cls = Class.forName(clsName);
			Object obj = cls.newInstance();
			Method[] tempmethod = cls.getDeclaredMethods();

			ArrayList<String> listMethod = new ArrayList<String>();
			for (int i = 0; i < tempmethod.length; i++) {
				listMethod.add(tempmethod[i].getName());
			}

			// Invoke method
			if (listMethod.contains(stepName)) {
				Method method = null;
				if (param.length == 0) {
					try {
						method = cls.getDeclaredMethod(stepName);
						method.invoke(obj);
					} catch (Exception e) {
						log.error(StackTraceInfo.getCurrentClassName());
						log.error(StackTraceInfo.getCurrentFileName());
						log.error(StackTraceInfo.getCurrentMethodName() + log.tab + "STEP '" + stepName
								+ "' REQUIRE DATA");
					}
				} else {
					try {
						method = cls.getDeclaredMethod(stepName, param.getClass());
						method.invoke(obj, (Object) param);
					} catch (Exception e) {
						log.error(StackTraceInfo.getCurrentClassName());
						log.error(StackTraceInfo.getCurrentFileName());
						log.error(StackTraceInfo.getCurrentMethodName() + log.tab + "STEP '" + stepName
								+ "' DO NOT REQUIRE DATA");
					}
				}
				// isExec = true;
			} else {
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + "STEP '" + stepName
						+ "' DO NOT EXIST IN TESTSCRIPT");
			}
			// return isExec;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			log.error("COULD NOT EXECUTE STEP: " + stepName);
			// return isExec;
		}
	}

	//// =================================================
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean excelReadableRunStep(String stepName, String[] param) throws Exception {
		
		return true;
	}

	public void getAllScript() throws Exception {
		
	}

	/**
	 * @FunctionName: excelReadableGetGlobalVars
	 * @Description:
	 * @author DatNguyen
	 * @return array global Vars
	 * @throws Exception : null
	 * @CreatedDate: 10/03/2014
	 */
	public ArrayList<String> excelReadableGetGlobalVars() throws Exception {

		return null;
	}

	/**
	 * @FunctionName: excelReadableGetData
	 * @Description:
	 * @author DatNguyen
	 * @return String[] data based on TestCase, Step and DataSet Number(Value1 or
	 *         Value2...)
	 * @throws Exception : null
	 * @CreatedDate: 11/03/2014
	 */
	public final String[] excelReadableGetData(final String testcase, final String step, final int dataset)
			throws Exception {
		String[] retdata = new String[] {};
		try {
			// Open excel file
			Workbook workbook = Workbook.getWorkbook(new File(AutoTestConstants.EXCEL_DATA_INPUT));
			Sheet sheet = workbook.getSheet(AutoTestConstants.INPUTDATA_SHEET_NAME);

			// Find Range of TestCase data
			ArrayList<String> data = new ArrayList<String>();

			Cell cellTestCase = sheet.findCell(testcase);
			int iStartRowTestCase = cellTestCase.getRow();
			int iEndRowTestCase = 0;
			int i = 0;
			try {
				// find end row of test case
				for (i = iStartRowTestCase; i <= sheet.getRows(); i++) {
					if (!sheet.getCell(InputDataExcelConfig.COL_FUNCTION_NAME, i + 1).getContents().equals("") && !sheet
							.getCell(InputDataExcelConfig.COL_TESTDATA_VALUE1, i + 1).getContents().equals("")) {
						iEndRowTestCase = i;
						break;
					}
				}
			} catch (Exception e) {
				iEndRowTestCase = i;
			}

			// ***********************
			// BIG MODIFY
			// NEED TO BE TEST CAREFULLY
			Cell cellStep;
			if (AutoTestConstants.iVar1 == 0 || AutoTestConstants.iVar1 <= iStartRowTestCase
					|| AutoTestConstants.iVar1 >= iEndRowTestCase) {
				cellStep = sheet.findCell(step, 0, iStartRowTestCase, 0, iEndRowTestCase, false);
			} else {
				cellStep = sheet.findCell(step, 0, AutoTestConstants.iVar1, 0, iEndRowTestCase, false);
				// Edited 16/10/2014
				if (cellStep == null)
					cellStep = sheet.findCell(step, 0, iStartRowTestCase, 0, iEndRowTestCase, false);
				//////////////
			}

			// ***********************

			// Find Range of Step Data
			// Cell cellStep = sheet.findCell(step, 0, AutoTestConstants.iVar1, 0,
			// iEndRowTestCase,
			// false);
			int iStartRowStep = cellStep.getRow();
			int iEndRowStep = 0;
			for (i = iStartRowStep; i < iEndRowTestCase; i++) {
				if (!sheet.getCell(0, i + 1).getContents().equals("")) {
					iEndRowStep = i;
					break;
				}
				if (i == iEndRowTestCase - 1) {
					iEndRowStep = iEndRowTestCase;
				}
			}
			AutoTestConstants.iVar1 = iEndRowStep;

			// *************************

			// Get data for Step Name
			for (i = iStartRowStep + 1; i <= iEndRowStep; i++) {
				int value = dataset;
				data.add(sheet.getCell(value + 1, i).getContents());
			}

			// Move data from array list to String
			retdata = new String[data.size()];
			for (i = 0; i < data.size(); i++) {
				retdata[i] = data.get(i);
			}
			// write to log
			log.info("TEST DATA OF STEP '" + step + "':");
			for (int z = 0; z < data.size(); z++) {
				log.info(data.get(z).toString());
			}

			return retdata;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			return retdata;
		}
	}

	public static final String[] getDataByTestStep(String testScenario, String testStep) throws Exception {
		String[] retdata = new String[] {};
		try {
			// Open excel file
			Workbook workbook = Workbook.getWorkbook(new File(AutoTestConstants.EXCEL_DATA_INPUT));
			Sheet sheet = workbook.getSheet(AutoTestConstants.INPUTDATA_SHEET_NAME);

			// Find Range of TestCase data

			int iRowData = 0;
			int iEndColumnData = 0;
			boolean find = false;

			ArrayList<String> data = new ArrayList<String>();

			Cell cellTestScenario = sheet.findCell(testScenario);

			if (cellTestScenario == null) {
				retdata = null;
			} else {
				int iStartRowTestStep = cellTestScenario.getRow();
				int iEndRowTestStep = 0;
				int i = 0;
				// System.out.println(sheet.getColumns());
				try {
					// find end row of test step
					for (i = 0; i <= sheet.getRows(); i++) {
						if (sheet.getCell(1, iStartRowTestStep + i).getContents().equals("")) {
							iEndRowTestStep = i;
							break;
						}
					}

					for (int j = 0; j < iEndRowTestStep; j++) {
						if (sheet.getCell(1, iStartRowTestStep + j).getContents().equals(testStep)) {
							find = true;
							iRowData = iStartRowTestStep + j;
						}
					}

					if (find) {
						for (int z = 2; z < sheet.getColumns(); z++) {
							if (sheet.getCell(z, iRowData).getContents().equals("")) {
								iEndColumnData = z;
								break;
							} else {
								data.add(sheet.getCell(z, iRowData).getContents());
							}
						}
					}
				} catch (Exception e) {
					iEndRowTestStep = i;
				}

				if (data != null && !data.isEmpty()) {
					// Move data from array list to String
					retdata = new String[data.size()];
					for (i = 0; i < data.size(); i++) {
						retdata[i] = data.get(i);
					}
					// write to log
					// log.info("TEST DATA OF STEP '" + step + "':");
					for (int z = 0; z < data.size(); z++) {
						log.info(data.get(z).toString());
					}
				}
				/*
				 * else { retdata = null; }
				 */

			}

			return retdata;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			return retdata;
		}
	}

	/**
	 * @FunctionName: excelReadableGetStepName
	 * @Description:
	 * @author DatNguyen
	 * @return Object[][] of step based on test case input
	 * @throws Exception : null
	 * @CreatedDate: 11/03/2014
	 */
	public final Object[][] excelReadableGetStepName(final String testcase) {
		Object[][] listStep = null;
		try {
			File inputWorkbook = new File(AutoTestConstants.EXCEL_DATA_INPUT);
			Workbook workbook = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = workbook.getSheet(AutoTestConstants.TESTCASE_SHEET_NAME);
			Cell cell = sheet.findCell(testcase);
			int iStartRowTestcase = cell.getRow();
			int iEndRowTestCase = sheet.getRows();
			int i = 0;

			// Find step range
			try {
				for (i = iStartRowTestcase;; i++) {
					if (!sheet.getCell(TestCaseExcelConfig.COL_TESTCASEID, i).getContents().equals("")) {
						iEndRowTestCase = i;
						break;
					}
				}
			} catch (Exception e) {
				iEndRowTestCase = i;
			}

			// Get list step of testcase
			listStep = new Object[iEndRowTestCase - iStartRowTestcase + 1][4];
			int j = 0;
			for (i = iStartRowTestcase; i <= iEndRowTestCase; i++) {
				// sheet.getCell(9, i).getContents();
				listStep[j][0] = sheet.getCell(TestCaseExcelConfig.COL_AUTOMATION_STEPNAME, i).getContents();
				listStep[j][1] = sheet.getCell(TestCaseExcelConfig.COL_STEP_NUMBER, i).getContents();
				listStep[j][2] = sheet.getCell(TestCaseExcelConfig.COL_STEP_DESCRIPTION, i).getContents();
				listStep[j][3] = sheet.getCell(TestCaseExcelConfig.COL_EXPECTED_RESULT, i).getContents();
				j++;
			}
			return listStep;
		} catch (Exception e) {
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			return listStep;
		}
	}

	/**
	 * @FunctionName: excelWritableResult
	 * @Description: This function will write result into excel file
	 * @author DatNguyen
	 * @CreatedDate: 11/03/2014
	 */
	public static final void writeResult(final int col, final int row, final String content) throws Exception {
		Label label;
		try {
			WritableFont font = new WritableFont(WritableFont.ARIAL, 8);
			WritableCellFormat format = new WritableCellFormat(font);
			label = new Label(col, row, content, format);
			AutoTestConstants.sheetWritable.addCell(label);
		} catch (Exception e) {
			log.error("COULD NOT WRITE TO EXCEL FILE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelWritableSeperate
	 * @Description: This function will write gray line to separate the result of
	 *               each test case
	 * @author DatNguyen
	 * @CreatedDate: 11/03/2014
	 */
	public final void excelWritableSeperate(ArrayList<Integer> indexList) throws Exception {
		try {
			for (int i = 0; i < indexList.size(); i++) {
				for (int j = 0; j <= ResultExcelConfig.COL_END_TIME; j++) {
					WritableCellFormat cellFormat = new WritableCellFormat();
					cellFormat.setBackground(Colour.GRAY_25, Pattern.SOLID);
					Label label = new Label(j, indexList.get(i), "", cellFormat);
					AutoTestConstants.sheetWritable.addCell(label);
				}
			}
		} catch (Exception e) {
			log.error("COULD NOT INSERT SPACE BETWEEN TWO TESTCASE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelWritableFillStatus
	 * @Description: This function will fill status of test run into excel file
	 * @author DatNguyen
	 * @CreatedDate: 11/03/2014
	 */
	public final void excelWritableFillStatus() throws Exception {
		try {
			Label label;
			for (int i = 0; i <= AutoTestConstants.sheetWritable.getColumns(); i++) {
				for (int j = 1; j <= AutoTestConstants.sheetWritable.getRows(); j++) {
					WritableCell cell = AutoTestConstants.sheetWritable.getWritableCell(i, j);
					if (cell.getContents().equals("PASS")) {
						if (cell.getType() == CellType.LABEL) {
							WritableFont font = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD);
							font.setColour(Colour.GREEN);
							WritableCellFormat format = new WritableCellFormat(font);
							label = new Label(i, j, "PASS", format);
							AutoTestConstants.sheetWritable.addCell(label);
						}
					} else if (cell.getContents().equals("FAIL")) {
						if (cell.getType() == CellType.LABEL) {
							WritableFont font = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD);
							font.setColour(Colour.RED);
							WritableCellFormat format = new WritableCellFormat(font);
							label = new Label(i, j, "FAIL", format);
							AutoTestConstants.sheetWritable.addCell(label);
						}
					} else if (cell.getContents().equals("SKIP")) {
						if (cell.getType() == CellType.LABEL) {
							WritableFont font = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD);
							font.setColour(Colour.VIOLET);
							WritableCellFormat format = new WritableCellFormat(font);
							label = new Label(i, j, "SKIP", format);
							AutoTestConstants.sheetWritable.addCell(label);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("COULD NOT FILL COLOR FOR STATUS");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelWritableDrawChart
	 * @Description: This function will write the chart result on excel file
	 * @author DatNguyen
	 * @CreatedDate: 11/03/2014
	 */
	public final void excelWritableDrawChart() throws Exception {
		try {
			double pass = 0;
			double fail = 0;
			double skip = 0;
			Number num;

			// draw chart for TestCase Status
			for (int i = 0; i <= AutoTestConstants.sheetWritable.getRows(); i++) {
				// ResultExcelConfig.COL_TESTCASE_STATUS
				WritableCell cell = AutoTestConstants.sheetWritable.getWritableCell(4, i);
				if (cell.getContents().equals("PASS")) {
					pass = pass + 1;
				}
				if (cell.getContents().equals("FAIL")) {
					fail = fail + 1;
				}
				if (cell.getContents().equals("SKIP")) {
					skip = skip + 1;
				}
			}
			// ChartConfig.COL_TESTCASE_PASSED 29
			//
			num = new Number(29, 24, pass);
			AutoTestConstants.sheetWritable.addCell(num);
			num = new Number(29, 25, fail);
			AutoTestConstants.sheetWritable.addCell(num);
			num = new Number(29, 26, skip);
			AutoTestConstants.sheetWritable.addCell(num);

			// draw chart for Step Status
			pass = 0;
			fail = 0;
			skip = 0;
			for (int i = 0; i <= AutoTestConstants.sheetWritable.getRows(); i++) {
				// ResultExcelConfig.COL_AUTOMATION_STEP_STATUS 12
				WritableCell cell = AutoTestConstants.sheetWritable.getWritableCell(12, i);
				if (cell.getContents().equals("PASS")) {
					pass = pass + 1;
				}
				if (cell.getContents().equals("FAIL")) {
					fail = fail + 1;
				}
				if (cell.getContents().equals("SKIP")) {
					skip = skip + 1;
				}
			}
			num = new Number(29, 8, pass);
			AutoTestConstants.sheetWritable.addCell(num);
			num = new Number(29, 9, fail);
			AutoTestConstants.sheetWritable.addCell(num);
			num = new Number(29, 10, skip);
			AutoTestConstants.sheetWritable.addCell(num);

			log.info("UPDATED RESULT ON CHART");

		} catch (Exception e) {
			log.error("COULD NOT DRAW RESUT CHART");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	public static final String createOutputFile(final String browser) throws Exception {
		try {
			String preoutputFile = AutoTestConstants.EXCEL_FILENAME.substring(
					AutoTestConstants.EXCEL_FILENAME.indexOf("_") + 1, AutoTestConstants.EXCEL_FILENAME.length() - 4) + "_"
					+ browser + "_" + utils.getDatetimeStamp() + ".xls";

			String outputFile = AutoTestConstants.RESULT_FOLDER + preoutputFile.replaceAll(":", "-").replaceAll(" ", "_");

			File originalFile = new File(AutoTestConstants.EXCEL_DATA_INPUT);
			File destinationFile = new File(outputFile);
			InputStream inStream = new FileInputStream(originalFile);
			OutputStream outStream = new FileOutputStream(destinationFile);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			inStream.close();
			outStream.close();
			return outputFile;
		} catch (Exception e) {
			log.error("COULD NOT CREATE EXECUTION EXCEL FILE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelReadableGetGlobalVars
	 * @Description:
	 * @author DatNguyen
	 * @CreatedDate: 11/03/2014
	 */
	public final void excelCopySendMail() throws Exception {
		try {
			String outputFile = AutoTestConstants.EXCEL_RESULT_FILE.substring(0,
					AutoTestConstants.EXCEL_RESULT_FILE.indexOf("_"));
			outputFile = outputFile + "_EMAILRESULT.xls";
			File originalFile = new File(AutoTestConstants.EXCEL_RESULT_FILE);
			File destinationFile = new File(outputFile);
			InputStream inStream = new FileInputStream(originalFile);
			OutputStream outStream = new FileOutputStream(destinationFile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			inStream.close();
			outStream.close();
		} catch (Exception e) {
			log.error("COULD NOT COPY RESULT FILE FOR SEND MAIL");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelReadableGetGlobalVars
	 * @Description:
	 * @author DatNguyen
	 * @CreatedDate: 11/03/2014
	 */
	public static final void addScreenShoot(final int col, final int row, final String link, final String desc)
			throws Exception {
		try {
			File image = new File(link);
			WritableHyperlink hlk = new WritableHyperlink(col, row, image, desc);
			AutoTestConstants.sheetWritable.addHyperlink(hlk);
		} catch (Exception e) {
			log.error("COULD NOT ADD SCREEN SHOT FOR FAIL TESTCASE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelReadableGetGlobalVars
	 * @Description:
	 * @author DatNguyen
	 * @CreatedDate: 11/03/2014
	 */
	public final void excelWritableOpen() throws Exception {
		try {
			InputStream ins = new FileInputStream(AutoTestConstants.EXCEL_RESULT_FILE);
			Workbook workbook = Workbook.getWorkbook(ins);
			File outputFile = new File(AutoTestConstants.EXCEL_RESULT_FILE);
			AutoTestConstants.workbookWritable = Workbook.createWorkbook(outputFile, workbook);
			AutoTestConstants.sheetWritable = AutoTestConstants.workbookWritable
					.getSheet(AutoTestConstants.RESULTS_SHEET_NAME);
		} catch (Exception e) {
			log.error("COULD NOT OPEN RESULT EXCEL FILE TO WRITE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @FunctionName: excelReadableGetGlobalVars
	 * @Description:
	 * @author DatNguyen
	 * @return array global Vars
	 * @throws Exception : null
	 * @CreatedDate: 11/03/2014
	 */
	public final void excelWritableClose() throws Exception {
		try {
			AutoTestConstants.workbookWritable.write();
			AutoTestConstants.workbookWritable.close();

			log.info(MessageFormat.format("WRITE TO RESULT EXCEL FILE \"{0}\" IS DONE",
					AutoTestConstants.EXCEL_RESULT_FILE));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("COULD NOT CLOSE RESULT EXCEL FILE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}

	}

	public final void excelWritableSetBorder() throws Exception {
		try {
			Label label;
			int maxRow = AutoTestConstants.sheetWritable.getRows();
			// ResultExcelConfig.COL_END_TIME
			for (int i = 0; i <= 15; i++) {
				for (int j = 1; j <= maxRow; j++) {
					WritableCell cell = AutoTestConstants.sheetWritable.getWritableCell(i, j);
					WritableCellFormat format = new WritableCellFormat();
					format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
					format.setAlignment(Alignment.LEFT);

					if ("Link".equals(cell.getContents().trim())) {
						WritableFont font = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD);
						font.setColour(Colour.DARK_BLUE);
						font.setItalic(true);
						format.setFont(font);
						label = new Label(i, j, cell.getContents(), format);
					} else {
						label = new Label(i, j, cell.getContents(), format);
					}
					AutoTestConstants.sheetWritable.addCell(label);
				}
			}
			log.info("SET BORDER ON RESULT SHEET");
		} catch (Exception e) {
			log.error("COULD NOT SET BORDER ON RESULT SHEET");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	public final void excelWritableScenarioGrouping() throws Exception {
		try {
			int startRowScenario = 2;
			int maxRow = AutoTestConstants.sheetWritable.getRows();
			for (int i = 2; i < maxRow; i++) {
				WritableCell cell = AutoTestConstants.sheetWritable.getWritableCell(ResultExcelConfig.COL_SCENARIO_NAME,
						i);
				if (i < maxRow - 1) {
					if (!"".equals(cell.getContents().trim())) {
						AutoTestConstants.sheetWritable.setRowGroup(startRowScenario, i - 1, true);
						startRowScenario = i + 1;
					}
				} else {
					AutoTestConstants.sheetWritable.setRowGroup(startRowScenario, i - 1, true);
				}
			}
			log.info("GROUPED SCENARIOS ON RESULT SHEET");
		}

		catch (Exception e) {
			log.error("COULD NOT GROUP SCENARIOS ON RESULT SHEET");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	public final void excelWritableTestCaseGrouping1() throws Exception {
		try {
			int startRowTestCase = 3;
			int maxRow = AutoTestConstants.sheetWritable.getRows();
			for (int i = 2; i < maxRow; i++) {
				WritableCell cell = AutoTestConstants.sheetWritable.getWritableCell(ResultExcelConfig.COL_TESTCASE_NUMBER,
						i);
				if (i < maxRow - 1) {
					if (!"".equals(cell.getContents().trim())) {
						AutoTestConstants.sheetWritable.setRowGroup(startRowTestCase, i - 2, true);
						startRowTestCase = i + 1;
					}
				} else {
					AutoTestConstants.sheetWritable.setRowGroup(startRowTestCase, i - 2, true);
				}
			}
			log.info("GROUPED TESTCASES ON RESULT SHEET");
		}

		catch (Exception e) {
			log.error("COULD NOT GROUP TESTCASES ON RESULT SHEET");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	public final void excelWritableTestCaseGrouping(ArrayList<String> testcaseList) throws Exception {
		try {
			int startRow = 0;
			int endRow = 0;
			int j = 2;
			int orderNo = 0;
			int maxRow = AutoTestConstants.sheetWritable.getRows();
			while (j < maxRow && orderNo < testcaseList.size()) {

				WritableCell cellTestCase = AutoTestConstants.sheetWritable
						.getWritableCell(ResultExcelConfig.COL_TESTCASE_NUMBER, j);

				if (testcaseList.get(orderNo).toString().equals(cellTestCase.getContents().trim())) {
					startRow = j + 1;
					for (int k = j + 1; k < maxRow; k++) {
						cellTestCase = AutoTestConstants.sheetWritable
								.getWritableCell(ResultExcelConfig.COL_TESTCASE_NUMBER, k);
						WritableCell cellScenario = AutoTestConstants.sheetWritable
								.getWritableCell(ResultExcelConfig.COL_SCENARIO_NAME, k - 1);
						if (!"".equals(cellTestCase.getContents()) && "".equals(cellScenario.getContents())) {
							endRow = k - 2;
							j = k + 1;
							break;
						}
						if (!"".equals(cellTestCase.getContents()) && !"".equals(cellScenario.getContents())) {
							endRow = k - 3;
							j = k + 1;
							break;
						}
					}
					AutoTestConstants.sheetWritable.setRowGroup(startRow, endRow, true);
					orderNo++;
				} else {
					j++;
				}

			}
			log.info("GROUPED TESTCASES ON RESULT SHEET");
		}

		catch (Exception e) {
			log.error("COULD NOT GROUP TESTCASES ON RESULT SHEET");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}
}
