package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;
import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.parser.MatchNode;
import com.shadowmydx.sql.interpreter.parser.Matcher;
import com.shadowmydx.sql.interpreter.sexpression.ExpressionTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WULI5 on 7/5/2017.
 */
public class EvalCurrentString implements NodeExecutor {
    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        try {
            String result = ExecutorEnvironment.varForString.get("current");
            List<List<String>> statements = Matcher.parseSQLContentByStatements(result);
            for (List<String> stmt: statements) {
                for (String key: ExpressionTree.stmtPattern.keySet()) {
                    List<Integer> returnIndex = new ArrayList<Integer>();
                    returnIndex.add(0);
                    if (stmt.size() == 0) {
                        continue;
                    }
                    MatchNode node = Matcher.generateMatchNonterminate(0, stmt, ExpressionTree.getStmtPatternByName(key), returnIndex);
                    if (node != null) {
                        NodeExecutor executor = new MainExecutor();
                        executor.execute(node, env, null);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return root;
    }
}
