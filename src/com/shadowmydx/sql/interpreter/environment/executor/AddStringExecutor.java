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
        String currentString = ExecutorEnvironment.varForString.get("current");
        currentString = processString(currentString);
        nextValue = MatchNode.copyNewNonTerminateLink(nextValue);
        new MainExecutor().execute(nextValue, env, null);
        currentString += processString((String) ExecutorEnvironment.getEax());
        ExecutorEnvironment.setEax(currentString);
        return MatchNode.findNonTerminateEnd(nextValue);
    }

    private String processString(String currentString) {
        if (currentString.startsWith("'") && currentString.endsWith("'")) {
            return currentString.substring(1, currentString.length() - 1);
        }
        if (currentString.startsWith("\"") && currentString.endsWith("\"")) {
            return currentString.substring(1, currentString.length() - 1);
        }
        return currentString;
    }
}
