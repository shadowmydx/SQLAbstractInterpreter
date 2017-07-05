package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.parser.MatchNode;
import com.shadowmydx.sql.interpreter.parser.NodeType;

import java.util.List;

/**
 * Created by WULI5 on 7/5/2017.
 */
public class IndexNonTerminateNodeExecutor implements NodeExecutor{
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        MatchNode start = root;
        Integer index = (Integer) ExecutorEnvironment.getEax();
        int count = 0;
        if (index > 0) {
            while (start != null) {
                if (start.getType() == NodeType.NONTERMINATE && count == index) {
                    ExecutorEnvironment.setEax(start);
                    break;
                }
                start = start.getNextNode();
                if (start.getType() == NodeType.NONTERMINATE) {
                    count++;
                }
            }
        } else {
            index = -1;
            while (start != null) {
                if (start.getType() == NodeType.NONTERMINATE && count == index) {
                    ExecutorEnvironment.setEax(start);
                    break;
                }
                start = start.getPreNode();
                if (start.getType() == NodeType.NONTERMINATE) {
                    count++;
                }
            }
        }
        return start;
    }
}
