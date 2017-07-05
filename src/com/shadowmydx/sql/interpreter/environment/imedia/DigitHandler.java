package com.shadowmydx.sql.interpreter.environment.imedia;

import com.shadowmydx.sql.interpreter.environment.ExecutorEnvironment;

/**
 * Created by WULI5 on 7/4/2017.
 */
public class DigitHandler extends ImediaHandler {
    @Override
    public void executeImediaItem(String token) {
        ExecutorEnvironment.setEax(Integer.parseInt(token));
    }
}
