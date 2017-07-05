package com.shadowmydx.sql.interpreter.parser.terminate;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class StringHandler implements Handler {
    @Override
    public boolean execute(String treeTerminate, String token) {
        if (token.startsWith("'") || token.startsWith("\"")) {
            if (token.endsWith("'") || token.endsWith("\"")) {
                return true;
            }
        }
        return false;
    }
}
