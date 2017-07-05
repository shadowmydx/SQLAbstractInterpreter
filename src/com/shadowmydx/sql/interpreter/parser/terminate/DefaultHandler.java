package com.shadowmydx.sql.interpreter.parser.terminate;

/**
 * Created by WULI5 on 6/29/2017.
 */
public class DefaultHandler implements Handler {

    @Override
    public boolean execute(String treeTerminate, String token) {
        return treeTerminate.equals(token);
    }
}
