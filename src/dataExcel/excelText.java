package dataExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import resources.Base;

public class excelText {

	private static int cellNum = 0;

	public static ArrayList<String> dataInTheTable(String nameInTheTable) throws IOException {

		// fileInputStream argument
		// path to the xml file
		Base base = new Base();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Data.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheetAmountBook = workbook.getNumberOfSheets();
		ArrayList<String> namesInTheTable = new ArrayList<String>();

		for (int i = 0; i < sheetAmountBook; i++) {

			XSSFSheet sheet = workbook.getSheetAt(i);

			Iterator<Row> rows = sheet.iterator();
			if (workbook.getSheetName(i).equalsIgnoreCase("dataTest")) {

				Row rowNum = getRow(workbook, rows, nameInTheTable);
				int firstCell = getCell(workbook, rows, nameInTheTable);

				String valuesOfCellInTheCol = rowNum.getCell(firstCell).getStringCellValue();

				Iterator<Cell> cellsInTheSameCol = rowNum.cellIterator();

				while (cellsInTheSameCol.hasNext()) {
					Cell nextCell = cellsInTheSameCol.next();
//					int TitleOfdata = nextCell.getColumnIndex();
//					System.out.println(TitleOfdata);
//					int lastValue = nextCell.getRow().getLastCellNum();
//					int firstValue = nextCell.getRow().getFirstCellNum();

					if (nextCell.getColumnIndex() > firstCell) {
						System.out.println(nextCell.getStringCellValue());
					}

				}

			}
		}
		return namesInTheTable;

	}


	public static Row getRow(XSSFWorkbook workbook, Iterator<Row> rows, String nameInTheTable) throws IOException {
		 Row rowNum = null;
		while (rows.hasNext()) {
			
			Row row = rows.next();
			Iterator<Cell> cellsInTheRow = row.cellIterator();
			while (cellsInTheRow.hasNext()) {
				Cell cell = cellsInTheRow.next();
		
				if (cell.getStringCellValue().equalsIgnoreCase(nameInTheTable)) {

					rowNum = cell.getRow();
					cellNum = cell.getColumnIndex();
				}
			}
		}
		return rowNum;
	}

	public static int getCell(XSSFWorkbook workbook, Iterator<Row> rows, String nameInTheTable) throws IOException {
	

		while (rows.hasNext()) {
	
			Row row = rows.next();
			System.out.println(row.getRowNum());
			Iterator<Cell> cellsInTheRow = row.cellIterator();
	
			while (cellsInTheRow.hasNext()) {
				Cell cell = cellsInTheRow.next();
				System.out.println(cell.getStringCellValue());
				
				if (cell.getStringCellValue().equalsIgnoreCase(nameInTheTable)) {
					cellNum = cell.getColumnIndex();
				}
			}
		}
	
		return cellNum;
	}

}
