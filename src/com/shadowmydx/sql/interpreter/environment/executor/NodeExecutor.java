package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.parser.MatchNode;

import java.util.List;

/**
 * Created by WULI5 on 7/3/2017.
 */
public interface NodeExecutor {
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult);
}
