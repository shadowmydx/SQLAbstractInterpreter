package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.ColumnType;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class CurrentColumnTypeNodeExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        ColumnType type = (ColumnType) ExecutorEnvironment.getEax();
        ExecutorEnvironment.varForColumnType.put("current", type);
        return root;
    }

}
