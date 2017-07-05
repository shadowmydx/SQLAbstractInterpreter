package com.shadowmydx.sql.interpreter.parser;

/**
 * Created by WULI5 on 6/29/2017.
 */
public class MatchNode {

    private NodeType type;
    private MatchNode preNode;
    private MatchNode nextNode;
    private String content;
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MatchNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MatchNode nextNode) {
        this.nextNode = nextNode;
    }

    public MatchNode getPreNode() {

        return preNode;
    }

    public void setPreNode(MatchNode preNode) {
        this.preNode = preNode;
    }

    public NodeType getType() {

        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public MatchNode getLastNode() {
        MatchNode start = this;
        MatchNode current = start;
        while (start != null) {
            current = start;
            start = start.nextNode;
        }
        return current;
    }

    public static MatchNode findNonTerminateEnd(MatchNode target) {
        if (target.getType() != NodeType.START) {
            return target;
        }
        int count = 1;
        MatchNode start = target.getNextNode();
        while (count != 0) {
            if (start.getType() == NodeType.START) {
                count ++;
            } else if (start.getType() == NodeType.END) {
                count --;
            }
            start = start.getNextNode();
        }
        return start;
    }
}
