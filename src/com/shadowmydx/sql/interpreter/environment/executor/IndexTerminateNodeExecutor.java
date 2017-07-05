package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.parser.MatchNode;
import com.shadowmydx.sql.interpreter.parser.NodeType;

import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class IndexTerminateNodeExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        MatchNode start = root;
        Integer index = (Integer) ExecutorEnvironment.getEax();
        int count = 0;
        if (index > 0) {
            while (start != null) {
                if (start.getType() == NodeType.TERMINATE && count == index) {
                    ExecutorEnvironment.setEax(start.getContent());
                    break;
                }
                start = start.getNextNode();
                if (start.getType() == NodeType.TERMINATE) {
                    count ++;
                }
            }
        } else {
            index = -index;
            while (start != null) {
                if (start.getType() == NodeType.TERMINATE && count == index) {
                    ExecutorEnvironment.setEax(start.getContent());
                    break;
                }
                start = start.getPreNode();
                if (start.getType() == NodeType.TERMINATE) {
                    count ++;
                }
            }
        }
        return root;
    }

}
