package com.autotest.framework.reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLS2HTMLParser
{

    private static final String NEW_LINE = "\n";
    private static final String HTML_FILE_EXTENSION = ".html";
    private static final String TEMP_FILE_EXTENSION = ".tmp";
    private static final String HTML_SNNIPET_1 = "<!DOCTYPE html><html><head><title>";
    //private static final String HTML_SNNIPET_2 = "</title></head><body><table>";
    private static final String HTML_SNNIPET_2 = "</title></head><body><table cellpading=\"0\" cellspacing=\"0\" style=\"border: 1px solid #ccc;width:300px\">";
    private static final String HTML_SNNIPET_3 = "</table></body></html>";
    private static final String HTML_SNNIPET_TH_S = "<th style=\"background-color: green\">";
    private static final String HTML_SNNIPET_TH_E = "</th>";
    private static final String HTML_TR_S = "<tr>";
    private static final String HTML_TR_E = "</tr>";
    private static final String HTML_TD_S = "<td style=\"border: 1px solid #ccc\">";
    private static final String HTML_TD_E = "</td>";

    private final File file;

    public XLS2HTMLParser(String filePath) {
        file = new File(filePath);
    }

    public void parse() throws FileNotFoundException, IOException {
        BufferedWriter writer;
        Workbook workbook;
        String fileName = file.getName();
        String folderName = file.getParent();
        if (fileName.toLowerCase().endsWith(HTMLReporting.FILE_TYPES[0])) {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } else {
            workbook = new XSSFWorkbook(new FileInputStream(file));
        }

        File tempFile = File.createTempFile(fileName + '-', HTML_FILE_EXTENSION
            + TEMP_FILE_EXTENSION, new File(folderName));
        writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write(HTML_SNNIPET_1);
        writer.write(fileName);
        writer.write(HTML_SNNIPET_2);
        Sheet sheet = workbook.getSheetAt(3);
        Iterator<Row> rows = sheet.rowIterator();
        Iterator<Cell> cells = null;

        int i = 0;
        while (rows.hasNext()) {
            Row row = rows.next();
            cells = row.cellIterator();
            writer.write(NEW_LINE);
            writer.write(HTML_TR_S);
            while (cells.hasNext()) {
                Cell cell = cells.next();
                if (i == 0) {
                    writer.write(HTML_SNNIPET_TH_S);
                    writer.write(cell.toString());
                    writer.write(HTML_SNNIPET_TH_E);
                } else {
                    writer.write(HTML_TD_S);
                    writer.write(cell.toString());
                    writer.write(HTML_TD_E);
                }
                //              writer.write(HTML_TD_S);
                //              writer.write(cell.toString());
                //              writer.write(HTML_TD_E);
            }
            writer.write(HTML_TR_E);
            i++;
        }
        writer.write(NEW_LINE);
        writer.write(HTML_SNNIPET_3);
        writer.close();
        File newFile = new File(folderName + '\\' + fileName.substring(0, fileName.indexOf(".xls")) + HTML_FILE_EXTENSION);
        tempFile.renameTo(newFile);
    }
}
