package com.shadowmydx.sql.interpreter.environment.item;

/**
 * Created by WULI5 on 7/3/2017.
 */
public class Column {
    private String columnName;
    private ColumnType type;

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public String getColumnName() {

        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
