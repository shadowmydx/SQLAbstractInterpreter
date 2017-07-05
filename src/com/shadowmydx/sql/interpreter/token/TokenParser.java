package com.shadowmydx.sql.interpreter.token;

import com.shadowmydx.sql.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

/**
 * Created by WULI5 on 6/22/2017.
 */
public class TokenParser {

    private String filePath;
    private List<Character> spaces = new ArrayList<Character> ();
    {
        spaces.add('\t');
        spaces.add('\r');
        spaces.add(' ');
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> parseItem(String path) throws IOException {
        this.setFilePath(path);
        String content = FileUtil.readFile(path);
        StateDealer.init();
        StateDealer.setSpaces(this.spaces);
        for (int i = 0; i < content.length(); i ++) {
            char singleChar = content.charAt(i);
            StateDealer.processChar(singleChar);
        }
        return mergeItemForString(StateDealer.getResult());
    }

    public void setSpaces(List<Character> spaces) {
        this.spaces = spaces;
    }

    public List<String> mergeItemForString(List<String> target) {
        List<String> result = new ArrayList<String> ();
        for (int i = 0; i < target.size(); i ++) {
            if (target.get(i).equals("'") || target.get(i).equals("\"")) {
                String targetString = target.get(i);
                for (int j = i + 1; j < target.size(); j ++) {
                    targetString += target.get(j);
                    if (target.get(j).equals(target.get(i))) {
                        i = j;
                        result.add(targetString);
                        break;
                    }
                }
            } else {
                result.add(target.get(i));
            }
        }
        return StateDealer.filterAllBlankItem(result);
    }

    public List<String> mergeItemForComment(List<String> target) {
        return null;
    }

    public static void main(String[] args) throws IOException {
        TokenParser parser = new TokenParser();
        List<String> result = parser.parseItem("./CMTX_ADD_INDEX_P_CON_ROUTE_LEG.sql");
        for (String item: result) {
            System.out.println(item);
        }
        System.out.println("finished");
    }
}
