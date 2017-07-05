package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.Column;
import com.shadowmydx.sql.interpreter.environment.item.ColumnType;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class CreateColumnTypeNodeExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        String columnTypeName = (String)ExecutorEnvironment.getEax();
        ColumnType columnType = new ColumnType();
        columnType.setTypeName(columnTypeName);
        Column column = ExecutorEnvironment.varForColumn.get("current");
        column.setType(columnType);
        ExecutorEnvironment.setEax(columnType);
        return root;
    }

}
