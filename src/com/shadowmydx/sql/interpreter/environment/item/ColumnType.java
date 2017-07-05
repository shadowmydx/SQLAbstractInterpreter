package com.shadowmydx.sql.interpreter.environment.item;

/**
 * Created by WULI5 on 7/3/2017.
 */
public class ColumnType {
    private String typeName;
    private int size = -1;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
