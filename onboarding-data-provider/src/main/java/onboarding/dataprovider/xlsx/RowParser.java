package onboarding.dataprovider.xlsx;

import onboarding.dataprovider.exceptions.CellNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class RowParser {

    private Row row;

    public RowParser(Row row) {
        this.row = row;
    }

    public Row getRow(){
        return this.row;
    }

    public void setRow(Row row){
        this.row = row;
    }

    public Cell getCell(int cellIndex, boolean isForSetValue) throws CellNotFoundException {
        if (cellIndex < 0) {
            throw new CellNotFoundException("Cell index should be equals or greater than 0");
        }
        Cell cell = row.getCell(cellIndex);
        if (cell == null && isForSetValue) {
            cell = createCell(cellIndex);
        }
        if (cell == null && !isForSetValue) {
            throw new CellNotFoundException("Unable to find cell with index " + cellIndex);
        }

        return cell;

}



    private Cell createCell(int columnIndex) {
        return this.row.createCell(columnIndex);
    }

    public LinkedHashMap<String, Integer> getAllColumnIndexes(){
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        Iterator<Cell> it = row.iterator();
        while (it.hasNext()) {
            Cell cell = it.next();
            int cellIndex = cell.getColumnIndex();
            String value = new CellParser(cell).getStringValue().trim();

            map.put(value, cellIndex);
        }

        return map;
    }

    public ArrayList<Integer> getColumnIndexes(ArrayList<String> columnNames) {
        ArrayList<Integer> matchedIndex = new ArrayList<>();
        ArrayList<Integer> listIndex = new ArrayList<>();
        ArrayList<String> listValuesInRow = new ArrayList<>();

        Iterator<Cell> it = row.iterator();
        while (it.hasNext()) {
            Cell cell = it.next();
            int cellIndex = cell.getColumnIndex();
            listIndex.add(cellIndex);
            listValuesInRow.add(new CellParser(cell).getStringValue());
        }

        for (String name : columnNames) {
            if (listValuesInRow.contains(name)) {
                int indexOfColumn = listIndex.get(listValuesInRow.indexOf(name));
                if (!matchedIndex.contains(indexOfColumn))
                    matchedIndex.add(indexOfColumn);
            }
        }

        return matchedIndex;
    }

    public ArrayList<Cell> getCells(ArrayList<Integer> indexes) throws CellNotFoundException {
        ArrayList<Cell> listValues = new ArrayList<>();
        for (Integer id : indexes) {
            listValues.add(getCell(id, false));
        }
        return listValues;
    }

    public ArrayList<String> getCellValues(ArrayList<Integer> indexes) throws CellNotFoundException {
        ArrayList<String> listValues = new ArrayList<>();
        for (Integer id : indexes) {
            listValues.add(new CellParser(getCell(id, false)).getStringValue());
        }
        return listValues;
    }

    public int getColumnIndex(String targetColumn) throws CellNotFoundException {
        ArrayList<String> columnName = new ArrayList<String>();
        columnName.add(targetColumn);
        ArrayList<Integer> listValue = getColumnIndexes(columnName);
        if (listValue.isEmpty())
            throw new CellNotFoundException(targetColumn + " is not present in the row");
        return listValue.get(0);
    }

    public int getColumnNum(){
        return row.getPhysicalNumberOfCells();
    }
}
