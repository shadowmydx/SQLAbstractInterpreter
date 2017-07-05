package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.ColumnContainer;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class CurrentTableNodeExecutor implements NodeExecutor{
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        ColumnContainer container = (ColumnContainer) ExecutorEnvironment.getEax();
        ExecutorEnvironment.varForColumnContainer.put("current", container);
        return root;
    }

}
