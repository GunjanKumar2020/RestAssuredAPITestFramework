package com.qa.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReaderUtil {
	
	private static String EXCEL_FILE_PATH="./src/test/resource/testdata/userdata.xlsx";
	private static Workbook workbook;
	private static Sheet sheet;
	
	public static Object[][] readDataFromExcelFile(String sheetname)
	{
		Object data[][]=null;
		try {
				FileInputStream ip = new FileInputStream(EXCEL_FILE_PATH);
				workbook=WorkbookFactory.create(ip);
				sheet=workbook.getSheet(sheetname);
				
				data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				
				for (int i=0;i<sheet.getLastRowNum();i++)
				{
					for (int j=0;j<sheet.getRow(0).getLastCellNum();j++)
					{
						data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					}
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return data;
		  
              }
	}

