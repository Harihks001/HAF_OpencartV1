package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public ExcelUtils(String path) {
		this.path = path;
	}

//Method to Get the Row count in the work sheet
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	// Method to Get the Cell count in the work sheet
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;

	}

	// Method to Get the Data from a particular cell in the work sheet
	public String getCellData(String sheetName, int rownum, int column) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}

	// Method to set data in a particular Cell
	public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {

		File xlfile = new File(path);
		if (!xlfile.exists()) // If file not exists create a new File
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		// If sheet not exists create a new sheet
		if (workbook.getSheetIndex(sheetName) == -1)
			workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);
		// If row not exists create a new row
		if (sheet.getRow(rownum) == null)
			sheet.createRow(rownum);

		row = sheet.getRow(rownum);
		cell = row.createCell(column);
		cell.setCellValue(data);
		style = workbook.createCellStyle();
		 // Set borders on all sides
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
	    cell.setCellStyle(style);
	    
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();

	}
	
	// Method to set the color of a particular cell as Green
	public void fillGreen(String sheetName, int rownum, int column) throws IOException {
	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);

	    row = sheet.getRow(rownum);
	    cell = row.getCell(column);

	    style = workbook.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    // Set borders on all sides
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
	    cell.setCellStyle(style);

	    cell.setCellStyle(style);
	    fo = new FileOutputStream(path);
	    workbook.write(fo);
	    workbook.close();
	    fo.close();
	    fi.close();
	}
	
	// Method to set the color of a particular cell as Green
	public void fillRed(String sheetName, int rownum, int column) throws IOException {
	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);

	    row = sheet.getRow(rownum);
	    cell = row.getCell(column);

	    style = workbook.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.RED1.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    // Set borders on all sides
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
	    cell.setCellStyle(style);

	    cell.setCellStyle(style);
	    fo = new FileOutputStream(path);
	    workbook.write(fo);
	    workbook.close();
	    fo.close();
	    fi.close();
	}
}
