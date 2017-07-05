package com.shadowmydx.sql.interpreter.environment.report;

import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.environment.item.ColumnContainer;

/**
 * Created by WULI5 on 7/3/2017.
 */
public interface ReportHandler {
    public void report(ColumnContainer container);
}
