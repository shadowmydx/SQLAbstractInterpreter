package com.shadowmydx.sql.interpreter.parser.terminate;

/**
 * Created by WULI5 on 6/29/2017.
 */
public interface Handler {
    boolean execute(String treeTerminate, String token);
}
