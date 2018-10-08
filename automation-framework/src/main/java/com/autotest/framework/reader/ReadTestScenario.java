package com.autotest.framework.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.autotest.configure.AutoTestConstants;
import com.autotest.framework.entity.TestScenario;
import com.autotest.framework.entity.TestSuite;

public class ReadTestScenario
{

    public TestScenario getListTestSuite(TestScenario sce) {
        try {
            List<TestSuite> tss = new ArrayList<TestSuite>();
            InputStream inputSteam = new FileInputStream(AutoTestConstants.excelDataInput);
            XSSFWorkbook workbook = new XSSFWorkbook(inputSteam);
            XSSFSheet sheet = workbook.getSheetAt(0);


            return sce;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
