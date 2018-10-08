package com.autotest.framework.excelentity;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class AutoTestResultModel {
	
	private String resultFileName;
	private WritableWorkbook workbookWritable;
    private WritableSheet sheetWritable;

	public String getResultFileName() {
		return resultFileName;
	}

	public WritableWorkbook getWorkbookWritable() {
		return workbookWritable;
	}

	public void setWorkbookWritable(WritableWorkbook workbookWritable) {
		this.workbookWritable = workbookWritable;
	}

	public WritableSheet getSheetWritable() {
		return sheetWritable;
	}

	public void setSheetWritable(WritableSheet sheetWritable) {
		this.sheetWritable = sheetWritable;
	}

	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}
}
