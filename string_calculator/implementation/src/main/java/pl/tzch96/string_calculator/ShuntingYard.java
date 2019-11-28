package pl.tzch96.string_calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class ShuntingYard extends Operators {

    public static ArrayList<String> infixToPostfix(ArrayList<String> infix) {
        ArrayList<String> postfix = new ArrayList<String>();
        Stack<String> operatorStack = new Stack<String>();

        for (String token: infix) {
            if (isNumeric(token)) {
                postfix.add(token);
            } else if (isFunction(token)) {
                operatorStack.push(token);
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() &&
                        (isFunction(operatorStack.peek()) || getPrecedence(token) <= getPrecedence(operatorStack.peek())) &&
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

            Collections.reverse(toRemove);
            postfix.addAll(toRemove);
        }

        return postfix;
    }
}