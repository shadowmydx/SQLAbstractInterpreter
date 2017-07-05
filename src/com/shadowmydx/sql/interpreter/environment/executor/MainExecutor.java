package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.imedia.ImediaHandler;
import com.shadowmydx.sql.interpreter.parser.MatchNode;
import com.shadowmydx.sql.interpreter.parser.NodeType;
import com.shadowmydx.sql.interpreter.sexpression.ExpressionTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class MainExecutor implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        MatchNode start = root;
        MatchNode ifChange = root;
        while (start != null) {
            if (start.getType() == NodeType.START) {
                ifChange = helpHandler(start, env);
            }
            if (ifChange != start) {
                start = ifChange;
            }
            start = start.getNextNode();
        }
        return root;
    }


    public static MatchNode helpHandler(MatchNode start, SQLEnvironment env) {
        String mark = start.getMark();
        ExpressionTree codeTree = ExecutorEnvironment.getSourceCodeTree(mark);
        if (codeTree != null) {
            return evalCodeTree(start, codeTree, env);
        }
        return start;
    }

    private static MatchNode evalCodeTree(MatchNode start, ExpressionTree codeTree, SQLEnvironment env) {
        List<Object> result = new ArrayList<Object>();
        if (codeTree == null || codeTree.getAllChildren() == null) {
            return start;
        }
        if (codeTree.getType() == NodeType.TERMINATE) {
            ImediaHandler.handleImediaValue(codeTree.getToken());
        }
        for (ExpressionTree child: codeTree.getAllChildren()) {
            evalCodeTree(start, child, env);
            Object returnValue = ExecutorEnvironment.getEax();
            result.add(returnValue);
        }
        NodeExecutor executor = ExecutorEnvironment.getBuildInExecutor(codeTree.getToken());
        if (executor != null) {
            return executor.execute(start, env, result);
        }
        return start;
    }
}
