package light.dataprovider.xlsx;

import light.dataprovider.exceptions.WorksheetNotFoundException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class WorkbookParser {
    private XSSFWorkbook workbook;

    public WorkbookParser(){
        workbook = new XSSFWorkbook();
    }

    public WorkbookParser(FileInputStream excelFile) throws IOException {
        this.workbook = new XSSFWorkbook(excelFile);
    }

    public XSSFWorkbook getWorkbook() {
        return this.workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook){
        this.workbook = workbook;
    }

    public XSSFWorkbook getWorkbook(FileInputStream excelFile) throws IOException {
        return new XSSFWorkbook(excelFile);
    }

    public Integer getNumberOfSheets() {
        return this.workbook.getNumberOfSheets();
    }

    public Sheet getSheet(String sheetName) throws WorksheetNotFoundException {

        if (sheetName == null || sheetName.isEmpty())
            throw new WorksheetNotFoundException("Sheet name should not be empty");

        Sheet sheet = this.workbook.getSheet(sheetName);

        if (sheet == null)
            throw new WorksheetNotFoundException("Unable to find sheet '" + sheetName + "'.");
        return sheet;
    }

    public Sheet getSheet(int sheetIndex) throws WorksheetNotFoundException {
        if (sheetIndex <0 || sheetIndex > getNumberOfSheets()){
            throw new WorksheetNotFoundException("Sheet index should not be less than 0 or greater than number of sheets.");
        }

        return this.workbook.getSheetAt(sheetIndex);
    }

    public Sheet createSheet(String sheetName) {
        return this.workbook.createSheet(sheetName);
    }

    public void close() throws IOException {
        this.workbook.close();
    }

    public CellStyle createCellStyle(){
        return workbook.createCellStyle();
    }

    public Font createFont(){
        return workbook.createFont();
    }

}
