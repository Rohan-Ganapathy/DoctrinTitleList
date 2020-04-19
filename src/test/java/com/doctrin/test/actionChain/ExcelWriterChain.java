package com.doctrin.test.actionChain;

import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.doctrin.test.config.ConfigData;
import com.doctrin.test.utils.Library;

public class ExcelWriterChain {
	
	ConfigData configData = new ConfigData();

	private static String dest;
	private static HSSFWorkbook myWorkBook = new HSSFWorkbook();
	private static HSSFSheet mySheet = myWorkBook.createSheet();
	
	Library library = new Library();
	CareerChain careerChain = new CareerChain();
	
	public static Logger LOGGER = Logger.getLogger(ExcelWriterChain.class.getSimpleName());

	public void excelAction(String No, String name, String title, int rowNum) throws Exception {
		
		dest = configData.getExcelPath();
	    HSSFRow myRow = null;
	    HSSFCell myCell = null;
	    String excelData[][] = new String[1][3];
	    
	    excelData[0][0] = No;
	    excelData[0][1] = name;
	    excelData[0][2] = title;

	    myRow = mySheet.createRow(rowNum);

	    for (int cellNum = 0; cellNum < 3; cellNum++) {
	        myCell = myRow.createCell(cellNum);
	        myCell.setCellValue(excelData[0][cellNum]);

	    }

	    try {
	        FileOutputStream out = new FileOutputStream(dest);
	        myWorkBook.write(out);
	        out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void writeToExcel(WebDriver driver, int listSize) throws Exception {
		
		excelAction("No" , "Name" , "Title", 0);
		
		for (int i = 0; i < listSize; i++) {
			excelAction(String.valueOf(i+1), careerChain.getCoworkerNameFromIndex(driver, i), careerChain.getCoworkerTitleFromIndex(driver, i), i+1);
			LOGGER.info("NO:" + " " + String.valueOf(i+1) + " " + "NAME:"  + " " + careerChain.getCoworkerNameFromIndex(driver, i) + " " + "TITLE:"  + " " + careerChain.getCoworkerTitleFromIndex(driver, i));
		}
	}
}
