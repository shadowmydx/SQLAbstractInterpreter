package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/5/2017.
 */
public class AddStringExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        MatchNode nextValue = (MatchNode) ExecutorEnvironment.getEax();
        String currentString = (String)ExecutorEnvironment.getEax();
        MainExecutor.helpHandler(nextValue, env);
        currentString += (String) ExecutorEnvironment.getEax();
        ExecutorEnvironment.setEax(currentString);
        return MatchNode.findNonTerminateEnd(nextValue);
    }
}
