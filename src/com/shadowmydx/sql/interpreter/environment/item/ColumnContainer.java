package com.shadowmydx.sql.interpreter.environment.item;

import java.util.*;

/**
 * Created by WULI5 on 7/3/2017.
 */
public class ColumnContainer implements Iterable<String> {
    private Map<String, Column> columns = new HashMap<String, Column>();
    private String type;
    private String action;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void addColumn(Column column) {
        columns.put(column.getColumnName(), column);
    }

    public Column getColumn(String columnName) {
        return columns.get(columnName);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Iterator<String> iterator() {
        return columns.keySet().iterator();
    }
}
