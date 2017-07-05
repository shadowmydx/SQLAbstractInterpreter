package com.shadowmydx.sql.interpreter.environment.executor;

import com.shadowmydx.sql.interpreter.environment.SQLEnvironment;
import com.shadowmydx.sql.interpreter.parser.MatchNode;
import com.shadowmydx.sql.interpreter.parser.NodeType;

import java.util.List;

/**
 * Created by WULI5 on 7/3/2017.
 */
public class ShowNodeExecutor implements NodeExecutor {

    private int startSpace = 0;

    @Override
    public MatchNode execute(MatchNode root, SQLEnvironment env, List<Object> childrenResult) {
        if (root == null) {
            return null;
        }
        MatchNode nextPtr = root;
        while (nextPtr != null) {
            switch (nextPtr.getType()) {
                case TERMINATE:
                    String content = terminate(nextPtr);
                    break;
                case START:
                    nextPtr = startNonTerminate(nextPtr);
                    break;
            }
            nextPtr = nextPtr.getNextNode();
        }
        return null;
    }


    private MatchNode startNonTerminate(MatchNode nextPtr) {
        System.out.print(nextPtr.getMark() + " BEGIN ");
        MatchNode start = nextPtr.getNextNode();
        while (start.getType() != NodeType.END) {
            if (start.getType() == null) {
                start = start.getNextNode();
            }
            if (start.getType() == NodeType.TERMINATE) {
                terminate(start);
            } else if (start.getType() == NodeType.START) {
                start = startNonTerminate(start);
            }
            start = start.getNextNode();
        }
        System.out.print(nextPtr.getMark() + " END ");
        return start;
    }

    public int getStartSpace() {
        return startSpace;
    }

    private String terminate( MatchNode root) {
        String content = "";
        System.out.print(root.getContent() + " ");
        return content;
    }

    private String spaces() {
        String result = "";
        for (int i = 0; i < this.getStartSpace(); i ++) {
            result += ' ';
        }
        return result;
    }

    public void setStartSpace(int startSpace) {
        this.startSpace = startSpace;
    }
}
