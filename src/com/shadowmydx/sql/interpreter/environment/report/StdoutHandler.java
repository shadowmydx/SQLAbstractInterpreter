package com.shadowmydx.sql.interpreter.environment.report;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.Column;
import com.shadowmydx.sql.interpreter.environment.item.ColumnContainer;

/**
 * Created by WULI5 on 7/3/2017.
 */
public class StdoutHandler implements ReportHandler {
    @Override
    public void report(ColumnContainer container) {
        System.out.println("=====================================================================");
        System.out.println("Action type: " + container.getAction());
        System.out.println("Container type: " + container.getType());
        System.out.println("Container name: " + container.getName());
        for (String columnName: container) {
            Column column = container.getColumn(columnName);
            System.out.println("---------------------------------------");
            System.out.println("Column Name: " + column.getColumnName());
            System.out.println("Column Type: " + column.getType().getTypeName());
            if (column.getType().getSize() >= 0) {
                System.out.println("Column Type size: " + column.getType().getSize());
            } else {
                System.out.println("Column Type size: default");
            }
            System.out.println("---------------------------------------");
        }
        System.out.println("=====================================================================");
    }
}
