package pl.tzch96.string_calculator;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ShuntingYard {

    private static List<String> supportedOperators = Arrays.asList("+", "-", "*", "/");
    private static Map<String, Integer> operatorPrecedence = new HashMap<String, Integer>() {
        {
            put("*", 3);
            put("/", 3);
            put("+", 2);
            put("-", 2);
            put("(", 1);
            put(")", 0);
        }
    };

    private static boolean isNumeric(String str) {
        try {
            Double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private static boolean isOperator(String str) {
        return supportedOperators.contains(str);
    }

    private static boolean isFunction(String str) {
        return str.matches("[A-Za-z]+");
    }

    private static Integer getPrecedence(String op) {
        Integer precedence = operatorPrecedence.get(op);
        return precedence;
    }

    public static ArrayList<String> infixToPostfix(ArrayList<String> infix) {
        ArrayList<String> postfix = new ArrayList<String>();
        Stack<String> operatorStack = new Stack<String>();

        for (String token: infix) {
            if (isNumeric(token)) {
                postfix.add(token);
            } else if (isFunction(token)) {
                operatorStack.push(token);
            } else if (isOperator(token)) {
                while ((isFunction(operatorStack.peek()) || getPrecedence(token) <= getPrecedence(operatorStack.peek())) &&
                        !operatorStack.peek().equals("(")) {
                    postfix.add(operatorStack.pop());
                }

                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    postfix.add(operatorStack.pop());
                }

                if (operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                }
            }
        }

        Stack<String> toRemove = new Stack<String>();

        if (!operatorStack.isEmpty()) {
            for (String operator: operatorStack) {
                toRemove.add(operator);
            }

            operatorStack.removeAll(toRemove);
            postfix.addAll(toRemove);
        }

        return postfix;
    }
}