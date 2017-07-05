package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.parser.MatchNode;
import com.shadowmydx.sql.interpreter.parser.NodeType;

import java.util.List;

/**
 * Created by WULI5 on 7/5/2017.
 */
public class EvalNonTerminate implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        MatchNode start = (MatchNode) ExecutorEnvironment.getEax();
        if (start.getType() != NodeType.START) {
            return start;
        }
        MatchNode newStart = MatchNode.copyNewNonTerminateLink(start);
        NodeExecutor executor = new MainExecutor();
        executor.execute(newStart, env, null);
        return MatchNode.findNonTerminateEnd(start);
    }
}
