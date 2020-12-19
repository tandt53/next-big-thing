package com.tandt53.dataprovider.xlsx;

import com.tandt53.common.Log;
import com.tandt53.dataprovider.exceptions.*;
import com.tandt53.dataprovider.exceptions.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelParser {

    private Log log = new Log(ExcelParser.class);

    private String filePath;
    private FileInputStream excelFile;
    private Integer firstRowIndex = 0;

    private WorkbookParser workbookParser;

    public ExcelParser(String filePath) {
        try {
            initWorkbook(filePath);
        } catch (WorkbookNotFoundException | IOException e) {
            log.info("WorkbookNotFoundException");
        }
    }

    public void setFirstRowIndex(Integer firstRowIndex) {
        this.firstRowIndex = firstRowIndex;
    }

    public Integer getFirstRowIndex() {
        return this.firstRowIndex;
    }

    public Integer getNumberOfSheets() {
        return workbookParser.getNumberOfSheets();
    }

    public String getSheetName(int index) throws WorksheetNotFoundException {
        return workbookParser.getSheet(index).getSheetName();
    }

    public String getValue(String sheetName, int columnIndex, int rowIndex) throws RowNotFoundException, WorksheetNotFoundException, CellNotFoundException, WorkbookNotFoundException {
        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));
        RowParser rowParser = new RowParser(sheetParser.getRow(rowIndex, false));
        CellParser cellParser = new CellParser(rowParser.getCell(columnIndex, false));
        return cellParser.getStringValue();
    }

    public <T> void setValue(String sheetName, int columnIndex, int rowIndex, T valueToSet) throws RowNotFoundException, WorksheetNotFoundException, CellNotFoundException, WorkbookNotFoundException {
        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));
        RowParser rowParser = new RowParser(sheetParser.getRow(rowIndex, false));
        CellParser cellParser = new CellParser(rowParser.getCell(columnIndex, false));
        cellParser.setValue(valueToSet);

        writeToFile();
    }

    private void writeToFile() {
        try {
            FileOutputStream outputStream = new FileOutputStream(this.filePath);
            workbookParser.getWorkbook().write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get `index -th` value in `targetColumnName` that match `conditions`
    public String getValue(String sheetName, Conditions conditions, int index, String targetColumnName) throws WorksheetNotFoundException, CellNotFoundException, ConditionsException, RowNotFoundException, WorkbookNotFoundException {
        return getAllValues(sheetName, conditions, targetColumnName).get(index);
    }

    // set `valueToSet` to the `index -th` row and in `targetColumnName` that match `conditions`
    public <T> void setValue(String sheetName, Conditions conditions, int index, String targetColumnName, T valueToSet) throws WorksheetNotFoundException, ConditionsException, CellNotFoundException, RowNotFoundException, WorkbookNotFoundException {
        log.info("set value in sheet '" + sheetName + "' with index '" + index + "' into column '" + targetColumnName + "' with value '" + valueToSet + "'");
        ArrayList<String> cellValues = new ArrayList<>();

        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));

        // get instance of the first row parser
        RowParser firstRowParser = new RowParser(sheetParser.getRow(firstRowIndex, false));

        // get the column index of the target column in the first row of table
        if (targetColumnName == null || targetColumnName.isEmpty()) {
            throw new CellNotFoundException("column name '" + targetColumnName + "' is not found in the first row of table.");
        }
        int columnIndex = getColumnIndex(firstRowParser, targetColumnName);

        ArrayList<Integer> listRowIndex = getRowIndexes(sheetName, conditions);
        if (listRowIndex == null || listRowIndex.isEmpty()) {
            throw new RowNotFoundException("Unable to find any row with conditions.");
        }

        if (index > listRowIndex.size()) {
            throw new RowNotFoundException("Index " + index + " is greater than number of found rows " + listRowIndex.size());
        }

        RowParser rowParser = new RowParser(sheetParser.getRow(listRowIndex.get(index), false)); // get the row and start parse the row
        CellParser cellParser = new CellParser(rowParser.getCell(columnIndex, false));
        cellParser.setValue(valueToSet);

        writeToFile();
    }

    // get all value in `targetColumnName` that match `conditions`
    public List<String> getAllValues(String sheetName, Conditions conditions, String targetColumnName) throws CellNotFoundException, RowNotFoundException, WorksheetNotFoundException, WorkbookNotFoundException, ConditionsException {
        ArrayList<String> cellValues = new ArrayList<>();

        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));

        // get instance of the first row parser
        RowParser firstRowParser = new RowParser(sheetParser.getRow(firstRowIndex, false));

        // get the column index of the target column in the first row of table
        int columnIndex = getColumnIndex(firstRowParser, targetColumnName);

        ArrayList<Integer> listRowIndex = getRowIndexes(sheetName, conditions);

        // parse all rows
        for (Integer i : listRowIndex) {
            RowParser rowParser = new RowParser(sheetParser.getRow(i, false)); // get the row and start parse the row
            CellParser cellParser = new CellParser(rowParser.getCell(columnIndex, false));
            cellValues.add(cellParser.getStringValue());
        }
        return cellValues;
    }


    // set `valueToSet` to the all row and in `targetColumnName` that match `conditions`
    public <T> void setAllValues(String sheetName, Conditions conditions, String targetColumnName, T valueToSet) throws WorkbookNotFoundException, WorksheetNotFoundException, RowNotFoundException, CellNotFoundException, ConditionsException {
        ArrayList<String> cellValues = new ArrayList<>();

        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));

        // get instance of the first row parser
        RowParser firstRowParser = new RowParser(sheetParser.getRow(firstRowIndex, false));

        // get the column index of the target column in the first row of table
        int columnIndex = getColumnIndex(firstRowParser, targetColumnName);

        ArrayList<Integer> listRowIndex = getRowIndexes(sheetName, conditions);

        // parse all rows
        for (Integer i : listRowIndex) {
            RowParser rowParser = new RowParser(sheetParser.getRow(i, false)); // get the row and start parse the row
            CellParser cellParser = new CellParser(rowParser.getCell(columnIndex, false));
            cellParser.setValue(valueToSet);
        }

        writeToFile();
    }

    public <T> void setValue(String sheetName, int lineIndex, String targetColumn, T valueToSet) throws WorksheetNotFoundException, RowNotFoundException, CellNotFoundException {
        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));
        RowParser firstRowParser = new RowParser(sheetParser.getRow(firstRowIndex, false));
        int columnIndex = getColumnIndex(firstRowParser, targetColumn);
        RowParser rowParser = new RowParser(sheetParser.getRow(lineIndex, false));
        CellParser cellParser = new CellParser(rowParser.getCell(columnIndex, false));
        cellParser.setValue(valueToSet);
        writeToFile();
    }

    public String getValue(String sheetName, int lineIndex, String targetColumn) throws WorksheetNotFoundException, RowNotFoundException, CellNotFoundException {
        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));
        RowParser firstRowParser = new RowParser(sheetParser.getRow(firstRowIndex, false));
        int columnIndex = getColumnIndex(firstRowParser, targetColumn);
        RowParser rowParser = new RowParser(sheetParser.getRow(lineIndex, false));
        CellParser cellParser = new CellParser(rowParser.getCell(columnIndex, false));
        return cellParser.getStringValue();
    }

    public String getValue(String sheetName, String conditionColumn, String conditionValue, String targetColumn) throws WorksheetNotFoundException, CellNotFoundException, ConditionsException, RowNotFoundException, WorkbookNotFoundException {
        Conditions conditions = new Conditions();
        conditions.addCondition(conditionColumn, conditionValue);
        return getValue(sheetName, conditions, 0, targetColumn);
    }

    public <T> void setValue(String sheetName, String conditionColumn, String conditionValue, String targetColumn, T targetValue) throws WorksheetNotFoundException, ConditionsException, RowNotFoundException, CellNotFoundException, WorkbookNotFoundException {
        Conditions conditions = new Conditions();
        conditions.addCondition(conditionColumn, conditionValue);
        setValue(sheetName, conditions, 0, targetColumn, targetValue);
    }

    public <T> void setValueNewRowAtLast(String sheetName, String targetColumn, T targetValue) throws WorksheetNotFoundException, CellNotFoundException, RowNotFoundException, WorkbookNotFoundException {
        int numberOfRow = getRowNum(sheetName);
        setValue(sheetName, numberOfRow, firstRowIndex + targetColumn, targetValue);
    }

    public String getValueAtLastRow(String sheetName, String targetColumn) throws WorksheetNotFoundException, CellNotFoundException, RowNotFoundException, WorkbookNotFoundException {
        int numberOfRow = getRowNum(sheetName);
        return getValue(sheetName, firstRowIndex + numberOfRow - 1, targetColumn);
    }

    public Integer getRowNum(String sheetName) throws WorksheetNotFoundException, RowNotFoundException {
        int rowNum = 0;
        SheetParser sheetParser = new SheetParser(workbookParser.getWorkbook().getSheet(sheetName));
        RowParser firstRowParser = new RowParser(sheetParser.getRow(firstRowIndex, false));
        if (firstRowParser == null) {
            return 0;
        }
        Iterator<Cell> cellIterator = firstRowParser.getRow().iterator();
        int numberOfColumn = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell == null || cell.getStringCellValue().isEmpty()) {
                break;
            }
            numberOfColumn++;
        }
        Iterator<Row> iterator = sheetParser.getSheet().iterator();
        while (iterator.hasNext()) {
            RowParser rowParser = new RowParser(iterator.next());
            if (rowParser.getRow().getPhysicalNumberOfCells() > 0) {
                rowNum++;
            } else {
                break;
            }
        }

        rowNum = rowNum - firstRowIndex;
        return rowNum;
    }

    public ArrayList<Integer> getRowIndexes(String sheetName, Conditions conditions) throws WorksheetNotFoundException, RowNotFoundException, ConditionsException, CellNotFoundException {
        // get set of keys and values from the conditions
        if (conditions == null || conditions.isEmpty())
            throw new ConditionsException("Condition should not be null or empty");
        ArrayList<String> columnNames = conditions.getKeys();
        ArrayList<String> values = conditions.getValues();

        // create an empty set of value
        ArrayList<Integer> listIndexOfRows = new ArrayList<>();

        // get instance of a row
        SheetParser sheetParser = new SheetParser(workbookParser.getSheet(sheetName));
        RowParser firstRowParser = new RowParser(sheetParser.getRow(firstRowIndex, false));

        // get the set of column index based on column name in the first row of table
        ArrayList<Integer> columnIndexes = getColumnIndexes(firstRowParser, columnNames);

        // check the size of column index and column name are equals each other or not
        // add more code for define more cases and expected output
        if (columnIndexes.size() != columnNames.size()) {
            System.out.println("columnNames do not appear in the first row");
        }

        // get the number of rows in the table
        int numberOfRows = getRowNum(sheetName);

        // parse all rows
        for (int i = 1; i < numberOfRows; i++) {
            RowParser rowParser = new RowParser(sheetParser.getRow(i + firstRowIndex, false)); // get the row and start parse the row

            // get all values based on column indexes, and then compare with values in conditions
            ArrayList<String> listCellValues = rowParser.getCellValues(columnIndexes);
            if (listCellValues.equals(values)) {
                // if match, get the value of the cell with index is columnIndex, and then add to the list
                listIndexOfRows.add(i + firstRowIndex);
            }
        }
        log.info("matching rows size: " + listIndexOfRows.size());
        return listIndexOfRows;
    }

    private void initWorkbook(String filePath) throws WorkbookNotFoundException, IOException {
        if (filePath == null) {
            throw new WorkbookNotFoundException("File path should not be null");
        }
        this.filePath = filePath;
        excelFile = new FileInputStream(new File(this.filePath));
        workbookParser = new WorkbookParser(excelFile);
    }

    private int getColumnIndex(RowParser rowParser, String targetColumnName) throws CellNotFoundException {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add(targetColumnName);
        ArrayList<Integer> columnIndexes = getColumnIndexes(rowParser, columnNames);
        if (columnIndexes.size() != 0)
            return columnIndexes.get(0);
        else
            throw new CellNotFoundException("Column Name '" + targetColumnName + "' is not found.");
    }

    private ArrayList<Integer> getColumnIndexes(RowParser rowParser, ArrayList<String> columnNames) {
        ArrayList<Integer> listColumnIndexes = new ArrayList<>();

        ArrayList<String> listContents = new ArrayList<>();
        ArrayList<Integer> listIndexes = new ArrayList<>();

        Iterator<Cell> it = rowParser.getRow().iterator();
        while (it.hasNext()) {
            CellParser cellParser = new CellParser(it.next());
            String cellContent = cellParser.getStringValue();
            int cellIndex = cellParser.getCell().getColumnIndex();
            if (columnNames.contains(cellContent) && !listColumnIndexes.contains(cellIndex)) {
                listContents.add(cellContent);
                listIndexes.add(cellIndex);
            }
        }

        for (String name : columnNames) {
            if (listContents.contains(name) && !listColumnIndexes.contains(listIndexes.get(listContents.indexOf(name)))) {
                listColumnIndexes.add(listIndexes.get(listContents.indexOf(name)));
            }
        }

        return listColumnIndexes;
    }

    public void close() {
        try {
            if (workbookParser != null) {
                workbookParser.close();
                workbookParser = null;
            }

            if (excelFile != null) {
                excelFile.close();
                excelFile = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

