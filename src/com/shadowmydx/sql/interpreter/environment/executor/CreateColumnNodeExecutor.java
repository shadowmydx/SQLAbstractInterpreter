package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.Column;
import com.shadowmydx.sql.interpreter.environment.item.ColumnContainer;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class CreateColumnNodeExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        String columnName = (String)ExecutorEnvironment.getEax();
        Column column = new Column();
        column.setColumnName(columnName);
        ColumnContainer container = ExecutorEnvironment.varForColumnContainer.get("current");
        container.addColumn(column);
        ExecutorEnvironment.setEax(column);
        return root;
    }
}
