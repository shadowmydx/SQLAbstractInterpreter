package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.Table;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class CreateTableNodeExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        String tableName = (String) ExecutorEnvironment.getEax();
        Table newTable = new Table();
        newTable.setAction("create");
        newTable.setType("Table");
        newTable.setName(tableName);
        env.add(tableName, newTable);
        ExecutorEnvironment.setEax(newTable);
        return root;
    }

}
