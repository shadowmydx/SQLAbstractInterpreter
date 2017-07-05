package com.shadowmydx.sql.interpreter.parser;

import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.executor.MainExecutor;
import com.shadowmydx.sql.interpreter.environment.executor.NodeExecutor;
import com.shadowmydx.sql.interpreter.environment.executor.ShowNodeExecutor;
import com.shadowmydx.sql.interpreter.environment.report.ReportHandler;
import com.shadowmydx.sql.interpreter.environment.report.StdoutHandler;
import com.shadowmydx.sql.interpreter.sexpression.ExpressionParser;
import com.shadowmydx.sql.interpreter.sexpression.ExpressionTree;
import com.shadowmydx.sql.interpreter.token.TokenParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WULI5 on 6/29/2017.
 */
public class Matcher {
    private static MatchNode generateMatchItem(int startIndex, List<String> tokens, ExpressionTree tree, List<Integer> returnIndex) {
        if (startIndex >= tokens.size()) {
            return null;
        }
        MatchNode root = new MatchNode();
        int lastMatchIndex = startIndex;
        if (tree.getType() == NodeType.TERMINATE) {
            if (TerminateHandler.judge(tree.getToken(), tokens.get(startIndex))) {
                root.setContent(tokens.get(startIndex));
                root.setMark(tree.getToken());
                root.setType(NodeType.TERMINATE);
                lastMatchIndex = startIndex;
                startIndex ++;
            } else {
                return null;
            }
        } else {
            root.setType(NodeType.START);
            root.setMark(tree.getToken());
            ExpressionTree nonTerminate = ExpressionTree.getExprPatternByName(tree.getToken());
            if (nonTerminate != null) {
                MatchNode node = generateMatchNonterminate(startIndex, tokens, nonTerminate, returnIndex);
                if (node == null) {
                    return null;
                }
                root.setNextNode(node);
                node.setPreNode(root);
                MatchNode end = new MatchNode();
                end.setType(NodeType.END);
                end.setMark(tree.getToken());
                node.getLastNode().setNextNode(end);
                startIndex = returnIndex.get(0) + 1;
                lastMatchIndex = startIndex - 1;
            }
        }
        List<ExpressionTree> children = tree.getAllChildren();
        returnIndex.set(0, lastMatchIndex);
        if (children.size() == 0) {
            return root;
        }
        for (ExpressionTree child: children) {
            int tmpReturnIndex = returnIndex.get(0);
            MatchNode node = generateMatchItem(startIndex, tokens, child, returnIndex);
            if (node != null) {
                node.setPreNode(root.getLastNode());
                root.getLastNode().setNextNode(node);
//                returnIndex.set(0, lastMatchIndex);
                return root;
            }
            returnIndex.set(0, tmpReturnIndex);
        }
        return null;
    }

    public static MatchNode generateMatchNonterminate(int startIndex, List<String> tokens, ExpressionTree stmt, List<Integer> returnIndex) {
//        MatchNode root = new MatchNode();
        List<ExpressionTree> children = stmt.getAllChildren();
        for (ExpressionTree child: children) {
            int tmpReturnIndex = returnIndex.get(0);
            MatchNode node = generateMatchItem(startIndex, tokens, child, returnIndex);
            if (node != null) {
//                node.setPreNode(root.getLastNode());
//                root.getLastNode().setNextNode(node);
                return node;
            }
            returnIndex.set(0, tmpReturnIndex);
        }
        return null;
    }

    public static List<List<String>> parseSQLFileByStatements(String path) throws IOException {
        TokenParser parser = new TokenParser();
        List<Character> spaces = new ArrayList<Character>();
        spaces.add('\n');
        spaces.add('\r');
        spaces.add('\t');
        spaces.add(' ');
        parser.setSpaces(spaces);
        List<String> totals = parser.parseItem(path);
        for (int i = 0; i < totals.size(); i ++) {
            totals.set(i, totals.get(i).toUpperCase());
        }
        return splitConventional(totals, ";");
    }

    public static List<List<String>> parseSQLContentByStatements(String content) throws IOException {
        TokenParser parser = new TokenParser();
        List<Character> spaces = new ArrayList<Character>();
        spaces.add('\n');
        spaces.add('\r');
        spaces.add('\t');
        spaces.add(' ');
        parser.setSpaces(spaces);
        List<String> totals = parser.parseItemFromString(content);
        for (int i = 0; i < totals.size(); i ++) {
            totals.set(i, totals.get(i).toUpperCase());
        }
        return splitConventional(totals, ";");
    }

    private static List<List<String>> splitConventional(List<String> input, String splitTarget) {
        List<List<String>> result = new ArrayList<>();
        int prev = 0;

        for (int cur = 0; cur < input.size(); cur++) {
            if (input.get(cur).equals(splitTarget)) {
                result.add(input.subList(prev, cur + 1));
                prev = cur + 1;
            }
        }
        result.add(input.subList(prev, input.size()));

        return result;
    }

    public static void main(String[] args) throws IOException {
        List<List<String>> result = parseSQLFileByStatements("./create_test.sql");
        List<Integer> returnIndex = new ArrayList<Integer> ();
        returnIndex.add(0);
        ExpressionParser.initPattern();
        ExpressionParser.initExecutor();
        MatchNode root = generateMatchNonterminate(0, result.get(2), ExpressionTree.getStmtPatternByName("create_table_stmt"), returnIndex);
        NodeExecutor executor = new MainExecutor();
        SQLEnvironment env = new SQLEnvironment();
        executor.execute(root, env, null);

        returnIndex.set(0, 0);
        root = generateMatchNonterminate(0, result.get(1), ExpressionTree.getStmtPatternByName("execute_stmt"), returnIndex);
        executor = new MainExecutor();
        executor.execute(root, env, null);

        ReportHandler handler = new StdoutHandler();
        env.outputReport(handler);
    }
}
