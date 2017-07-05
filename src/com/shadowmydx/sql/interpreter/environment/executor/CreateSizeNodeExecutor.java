package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.ColumnType;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class CreateSizeNodeExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        ColumnType type = ExecutorEnvironment.varForColumnType.get("current");
        int size = Integer.parseInt((String) ExecutorEnvironment.getEax());
        type.setSize(size);
        ExecutorEnvironment.setEax(size);
        return root;
    }

}
