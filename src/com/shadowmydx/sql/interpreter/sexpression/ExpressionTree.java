package com.shadowmydx.sql.interpreter.sexpression;

import com.shadowmydx.sql.interpreter.parser.NodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WULI5 on 6/26/2017.
 */
public class ExpressionTree {
    public static Map<String, ExpressionTree> stmtPattern = new HashMap<String, ExpressionTree> ();
    public static Map<String, ExpressionTree> exprPattern = new HashMap<String, ExpressionTree> ();
    public static Map<String, ExpressionTree> executors = new HashMap<String, ExpressionTree> ();

    private NodeType type;
    private String token;

    private List<ExpressionTree> children = new ArrayList<ExpressionTree> ();

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        if ((token.startsWith("'") && token.endsWith("'")) || (token.startsWith("\"") && token.endsWith("\""))) {
            this.token = token.substring(1, token.length() - 1);
        } else {
            this.token = token;
        }
    }

    public void addChild(ExpressionTree child) {
        children.add(child);
    }

    public List<ExpressionTree> getAllChildren() {
        return children;
    }

    public static ExpressionTree getStmtPatternByName(String patternName) {
        return stmtPattern.get(patternName);
    }

    public static void addStmtPattern(String patternName, ExpressionTree tree) {
        stmtPattern.put(patternName, tree);
    }

    public static ExpressionTree getExprPatternByName(String patternName) {
        return exprPattern.get(patternName);
    }

    public static void addExprPattern(String patternName, ExpressionTree tree) {
        exprPattern.put(patternName, tree);
    }

    public static ExpressionTree getExecutorByName(String executor) {
        return executors.get(executor);
    }

    public static void addExecutor(String executor, ExpressionTree tree) {
        executors.put(executor, tree);
    }
}
