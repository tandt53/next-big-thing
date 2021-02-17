package tandt.dataprovider.xlsx;

import tandt.dataprovider.exceptions.RowNotFoundException;
import tandt.dataprovider.exceptions.WorksheetNotFoundException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetParser {

    Sheet sheet;

    public SheetParser(Sheet sheet) {
        this.sheet = sheet;
        configSheet();
    }

    public SheetParser(XSSFWorkbook workbook, int sheetIndex) {
        this.sheet = workbook.getSheetAt(sheetIndex);
        configSheet();
    }

    public SheetParser(XSSFWorkbook workbook, String sheetName) {
        this.sheet = workbook.getSheet(sheetName);
        configSheet();
    }

    private void configSheet() {
        sheet.setColumnWidth(15, 15);
        sheet.setDefaultRowHeightInPoints(30f);
    }

    public Row getRow(int rowIndex, boolean isForSetValue) throws RowNotFoundException, WorksheetNotFoundException {
        Row row = this.sheet.getRow(rowIndex);

        if (row == null && isForSetValue) {
            row = createRow(rowIndex);
        }

        if (row == null && !isForSetValue) {
            throw new RowNotFoundException("Unable to find row with index " + rowIndex);
        }

        return row;
    }

    private Row createRow(int rowIndex) {
        return sheet.createRow(rowIndex);
    }

    public Sheet getSheet() {
        return this.sheet;
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public void setColumnWidth(int columnIndex, int width) {
        sheet.setColumnWidth(columnIndex, width);
    }


}
