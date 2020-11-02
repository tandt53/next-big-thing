package com.tandt53.automation.dataprovider.xlsx;

import com.tandt53.automation.dataprovider.exceptions.CellNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

import java.nio.charset.Charset;

public class CellParser {

    Cell cell;

    public CellParser(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return this.cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public String getStringValue() {
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    public Object getValue() {
        CellType type = cell.getCellType();
        if (type == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (type == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if(type == CellType.STRING){
            return cell.getStringCellValue();
        } else if(type == CellType._NONE){
            return cell.getErrorCellValue();
        } else {
            return cell.getStringCellValue();
        }
    }

    public <T> void setValue(T valueToSet) throws CellNotFoundException {
        if (valueToSet == null)
            throw new CellNotFoundException("Value should not be null.");
        cell.setCellType(CellType.STRING);
        cell.setCellValue("" + valueToSet);
    }

    public <T> void setValueUtf8(T valueToSet) throws CellNotFoundException {
        if (valueToSet == null)
            throw new CellNotFoundException("Value should not be null.");
        cell.setCellType(CellType.STRING);
        cell.setCellValue(new String(("" + valueToSet).getBytes(Charset.forName("UTF-8"))));
    }

    public void setCellStyle(CellStyle cellStyle){
        cell.setCellStyle(cellStyle);
    }

}
