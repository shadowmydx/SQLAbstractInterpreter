package com.shadowmydx.sql.util;

import java.io.*;

/**
 * Created by WULI5 on 6/22/2017.
 */
public class FileUtil {
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        int item = reader.read();
        while (item != -1) {
            char single = (char)item;
            buffer.append(single);
            item = reader.read();
        }
        reader.close();
        is.close();
    }

    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }
}
