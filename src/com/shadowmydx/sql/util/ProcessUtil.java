package com.shadowmydx.sql.util;

/**
 * Created by WULI5 on 6/26/2017.
 */
public class ProcessUtil {
    public static boolean isTerminate(String token) {
        for (int i = 0; i < token.length(); i ++) {
            if (!Character.isJavaIdentifierPart(token.charAt(i))) {
                break;
            }
            if (Character.isLowerCase(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
