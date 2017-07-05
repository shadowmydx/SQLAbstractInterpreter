package com.shadowmydx.sql.interpreter.environment;

import com.shadowmydx.sql.interpreter.environment.executor.*;
import com.shadowmydx.sql.interpreter.environment.item.Column;
import com.shadowmydx.sql.interpreter.environment.item.ColumnContainer;
import com.shadowmydx.sql.interpreter.environment.item.ColumnType;
import com.shadowmydx.sql.interpreter.sexpression.ExpressionTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class ExecutorEnvironment {

    private static final Map<String, ExpressionTree> executors = ExpressionTree.executors;
    private static final Map<String, NodeExecutor> buildIn = new HashMap<String, NodeExecutor>();
    public static Map<String, Column> varForColumn = new HashMap<String, Column>();
    public static Map<String, ColumnType> varForColumnType = new HashMap<String, ColumnType>();
    public static Map<String, ColumnContainer> varForColumnContainer = new HashMap<String, ColumnContainer>();
    public static Map<String, String> varForString = new HashMap<String, String> ();

    private static Object eax = null; // handle return value by build-in function

    static {
        buildIn.put("_create_table", new CreateTableNodeExecutor());
        buildIn.put("_create_column", new CreateColumnNodeExecutor());
        buildIn.put("_create_type", new CreateColumnTypeNodeExecutor());
        buildIn.put("_create_size", new CreateSizeNodeExecutor());
        buildIn.put("_modify_current_table", new CurrentTableNodeExecutor());
        buildIn.put("_modify_current_column", new CurrentColumnNodeExecutor());
        buildIn.put("_modify_current_type", new CurrentColumnTypeNodeExecutor());
        buildIn.put("_index_terminate", new IndexTerminateNodeExecutor());
        buildIn.put("_index_non_terminate", new IndexNonTerminateNodeExecutor());
        buildIn.put("_modify_current_string", new CurrentStringNodeExecutor());
        buildIn.put("_add_string", new AddStringExecutor());
        buildIn.put("_execute_current_string", new EvalCurrentString());
        buildIn.put("_execute_non_terminate", new EvalNonTerminate());
    }

    public static Object getEax() {
        return eax;
    }

    public static void setEax(Object eax) {
        ExecutorEnvironment.eax = eax;
    }

    public static ExpressionTree getSourceCodeTree(String token) {
        return executors.get(token);
    }

    public static NodeExecutor getBuildInExecutor(String token) {
        return buildIn.get(token);
    }
}
