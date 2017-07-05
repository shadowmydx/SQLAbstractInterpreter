package com.shadowmydx.sql.interpreter.environment;

import com.shadowmydx.sql.interpreter.environment.item.ColumnContainer;
import com.shadowmydx.sql.interpreter.environment.report.ReportHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WULI5 on 7/3/2017.
 */
public class SQLEnvironment {

    private Map<String, ColumnContainer> env = new HashMap<String, ColumnContainer> ();

    public void add(String key, ColumnContainer container) {
        env.put(key, container);
    }

    public void outputReport(ReportHandler handler) {
        for (String key: env.keySet()) {
            ColumnContainer container = env.get(key);
            handler.report(container);
        }
    }

}
