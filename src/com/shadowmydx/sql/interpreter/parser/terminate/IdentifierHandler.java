package com.shadowmydx.sql.interpreter.parser.terminate;

import com.shadowmydx.sql.interpreter.parser.TerminateHandler;
import com.shadowmydx.sql.util.ProcessUtil;

/**
 * Created by WULI5 on 6/29/2017.
 */
public class IdentifierHandler implements Handler {
    @Override
    public boolean execute(String treeTerminate, String token) {
        return ProcessUtil.isTerminate(token) && !TerminateHandler.isKeyword(token);

    }
}
