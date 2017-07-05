package com.shadowmydx.sql.interpreter.token;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by WULI5 on 6/22/2017.
 */
public class StateDealer {

    enum State {
        DIGIT, WORD, SYMBOL, BLANK, NONE
    }

    private static State currentState = State.NONE;

    private static String currentItem = "";
    private static List<String> result = new ArrayList<String>();
    private static List<Character> spaces = new ArrayList<Character> ();

    public static List<Character> getSpaces() {
        return spaces;
    }

    public static void setSpaces(List<Character> spaces) {
        StateDealer.spaces = spaces;
    }

    private static boolean isSpaces(char target) {
        for (int i = 0; i < spaces.size(); i ++) {
            if (spaces.get(i).equals(target)) {
                return true;
            }
        }
        return false;
    }

    private static int[] generateMergeHint(char target) {
        int[] mergeHint = new int[4];
        if (Character.isJavaIdentifierPart(target)) {
            mergeHint[1] = 1;
            if (Character.isDigit(target)) {
                mergeHint[0] = 1;
            }
        } else if (isSpaces(target)) {
            mergeHint[3] = 1;
        } else {
            mergeHint[2] = 1;
        }

        return mergeHint;
    }

    private static State determineStartState(int[] mergeHint) {
        if (mergeHint[0] == 1) {
            return State.DIGIT;
        } else if (mergeHint[1] == 1) {
            return State.WORD;
        } else if (mergeHint[2] == 1) {
            return State.SYMBOL;
        } else {
            return State.BLANK;
        }
    }

    public static void init() {
        currentState = State.NONE;

        currentItem = "";
        result = new ArrayList<String>();
    }

    public static void processChar(char target) {
        int[] mergeHint = generateMergeHint(target);
        switch (currentState) {
            case NONE:
                currentItem = target + "";
                currentState = determineStartState(mergeHint);
                break;
            case DIGIT:
                if (mergeHint[0] == 1) {
                    currentItem += target;
                } else {
                    currentState = determineStartState(mergeHint);
                    result.add(currentItem);
                    currentItem = target + "";
                }
                break;
            case WORD:
                if (mergeHint[1] == 1) {
                    currentItem += target;
                } else {
                    currentState = determineStartState(mergeHint);
                    result.add(currentItem);
                    currentItem = target + "";
                }
                break;
            case SYMBOL:
                result.add(currentItem);
                currentState = determineStartState(mergeHint);
                currentItem = target + "";
                break;
            case BLANK:
                result.add(currentItem);
                currentState = determineStartState(mergeHint);
                currentItem = target + "";
                break;

        }
    }

    public static List<String> getResult() {
        if (currentItem.length() >= 0) {
            processChar(' '); // add last item to result list
        }
        List<String> totalResult = new ArrayList<String> ();
        for (String item: result) {
            totalResult.add(item);
        }
        return totalResult;
    }

    public static List<String> filterAllBlankItem(List<String> target) {
        List<String> result = new ArrayList<String> ();
        for (String item: target) {
            if (determineStartState(generateMergeHint(item.charAt(0))) != State.BLANK) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String test = "";
        List<String> test1 = new ArrayList<String> ();
        for (int i = 0; i < 10; i ++) {
            test += i;
            test1.add(test);
            test = "";
        }
        Character a = ';';
        StateDealer.init();
        StateDealer.processChar(';');
        List<String> dd = StateDealer.getResult();
        System.out.print(a + "");
        System.out.println(determineStartState(generateMergeHint('(')));
        determineStartState(generateMergeHint(';'));
    }
}
