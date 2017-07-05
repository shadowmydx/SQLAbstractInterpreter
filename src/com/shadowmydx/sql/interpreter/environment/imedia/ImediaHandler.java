package com.shadowmydx.sql.interpreter.environment.imedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WULI5 on 7/4/2017.
 */
public abstract class ImediaHandler {
    private static List<String> patterns = new ArrayList<String>();
    private static Map<String, ImediaHandler> handlers = new HashMap<String, ImediaHandler> ();

    static {
        patterns.add("\\d+");

        handlers.put("\\d+", new DigitHandler());
    }

    public static void handleImediaValue(String token) {
        String target = null;
        for (String pattern: patterns) {
            if (token.matches(pattern)) {
                target = pattern;
                break;
            }
        }
        handlers.get(target).executeImediaItem(token);
    }

    public abstract void executeImediaItem(String token);
}
