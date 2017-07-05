package com.shadowmydx.sql.interpreter.sexpression;

import com.shadowmydx.sql.interpreter.parser.NodeType;
import com.shadowmydx.sql.interpreter.token.TokenParser;
import com.shadowmydx.sql.util.ProcessUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WULI5 on 6/26/2017.
 */
public class ExpressionParser {

    private static ExpressionTree parserTreeByTokens(int startIndex, List<String> tokens, List<Integer> returnIndex) {
        ExpressionTree root = new ExpressionTree();

        if (tokens.get(startIndex).equals("(")) {
            root.setToken(tokens.get(startIndex + 1));
            if (ProcessUtil.isTerminate(tokens.get(startIndex + 1))) {
                root.setType(NodeType.TERMINATE);
            } else {
                root.setType(NodeType.NONTERMINATE);
            }
        } else {
            root.setToken(tokens.get(startIndex));
            if (ProcessUtil.isTerminate(tokens.get(startIndex))){
                root.setType(NodeType.TERMINATE);
            } else {
                root.setType(NodeType.NONTERMINATE);
            }
            return root;
        }

        for (int i = startIndex + 2; i < tokens.size(); i ++) {
            if (tokens.get(i).equals("(")) {
                root.addChild(ExpressionParser.parserTreeByTokens(i, tokens, returnIndex));
                i = returnIndex.get(0);
            } else if (tokens.get(i).equals(")")) {
                returnIndex.set(0, i);
                return root;
            } else {
                ExpressionTree node = new ExpressionTree();
                node.setToken(tokens.get(i));
                if (ProcessUtil.isTerminate(tokens.get(i))) {
                    node.setType(NodeType.TERMINATE);
                } else {
                    node.setType(NodeType.NONTERMINATE);
                }
                root.addChild(node);
            }
        }
        return root;
    }

    public static List<ExpressionTree> parseTokensList(List<String> tokens) {
        List<Integer> returnIndex = new ArrayList<Integer> ();
        List<ExpressionTree> trees = new ArrayList<ExpressionTree> ();
        int startIndex = 0;
        while (startIndex < tokens.size()) {
            returnIndex.add(startIndex);
            trees.add(parserTreeByTokens(startIndex, tokens, returnIndex));
            startIndex = returnIndex.get(0) + 1;
        }
        return trees;
    }

    public static List<ExpressionTree> parseTreeFromFile(String path) throws IOException {
        TokenParser parser = new TokenParser();
        List<Character> spaces = new ArrayList<Character> ();
        spaces.add('\n');
        spaces.add('\r');
        spaces.add('\t');
        spaces.add(' ');
        parser.setSpaces(spaces);
        List<String> tokens = parser.parseItem(path);
        List<ExpressionTree> trees = parseTokensList(tokens);
        return trees;
    }

    public static void initPattern() throws IOException {
        List<ExpressionTree> trees = parseTreeFromFile("./test.pat");
        for (ExpressionTree tree: trees) {
            if (tree.getToken().endsWith("_stmt")) {
                ExpressionTree.addStmtPattern(tree.getToken(), tree);
            } else {
                ExpressionTree.addExprPattern(tree.getToken(), tree);
            }
        }
    }

    public static void initExecutor() throws IOException {
        List<ExpressionTree> trees = parseTreeFromFile("./execute.lisp");
        for (ExpressionTree tree: trees) {
            ExpressionTree.addExecutor(tree.getToken(), tree);
        }
    }

    public static void main(String[] args) throws IOException {
        initPattern();
        initExecutor();
        Map<String, ExpressionTree> stmt = ExpressionTree.stmtPattern;
        Map<String, ExpressionTree> expr = ExpressionTree.exprPattern;
        Map<String, ExpressionTree> exec = ExpressionTree.executors;
        System.out.print("yes");
    }
}
