package com.autotest.framework.reporting;


public class HTMLReporting
{
    public static final String[] FILE_TYPES = new String[] { "xls", "xlsx" };

    private final String parseFile;
    public HTMLReporting(String resultExcelPath) {
        this.parseFile = resultExcelPath;
    }

    public void parseXLS() {
        try {
            XLS2HTMLParser parser = new XLS2HTMLParser(this.parseFile);
            parser.parse();
        }
        catch (Exception e) {

        }

    }
}


