package models;

import lombok.Data;

@Data
public class TableDataCollection {
    private int rowNumber;
    private String colName;
    private String colVal;
    private ColumnSpecialValue colSpecialVal;

    public TableDataCollection(int rowNumber, String colName, String colVal,
                               ColumnSpecialValue colSpecialVal) {
        this.rowNumber = rowNumber;
        this.colName = colName;
        this.colVal = colVal;
        this.colSpecialVal = colSpecialVal;
    }
}
