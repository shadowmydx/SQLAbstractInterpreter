package com.shadowmydx.sql.interpreter.parser.terminate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WULI5 on 6/29/2017.
 */
public class DigitHandler implements Handler {
    @Override
    public boolean execute(String treeTerminate, String token) {
        String digit = "^-?[1-9]\\d*$";
        Pattern pattern = Pattern.compile(digit);
        Matcher matcher = pattern.matcher(token);
        return matcher.matches();
    }
}
